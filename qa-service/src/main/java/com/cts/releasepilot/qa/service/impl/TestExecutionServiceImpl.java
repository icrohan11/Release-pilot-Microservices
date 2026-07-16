package com.cts.releasepilot.qa.service.impl;

import com.cts.releasepilot.qa.client.ReleaseClientService;
import com.cts.releasepilot.qa.common.ExecutionStatus;
import com.cts.releasepilot.qa.dto.ReleaseDTO;
import com.cts.releasepilot.qa.dto.TestExecutionRequestDTO;
import com.cts.releasepilot.qa.dto.TestExecutionResponseDTO;
import com.cts.releasepilot.qa.entity.TestExecution;
import com.cts.releasepilot.qa.exception.ResourceNotFoundException;
import com.cts.releasepilot.qa.mapper.TestExecutionMapper;
import com.cts.releasepilot.qa.repository.TestExecutionRepository;
import com.cts.releasepilot.qa.service.TestExecutionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TestExecutionServiceImpl implements TestExecutionService {

    private static final Logger log = LoggerFactory.getLogger(TestExecutionServiceImpl.class);

    private final TestExecutionRepository testExecutionRepository;
    private final TestExecutionMapper testExecutionMapper;
    private final ReleaseClientService releaseClientService;

    public TestExecutionServiceImpl(TestExecutionRepository testExecutionRepository,
                                    TestExecutionMapper testExecutionMapper,
                                    ReleaseClientService releaseClientService) {
        this.testExecutionRepository = testExecutionRepository;
        this.testExecutionMapper = testExecutionMapper;
        this.releaseClientService = releaseClientService;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TestExecutionResponseDTO> getAllExecutions() {
        return testExecutionRepository.findAll().stream().map(testExecutionMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public TestExecutionResponseDTO getExecutionById(Long id) {
        return testExecutionMapper.toResponse(findOrThrow(id));
    }

    @Override
    @Transactional
    public TestExecutionResponseDTO createExecution(TestExecutionRequestDTO dto) {
        // Validate the referenced release via the release-service (resilience-guarded).
        ReleaseDTO release = releaseClientService.getRelease(dto.getReleaseID());
        log.info("Creating execution for release {} (status={})", dto.getReleaseID(), release.getStatus());

        TestExecution execution = testExecutionMapper.toEntity(dto);
        execution.setExecutionID(null);
        return testExecutionMapper.toResponse(testExecutionRepository.save(execution));
    }

    @Override
    @Transactional
    public TestExecutionResponseDTO updateExecution(Long id, TestExecutionRequestDTO dto) {
        TestExecution execution = findOrThrow(id);
        testExecutionMapper.updateEntity(execution, dto);
        return testExecutionMapper.toResponse(testExecutionRepository.save(execution));
    }

    @Override
    @Transactional
    public void deleteExecution(Long id) {
        TestExecution execution = findOrThrow(id);
        testExecutionRepository.delete(execution);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TestExecutionResponseDTO> getExecutionsByRelease(Long releaseID) {
        return testExecutionRepository.findByReleaseID(releaseID).stream().map(testExecutionMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TestExecutionResponseDTO> getExecutionsBySuite(Long suiteID) {
        return testExecutionRepository.findBySuiteID(suiteID).stream().map(testExecutionMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TestExecutionResponseDTO> getExecutionsByStatus(ExecutionStatus status) {
        return testExecutionRepository.findByStatus(status).stream().map(testExecutionMapper::toResponse).toList();
    }

    private TestExecution findOrThrow(Long id) {
        return testExecutionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Test execution not found with ID: " + id));
    }
}
