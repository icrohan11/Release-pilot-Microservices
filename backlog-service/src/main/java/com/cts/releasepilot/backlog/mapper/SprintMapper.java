package com.cts.releasepilot.backlog.mapper;

import com.cts.releasepilot.backlog.dto.SprintRequestDTO;
import com.cts.releasepilot.backlog.dto.SprintResponseDTO;
import com.cts.releasepilot.backlog.entity.Sprint;
import org.springframework.stereotype.Component;

/** Converts between {@link Sprint} entities and their DTOs. */
@Component
public class SprintMapper {

    public Sprint toEntity(SprintRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        Sprint sprint = new Sprint();
        sprint.setProductID(dto.getProductID());
        sprint.setSprintName(dto.getSprintName());
        sprint.setStartDate(dto.getStartDate());
        sprint.setEndDate(dto.getEndDate());
        sprint.setGoal(dto.getGoal());
        sprint.setTotalStoryPoints(dto.getTotalStoryPoints());
        sprint.setCompletedPoints(dto.getCompletedPoints());
        sprint.setStatus(dto.getStatus());
        return sprint;
    }

    /** Apply an update DTO onto an existing entity. */
    public void updateEntity(Sprint sprint, SprintRequestDTO dto) {
        sprint.setProductID(dto.getProductID());
        sprint.setSprintName(dto.getSprintName());
        sprint.setStartDate(dto.getStartDate());
        sprint.setEndDate(dto.getEndDate());
        sprint.setGoal(dto.getGoal());
        sprint.setTotalStoryPoints(dto.getTotalStoryPoints());
        sprint.setCompletedPoints(dto.getCompletedPoints());
        sprint.setStatus(dto.getStatus());
    }

    public SprintResponseDTO toResponse(Sprint sprint) {
        if (sprint == null) {
            return null;
        }
        SprintResponseDTO dto = new SprintResponseDTO();
        dto.setSprintID(sprint.getSprintID());
        dto.setProductID(sprint.getProductID());
        dto.setSprintName(sprint.getSprintName());
        dto.setStartDate(sprint.getStartDate());
        dto.setEndDate(sprint.getEndDate());
        dto.setGoal(sprint.getGoal());
        dto.setTotalStoryPoints(sprint.getTotalStoryPoints());
        dto.setCompletedPoints(sprint.getCompletedPoints());
        dto.setStatus(sprint.getStatus());
        return dto;
    }
}
