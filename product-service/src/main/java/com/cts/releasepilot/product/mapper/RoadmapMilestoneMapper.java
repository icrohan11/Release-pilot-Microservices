package com.cts.releasepilot.product.mapper;

import com.cts.releasepilot.product.dto.RoadmapMilestoneRequestDTO;
import com.cts.releasepilot.product.dto.RoadmapMilestoneResponseDTO;
import com.cts.releasepilot.product.entity.RoadmapMilestone;
import org.springframework.stereotype.Component;

/** Converts between {@link RoadmapMilestone} entities and their DTOs. */
@Component
public class RoadmapMilestoneMapper {

    /** Build a new entity from a request DTO. */
    public RoadmapMilestone toEntity(RoadmapMilestoneRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        RoadmapMilestone milestone = new RoadmapMilestone();
        milestone.setProductID(dto.getProductID());
        milestone.setMilestoneName(dto.getMilestoneName());
        milestone.setTargetQuarter(dto.getTargetQuarter());
        milestone.setStrategicTheme(dto.getStrategicTheme());
        milestone.setExpectedVersion(dto.getExpectedVersion());
        milestone.setStatus(dto.getStatus());
        return milestone;
    }

    /** Apply an update DTO onto an existing entity. */
    public void updateEntity(RoadmapMilestone milestone, RoadmapMilestoneRequestDTO dto) {
        milestone.setProductID(dto.getProductID());
        milestone.setMilestoneName(dto.getMilestoneName());
        milestone.setTargetQuarter(dto.getTargetQuarter());
        milestone.setStrategicTheme(dto.getStrategicTheme());
        milestone.setExpectedVersion(dto.getExpectedVersion());
        milestone.setStatus(dto.getStatus());
    }

    public RoadmapMilestoneResponseDTO toResponse(RoadmapMilestone milestone) {
        if (milestone == null) {
            return null;
        }
        RoadmapMilestoneResponseDTO dto = new RoadmapMilestoneResponseDTO();
        dto.setMilestoneID(milestone.getMilestoneID());
        dto.setProductID(milestone.getProductID());
        dto.setMilestoneName(milestone.getMilestoneName());
        dto.setTargetQuarter(milestone.getTargetQuarter());
        dto.setStrategicTheme(milestone.getStrategicTheme());
        dto.setExpectedVersion(milestone.getExpectedVersion());
        dto.setStatus(milestone.getStatus());
        return dto;
    }
}
