package com.cts.releasepilot.release.mapper;

import com.cts.releasepilot.release.dto.ChangeFreezeWindowRequestDTO;
import com.cts.releasepilot.release.dto.ChangeFreezeWindowResponseDTO;
import com.cts.releasepilot.release.entity.ChangeFreezeWindow;
import org.springframework.stereotype.Component;

/** Converts between {@link ChangeFreezeWindow} entities and their DTOs. */
@Component
public class ChangeFreezeWindowMapper {

    public ChangeFreezeWindow toEntity(ChangeFreezeWindowRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        ChangeFreezeWindow entity = new ChangeFreezeWindow();
        entity.setProductID(dto.getProductID());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setReason(dto.getReason());
        entity.setApprovedByID(dto.getApprovedByID());
        entity.setStatus(dto.getStatus());
        return entity;
    }

    public void updateEntity(ChangeFreezeWindow entity, ChangeFreezeWindowRequestDTO dto) {
        entity.setProductID(dto.getProductID());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setReason(dto.getReason());
        entity.setApprovedByID(dto.getApprovedByID());
        entity.setStatus(dto.getStatus());
    }

    public ChangeFreezeWindowResponseDTO toResponse(ChangeFreezeWindow entity) {
        if (entity == null) {
            return null;
        }
        ChangeFreezeWindowResponseDTO dto = new ChangeFreezeWindowResponseDTO();
        dto.setFreezeID(entity.getFreezeID());
        dto.setProductID(entity.getProductID());
        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());
        dto.setReason(entity.getReason());
        dto.setApprovedByID(entity.getApprovedByID());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}
