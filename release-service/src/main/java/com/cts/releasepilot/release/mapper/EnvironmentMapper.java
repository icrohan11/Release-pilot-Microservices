package com.cts.releasepilot.release.mapper;

import com.cts.releasepilot.release.dto.EnvironmentRequestDTO;
import com.cts.releasepilot.release.dto.EnvironmentResponseDTO;
import com.cts.releasepilot.release.entity.Environment;
import org.springframework.stereotype.Component;

/** Converts between {@link Environment} entities and their DTOs. */
@Component
public class EnvironmentMapper {

    public Environment toEntity(EnvironmentRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        Environment entity = new Environment();
        entity.setProductID(dto.getProductID());
        entity.setEnvName(dto.getEnvName());
        entity.setOwnerID(dto.getOwnerID());
        entity.setCurrentVersion(dto.getCurrentVersion());
        entity.setLastPromotionDate(dto.getLastPromotionDate());
        entity.setStatus(dto.getStatus());
        return entity;
    }

    public void updateEntity(Environment entity, EnvironmentRequestDTO dto) {
        entity.setProductID(dto.getProductID());
        entity.setEnvName(dto.getEnvName());
        entity.setOwnerID(dto.getOwnerID());
        entity.setCurrentVersion(dto.getCurrentVersion());
        entity.setLastPromotionDate(dto.getLastPromotionDate());
        entity.setStatus(dto.getStatus());
    }

    public EnvironmentResponseDTO toResponse(Environment entity) {
        if (entity == null) {
            return null;
        }
        EnvironmentResponseDTO dto = new EnvironmentResponseDTO();
        dto.setEnvID(entity.getEnvID());
        dto.setProductID(entity.getProductID());
        dto.setEnvName(entity.getEnvName());
        dto.setOwnerID(entity.getOwnerID());
        dto.setCurrentVersion(entity.getCurrentVersion());
        dto.setLastPromotionDate(entity.getLastPromotionDate());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}
