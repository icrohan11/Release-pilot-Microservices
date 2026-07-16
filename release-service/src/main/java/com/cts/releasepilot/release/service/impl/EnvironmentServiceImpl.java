package com.cts.releasepilot.release.service.impl;

import com.cts.releasepilot.release.common.EnvStatus;
import com.cts.releasepilot.release.dto.EnvironmentRequestDTO;
import com.cts.releasepilot.release.dto.EnvironmentResponseDTO;
import com.cts.releasepilot.release.entity.Environment;
import com.cts.releasepilot.release.exception.ResourceNotFoundException;
import com.cts.releasepilot.release.mapper.EnvironmentMapper;
import com.cts.releasepilot.release.repository.EnvironmentRepository;
import com.cts.releasepilot.release.service.EnvironmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EnvironmentServiceImpl implements EnvironmentService {

    private final EnvironmentRepository environmentRepository;
    private final EnvironmentMapper environmentMapper;

    public EnvironmentServiceImpl(EnvironmentRepository environmentRepository,
                                  EnvironmentMapper environmentMapper) {
        this.environmentRepository = environmentRepository;
        this.environmentMapper = environmentMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EnvironmentResponseDTO> getAll() {
        return environmentRepository.findAll().stream().map(environmentMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public EnvironmentResponseDTO getById(Long id) {
        return environmentMapper.toResponse(findOrThrow(id));
    }

    @Override
    @Transactional
    public EnvironmentResponseDTO create(EnvironmentRequestDTO dto) {
        Environment entity = environmentMapper.toEntity(dto);
        entity.setEnvID(null);
        if (entity.getStatus() == null) {
            entity.setStatus(EnvStatus.Active);
        }
        return environmentMapper.toResponse(environmentRepository.save(entity));
    }

    @Override
    @Transactional
    public EnvironmentResponseDTO update(Long id, EnvironmentRequestDTO dto) {
        Environment entity = findOrThrow(id);
        environmentMapper.updateEntity(entity, dto);
        return environmentMapper.toResponse(environmentRepository.save(entity));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Environment entity = findOrThrow(id);
        environmentRepository.delete(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EnvironmentResponseDTO> getByProduct(Long productID) {
        return environmentRepository.findByProductID(productID).stream()
                .map(environmentMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<EnvironmentResponseDTO> getByStatus(EnvStatus status) {
        return environmentRepository.findByStatus(status).stream()
                .map(environmentMapper::toResponse).toList();
    }

    private Environment findOrThrow(Long id) {
        return environmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Environment not found with ID: " + id));
    }
}
