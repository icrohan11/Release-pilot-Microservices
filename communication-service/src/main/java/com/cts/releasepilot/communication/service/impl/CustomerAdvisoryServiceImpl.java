package com.cts.releasepilot.communication.service.impl;

import com.cts.releasepilot.communication.common.AdvisoryStatus;
import com.cts.releasepilot.communication.common.Severity;
import com.cts.releasepilot.communication.dto.CustomerAdvisoryRequestDTO;
import com.cts.releasepilot.communication.dto.CustomerAdvisoryResponseDTO;
import com.cts.releasepilot.communication.entity.CustomerAdvisory;
import com.cts.releasepilot.communication.exception.ResourceNotFoundException;
import com.cts.releasepilot.communication.mapper.CustomerAdvisoryMapper;
import com.cts.releasepilot.communication.repository.CustomerAdvisoryRepository;
import com.cts.releasepilot.communication.service.CustomerAdvisoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerAdvisoryServiceImpl implements CustomerAdvisoryService {

    private final CustomerAdvisoryRepository advisoryRepository;
    private final CustomerAdvisoryMapper advisoryMapper;

    public CustomerAdvisoryServiceImpl(CustomerAdvisoryRepository advisoryRepository,
                                       CustomerAdvisoryMapper advisoryMapper) {
        this.advisoryRepository = advisoryRepository;
        this.advisoryMapper = advisoryMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerAdvisoryResponseDTO> getAllAdvisories() {
        return advisoryRepository.findAll().stream().map(advisoryMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerAdvisoryResponseDTO getAdvisoryById(Long id) {
        return advisoryMapper.toResponse(findOrThrow(id));
    }

    @Override
    @Transactional
    public CustomerAdvisoryResponseDTO createAdvisory(CustomerAdvisoryRequestDTO dto) {
        CustomerAdvisory advisory = advisoryMapper.toEntity(dto);
        advisory.setAdvisoryID(null);
        if (advisory.getStatus() == null) {
            advisory.setStatus(AdvisoryStatus.Draft);
        }
        return advisoryMapper.toResponse(advisoryRepository.save(advisory));
    }

    @Override
    @Transactional
    public CustomerAdvisoryResponseDTO updateAdvisory(Long id, CustomerAdvisoryRequestDTO dto) {
        CustomerAdvisory advisory = findOrThrow(id);
        advisoryMapper.updateEntity(advisory, dto);
        return advisoryMapper.toResponse(advisoryRepository.save(advisory));
    }

    @Override
    @Transactional
    public void deleteAdvisory(Long id) {
        CustomerAdvisory advisory = findOrThrow(id);
        advisoryRepository.delete(advisory);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerAdvisoryResponseDTO> getAdvisoriesByRelease(Long releaseID) {
        return advisoryRepository.findByReleaseID(releaseID).stream()
                .map(advisoryMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerAdvisoryResponseDTO> getAdvisoriesBySeverity(Severity severity) {
        return advisoryRepository.findBySeverity(severity).stream()
                .map(advisoryMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerAdvisoryResponseDTO> getAdvisoriesByStatus(AdvisoryStatus status) {
        return advisoryRepository.findByStatus(status).stream()
                .map(advisoryMapper::toResponse).toList();
    }

    private CustomerAdvisory findOrThrow(Long id) {
        return advisoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Advisory not found with ID: " + id));
    }
}
