package com.cts.releasepilot.communication.mapper;

import com.cts.releasepilot.communication.dto.CustomerAdvisoryRequestDTO;
import com.cts.releasepilot.communication.dto.CustomerAdvisoryResponseDTO;
import com.cts.releasepilot.communication.entity.CustomerAdvisory;
import org.springframework.stereotype.Component;

/** Converts between {@link CustomerAdvisory} entities and their DTOs. */
@Component
public class CustomerAdvisoryMapper {

    public CustomerAdvisory toEntity(CustomerAdvisoryRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        CustomerAdvisory advisory = new CustomerAdvisory();
        advisory.setReleaseID(dto.getReleaseID());
        advisory.setTitle(dto.getTitle());
        advisory.setSeverity(dto.getSeverity());
        advisory.setAffectedVersions(dto.getAffectedVersions());
        advisory.setResolution(dto.getResolution());
        advisory.setPublishedDate(dto.getPublishedDate());
        advisory.setStatus(dto.getStatus());
        return advisory;
    }

    /** Apply a request DTO onto an existing entity. */
    public void updateEntity(CustomerAdvisory advisory, CustomerAdvisoryRequestDTO dto) {
        advisory.setReleaseID(dto.getReleaseID());
        advisory.setTitle(dto.getTitle());
        advisory.setSeverity(dto.getSeverity());
        advisory.setAffectedVersions(dto.getAffectedVersions());
        advisory.setResolution(dto.getResolution());
        advisory.setPublishedDate(dto.getPublishedDate());
        advisory.setStatus(dto.getStatus());
    }

    public CustomerAdvisoryResponseDTO toResponse(CustomerAdvisory advisory) {
        if (advisory == null) {
            return null;
        }
        CustomerAdvisoryResponseDTO dto = new CustomerAdvisoryResponseDTO();
        dto.setAdvisoryID(advisory.getAdvisoryID());
        dto.setReleaseID(advisory.getReleaseID());
        dto.setTitle(advisory.getTitle());
        dto.setSeverity(advisory.getSeverity());
        dto.setAffectedVersions(advisory.getAffectedVersions());
        dto.setResolution(advisory.getResolution());
        dto.setPublishedDate(advisory.getPublishedDate());
        dto.setStatus(advisory.getStatus());
        return dto;
    }
}
