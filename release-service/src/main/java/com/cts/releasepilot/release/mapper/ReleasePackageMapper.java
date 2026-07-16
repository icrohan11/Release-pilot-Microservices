package com.cts.releasepilot.release.mapper;

import com.cts.releasepilot.release.dto.ReleasePackageRequestDTO;
import com.cts.releasepilot.release.dto.ReleasePackageResponseDTO;
import com.cts.releasepilot.release.entity.ReleasePackage;
import org.springframework.stereotype.Component;

/** Converts between {@link ReleasePackage} entities and their DTOs. */
@Component
public class ReleasePackageMapper {

    public ReleasePackage toEntity(ReleasePackageRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        ReleasePackage entity = new ReleasePackage();
        entity.setProductID(dto.getProductID());
        entity.setVersionNumber(dto.getVersionNumber());
        entity.setReleaseType(dto.getReleaseType());
        entity.setIncludedItemIDs(dto.getIncludedItemIDs());
        entity.setReleaseNotesDraft(dto.getReleaseNotesDraft());
        entity.setTargetReleaseDate(dto.getTargetReleaseDate());
        entity.setReleasedByID(dto.getReleasedByID());
        entity.setStatus(dto.getStatus());
        return entity;
    }

    public void updateEntity(ReleasePackage entity, ReleasePackageRequestDTO dto) {
        entity.setProductID(dto.getProductID());
        entity.setVersionNumber(dto.getVersionNumber());
        entity.setReleaseType(dto.getReleaseType());
        entity.setIncludedItemIDs(dto.getIncludedItemIDs());
        entity.setReleaseNotesDraft(dto.getReleaseNotesDraft());
        entity.setTargetReleaseDate(dto.getTargetReleaseDate());
        entity.setReleasedByID(dto.getReleasedByID());
        entity.setStatus(dto.getStatus());
    }

    public ReleasePackageResponseDTO toResponse(ReleasePackage entity) {
        if (entity == null) {
            return null;
        }
        ReleasePackageResponseDTO dto = new ReleasePackageResponseDTO();
        dto.setReleaseID(entity.getReleaseID());
        dto.setProductID(entity.getProductID());
        dto.setVersionNumber(entity.getVersionNumber());
        dto.setReleaseType(entity.getReleaseType());
        dto.setIncludedItemIDs(entity.getIncludedItemIDs());
        dto.setReleaseNotesDraft(entity.getReleaseNotesDraft());
        dto.setTargetReleaseDate(entity.getTargetReleaseDate());
        dto.setReleasedByID(entity.getReleasedByID());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}
