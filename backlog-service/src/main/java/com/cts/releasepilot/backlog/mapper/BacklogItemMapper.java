package com.cts.releasepilot.backlog.mapper;

import com.cts.releasepilot.backlog.dto.BacklogItemRequestDTO;
import com.cts.releasepilot.backlog.dto.BacklogItemResponseDTO;
import com.cts.releasepilot.backlog.entity.BacklogItem;
import org.springframework.stereotype.Component;

/** Converts between {@link BacklogItem} entities and their DTOs. */
@Component
public class BacklogItemMapper {

    public BacklogItem toEntity(BacklogItemRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        BacklogItem item = new BacklogItem();
        item.setProductID(dto.getProductID());
        item.setTitle(dto.getTitle());
        item.setType(dto.getType());
        item.setPriority(dto.getPriority());
        item.setStoryPoints(dto.getStoryPoints());
        item.setRequestedByID(dto.getRequestedByID());
        item.setMilestoneID(dto.getMilestoneID());
        item.setStatus(dto.getStatus());
        return item;
    }

    /** Apply an update DTO onto an existing entity. */
    public void updateEntity(BacklogItem item, BacklogItemRequestDTO dto) {
        item.setProductID(dto.getProductID());
        item.setTitle(dto.getTitle());
        item.setType(dto.getType());
        item.setPriority(dto.getPriority());
        item.setStoryPoints(dto.getStoryPoints());
        item.setRequestedByID(dto.getRequestedByID());
        item.setMilestoneID(dto.getMilestoneID());
        item.setStatus(dto.getStatus());
    }

    public BacklogItemResponseDTO toResponse(BacklogItem item) {
        if (item == null) {
            return null;
        }
        BacklogItemResponseDTO dto = new BacklogItemResponseDTO();
        dto.setItemID(item.getItemID());
        dto.setProductID(item.getProductID());
        dto.setTitle(item.getTitle());
        dto.setType(item.getType());
        dto.setPriority(item.getPriority());
        dto.setStoryPoints(item.getStoryPoints());
        dto.setRequestedByID(item.getRequestedByID());
        dto.setMilestoneID(item.getMilestoneID());
        dto.setStatus(item.getStatus());
        return dto;
    }
}
