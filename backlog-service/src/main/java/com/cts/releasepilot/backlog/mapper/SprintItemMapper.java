package com.cts.releasepilot.backlog.mapper;

import com.cts.releasepilot.backlog.dto.SprintItemRequestDTO;
import com.cts.releasepilot.backlog.dto.SprintItemResponseDTO;
import com.cts.releasepilot.backlog.entity.SprintItem;
import org.springframework.stereotype.Component;

/** Converts between {@link SprintItem} entities and their DTOs. */
@Component
public class SprintItemMapper {

    public SprintItem toEntity(SprintItemRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        SprintItem item = new SprintItem();
        item.setSprintID(dto.getSprintID());
        item.setBacklogItemID(dto.getBacklogItemID());
        item.setAssignedDevID(dto.getAssignedDevID());
        item.setEstimatedHours(dto.getEstimatedHours());
        item.setActualHours(dto.getActualHours());
        item.setStatus(dto.getStatus());
        return item;
    }

    /** Apply an update DTO onto an existing entity. */
    public void updateEntity(SprintItem item, SprintItemRequestDTO dto) {
        item.setSprintID(dto.getSprintID());
        item.setBacklogItemID(dto.getBacklogItemID());
        item.setAssignedDevID(dto.getAssignedDevID());
        item.setEstimatedHours(dto.getEstimatedHours());
        item.setActualHours(dto.getActualHours());
        item.setStatus(dto.getStatus());
    }

    public SprintItemResponseDTO toResponse(SprintItem item) {
        if (item == null) {
            return null;
        }
        SprintItemResponseDTO dto = new SprintItemResponseDTO();
        dto.setSprintItemID(item.getSprintItemID());
        dto.setSprintID(item.getSprintID());
        dto.setBacklogItemID(item.getBacklogItemID());
        dto.setAssignedDevID(item.getAssignedDevID());
        dto.setEstimatedHours(item.getEstimatedHours());
        dto.setActualHours(item.getActualHours());
        dto.setStatus(item.getStatus());
        return dto;
    }
}
