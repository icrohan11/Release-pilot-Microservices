package com.cts.releasepilot.release.mapper;

import com.cts.releasepilot.release.dto.PatchDependencyRequestDTO;
import com.cts.releasepilot.release.dto.PatchDependencyResponseDTO;
import com.cts.releasepilot.release.entity.PatchDependency;
import org.springframework.stereotype.Component;

/** Converts between {@link PatchDependency} entities and their DTOs. */
@Component
public class PatchDependencyMapper {

    public PatchDependency toEntity(PatchDependencyRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        PatchDependency entity = new PatchDependency();
        entity.setReleaseID(dto.getReleaseID());
        entity.setDependsOnReleaseID(dto.getDependsOnReleaseID());
        entity.setDependencyType(dto.getDependencyType());
        entity.setNotes(dto.getNotes());
        return entity;
    }

    public void updateEntity(PatchDependency entity, PatchDependencyRequestDTO dto) {
        entity.setReleaseID(dto.getReleaseID());
        entity.setDependsOnReleaseID(dto.getDependsOnReleaseID());
        entity.setDependencyType(dto.getDependencyType());
        entity.setNotes(dto.getNotes());
    }

    public PatchDependencyResponseDTO toResponse(PatchDependency entity) {
        if (entity == null) {
            return null;
        }
        PatchDependencyResponseDTO dto = new PatchDependencyResponseDTO();
        dto.setDependencyID(entity.getDependencyID());
        dto.setReleaseID(entity.getReleaseID());
        dto.setDependsOnReleaseID(entity.getDependsOnReleaseID());
        dto.setDependencyType(entity.getDependencyType());
        dto.setNotes(entity.getNotes());
        return dto;
    }
}
