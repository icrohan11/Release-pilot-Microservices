package com.cts.releasepilot.qa.service.impl;

import com.cts.releasepilot.qa.common.GateOutcome;
import com.cts.releasepilot.qa.common.GateStatus;
import com.cts.releasepilot.qa.dto.ReleaseGateRequestDTO;
import com.cts.releasepilot.qa.dto.ReleaseGateResponseDTO;
import com.cts.releasepilot.qa.entity.ReleaseGate;
import com.cts.releasepilot.qa.exception.ResourceNotFoundException;
import com.cts.releasepilot.qa.mapper.ReleaseGateMapper;
import com.cts.releasepilot.qa.repository.ReleaseGateRepository;
import com.cts.releasepilot.qa.service.ReleaseGateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReleaseGateServiceImpl implements ReleaseGateService {

    private final ReleaseGateRepository releaseGateRepository;
    private final ReleaseGateMapper releaseGateMapper;

    public ReleaseGateServiceImpl(ReleaseGateRepository releaseGateRepository, ReleaseGateMapper releaseGateMapper) {
        this.releaseGateRepository = releaseGateRepository;
        this.releaseGateMapper = releaseGateMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReleaseGateResponseDTO> getAllGates() {
        return releaseGateRepository.findAll().stream().map(releaseGateMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ReleaseGateResponseDTO getGateById(Long id) {
        return releaseGateMapper.toResponse(findOrThrow(id));
    }

    @Override
    @Transactional
    public ReleaseGateResponseDTO createGate(ReleaseGateRequestDTO dto) {
        ReleaseGate gate = releaseGateMapper.toEntity(dto);
        gate.setGateID(null);
        return releaseGateMapper.toResponse(releaseGateRepository.save(gate));
    }

    @Override
    @Transactional
    public ReleaseGateResponseDTO updateGate(Long id, ReleaseGateRequestDTO dto) {
        ReleaseGate gate = findOrThrow(id);
        releaseGateMapper.updateEntity(gate, dto);
        return releaseGateMapper.toResponse(releaseGateRepository.save(gate));
    }

    @Override
    @Transactional
    public void deleteGate(Long id) {
        ReleaseGate gate = findOrThrow(id);
        releaseGateRepository.delete(gate);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReleaseGateResponseDTO> getGatesByRelease(Long releaseID) {
        return releaseGateRepository.findByReleaseID(releaseID).stream().map(releaseGateMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReleaseGateResponseDTO> getGatesByOutcome(GateOutcome outcome) {
        return releaseGateRepository.findByOutcome(outcome).stream().map(releaseGateMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReleaseGateResponseDTO> getGatesByStatus(GateStatus status) {
        return releaseGateRepository.findByStatus(status).stream().map(releaseGateMapper::toResponse).toList();
    }

    private ReleaseGate findOrThrow(Long id) {
        return releaseGateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Release gate not found with ID: " + id));
    }
}
