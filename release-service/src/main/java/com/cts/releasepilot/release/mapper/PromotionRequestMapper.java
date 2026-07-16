package com.cts.releasepilot.release.mapper;

import com.cts.releasepilot.release.dto.PromotionRequestRequestDTO;
import com.cts.releasepilot.release.dto.PromotionRequestResponseDTO;
import com.cts.releasepilot.release.entity.PromotionRequest;
import org.springframework.stereotype.Component;

/** Converts between {@link PromotionRequest} entities and their DTOs. */
@Component
public class PromotionRequestMapper {

    public PromotionRequest toEntity(PromotionRequestRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        PromotionRequest entity = new PromotionRequest();
        entity.setReleaseID(dto.getReleaseID());
        entity.setFromEnvID(dto.getFromEnvID());
        entity.setToEnvID(dto.getToEnvID());
        entity.setRequestedByID(dto.getRequestedByID());
        entity.setApprovedByID(dto.getApprovedByID());
        entity.setScheduledDateTime(dto.getScheduledDateTime());
        entity.setActualDateTime(dto.getActualDateTime());
        entity.setStatus(dto.getStatus());
        return entity;
    }

    public void updateEntity(PromotionRequest entity, PromotionRequestRequestDTO dto) {
        entity.setReleaseID(dto.getReleaseID());
        entity.setFromEnvID(dto.getFromEnvID());
        entity.setToEnvID(dto.getToEnvID());
        entity.setRequestedByID(dto.getRequestedByID());
        entity.setApprovedByID(dto.getApprovedByID());
        entity.setScheduledDateTime(dto.getScheduledDateTime());
        entity.setActualDateTime(dto.getActualDateTime());
        entity.setStatus(dto.getStatus());
    }

    public PromotionRequestResponseDTO toResponse(PromotionRequest entity) {
        if (entity == null) {
            return null;
        }
        PromotionRequestResponseDTO dto = new PromotionRequestResponseDTO();
        dto.setPromotionID(entity.getPromotionID());
        dto.setReleaseID(entity.getReleaseID());
        dto.setFromEnvID(entity.getFromEnvID());
        dto.setToEnvID(entity.getToEnvID());
        dto.setRequestedByID(entity.getRequestedByID());
        dto.setApprovedByID(entity.getApprovedByID());
        dto.setScheduledDateTime(entity.getScheduledDateTime());
        dto.setActualDateTime(entity.getActualDateTime());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}
