package com.cts.releasepilot.product.service.impl;

import com.cts.releasepilot.product.common.MilestoneStatus;
import com.cts.releasepilot.product.dto.RoadmapMilestoneRequestDTO;
import com.cts.releasepilot.product.dto.RoadmapMilestoneResponseDTO;
import com.cts.releasepilot.product.entity.RoadmapMilestone;
import com.cts.releasepilot.product.exception.ResourceNotFoundException;
import com.cts.releasepilot.product.mapper.RoadmapMilestoneMapper;
import com.cts.releasepilot.product.repository.RoadmapMilestoneRepository;
import com.cts.releasepilot.product.service.RoadmapMilestoneService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoadmapMilestoneServiceImpl implements RoadmapMilestoneService {

    private final RoadmapMilestoneRepository milestoneRepository;
    private final RoadmapMilestoneMapper milestoneMapper;

    public RoadmapMilestoneServiceImpl(RoadmapMilestoneRepository milestoneRepository,
                                       RoadmapMilestoneMapper milestoneMapper) {
        this.milestoneRepository = milestoneRepository;
        this.milestoneMapper = milestoneMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoadmapMilestoneResponseDTO> getAllMilestones() {
        return milestoneRepository.findAll().stream().map(milestoneMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public RoadmapMilestoneResponseDTO getMilestoneById(Long id) {
        return milestoneMapper.toResponse(findOrThrow(id));
    }

    @Override
    @Transactional
    public RoadmapMilestoneResponseDTO saveMilestone(RoadmapMilestoneRequestDTO dto) {
        RoadmapMilestone milestone = milestoneMapper.toEntity(dto);
        return milestoneMapper.toResponse(milestoneRepository.save(milestone));
    }

    @Override
    @Transactional
    public RoadmapMilestoneResponseDTO updateMilestone(Long id, RoadmapMilestoneRequestDTO dto) {
        RoadmapMilestone milestone = findOrThrow(id);
        milestoneMapper.updateEntity(milestone, dto);
        return milestoneMapper.toResponse(milestoneRepository.save(milestone));
    }

    @Override
    @Transactional
    public void deleteMilestone(Long id) {
        RoadmapMilestone milestone = findOrThrow(id);
        milestoneRepository.delete(milestone);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoadmapMilestoneResponseDTO> getMilestonesByProduct(Long productID) {
        return milestoneRepository.findByProductID(productID).stream().map(milestoneMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoadmapMilestoneResponseDTO> getMilestonesByStatus(MilestoneStatus status) {
        return milestoneRepository.findByStatus(status).stream().map(milestoneMapper::toResponse).toList();
    }

    private RoadmapMilestone findOrThrow(Long id) {
        return milestoneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Milestone not found with ID: " + id));
    }
}
