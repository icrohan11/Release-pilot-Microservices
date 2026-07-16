package com.cts.releasepilot.qa.service.impl;

import com.cts.releasepilot.qa.common.SuiteStatus;
import com.cts.releasepilot.qa.common.SuiteType;
import com.cts.releasepilot.qa.dto.TestSuiteRequestDTO;
import com.cts.releasepilot.qa.dto.TestSuiteResponseDTO;
import com.cts.releasepilot.qa.entity.TestSuite;
import com.cts.releasepilot.qa.exception.ResourceNotFoundException;
import com.cts.releasepilot.qa.mapper.TestSuiteMapper;
import com.cts.releasepilot.qa.repository.TestSuiteRepository;
import com.cts.releasepilot.qa.service.TestSuiteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TestSuiteServiceImpl implements TestSuiteService {

    private final TestSuiteRepository testSuiteRepository;
    private final TestSuiteMapper testSuiteMapper;

    public TestSuiteServiceImpl(TestSuiteRepository testSuiteRepository, TestSuiteMapper testSuiteMapper) {
        this.testSuiteRepository = testSuiteRepository;
        this.testSuiteMapper = testSuiteMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TestSuiteResponseDTO> getAllSuites() {
        return testSuiteRepository.findAll().stream().map(testSuiteMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public TestSuiteResponseDTO getSuiteById(Long id) {
        return testSuiteMapper.toResponse(findOrThrow(id));
    }

    @Override
    @Transactional
    public TestSuiteResponseDTO createSuite(TestSuiteRequestDTO dto) {
        TestSuite suite = testSuiteMapper.toEntity(dto);
        suite.setSuiteID(null);
        return testSuiteMapper.toResponse(testSuiteRepository.save(suite));
    }

    @Override
    @Transactional
    public TestSuiteResponseDTO updateSuite(Long id, TestSuiteRequestDTO dto) {
        TestSuite suite = findOrThrow(id);
        testSuiteMapper.updateEntity(suite, dto);
        return testSuiteMapper.toResponse(testSuiteRepository.save(suite));
    }

    @Override
    @Transactional
    public void deleteSuite(Long id) {
        TestSuite suite = findOrThrow(id);
        testSuiteRepository.delete(suite);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TestSuiteResponseDTO> getSuitesByProduct(Long productID) {
        return testSuiteRepository.findByProductID(productID).stream().map(testSuiteMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TestSuiteResponseDTO> getSuitesByStatus(SuiteStatus status) {
        return testSuiteRepository.findByStatus(status).stream().map(testSuiteMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TestSuiteResponseDTO> getSuitesByType(SuiteType type) {
        return testSuiteRepository.findByType(type).stream().map(testSuiteMapper::toResponse).toList();
    }

    private TestSuite findOrThrow(Long id) {
        return testSuiteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Test suite not found with ID: " + id));
    }
}
