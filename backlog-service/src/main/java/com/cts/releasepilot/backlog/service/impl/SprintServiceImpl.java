package com.cts.releasepilot.backlog.service.impl;

import com.cts.releasepilot.backlog.common.SprintStatus;
import com.cts.releasepilot.backlog.dto.SprintRequestDTO;
import com.cts.releasepilot.backlog.dto.SprintResponseDTO;
import com.cts.releasepilot.backlog.entity.Sprint;
import com.cts.releasepilot.backlog.exception.ResourceNotFoundException;
import com.cts.releasepilot.backlog.mapper.SprintMapper;
import com.cts.releasepilot.backlog.repository.SprintRepository;
import com.cts.releasepilot.backlog.service.SprintService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SprintServiceImpl implements SprintService {

    private final SprintRepository sprintRepository;
    private final SprintMapper sprintMapper;

    public SprintServiceImpl(SprintRepository sprintRepository, SprintMapper sprintMapper) {
        this.sprintRepository = sprintRepository;
        this.sprintMapper = sprintMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SprintResponseDTO> getAllSprints() {
        return sprintRepository.findAll().stream().map(sprintMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public SprintResponseDTO getSprintById(Long id) {
        return sprintMapper.toResponse(findOrThrow(id));
    }

    @Override
    @Transactional
    public SprintResponseDTO createSprint(SprintRequestDTO dto) {
        Sprint sprint = sprintMapper.toEntity(dto);
        if (sprint.getStatus() == null) {
            sprint.setStatus(SprintStatus.Planned);
        }
        return sprintMapper.toResponse(sprintRepository.save(sprint));
    }

    @Override
    @Transactional
    public SprintResponseDTO updateSprint(Long id, SprintRequestDTO dto) {
        Sprint sprint = findOrThrow(id);
        sprintMapper.updateEntity(sprint, dto);
        return sprintMapper.toResponse(sprintRepository.save(sprint));
    }

    @Override
    @Transactional
    public void deleteSprint(Long id) {
        Sprint sprint = findOrThrow(id);
        sprintRepository.delete(sprint);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SprintResponseDTO> getSprintsByProduct(Long productID) {
        return sprintRepository.findByProductID(productID).stream().map(sprintMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<SprintResponseDTO> getSprintsByStatus(SprintStatus status) {
        return sprintRepository.findByStatus(status).stream().map(sprintMapper::toResponse).toList();
    }

    private Sprint findOrThrow(Long id) {
        return sprintRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sprint not found with ID: " + id));
    }
}
