package com.cts.releasepilot.communication.mapper;

import com.cts.releasepilot.communication.dto.ReleaseNoteRequestDTO;
import com.cts.releasepilot.communication.dto.ReleaseNoteResponseDTO;
import com.cts.releasepilot.communication.entity.ReleaseNote;
import org.springframework.stereotype.Component;

/** Converts between {@link ReleaseNote} entities and their DTOs. */
@Component
public class ReleaseNoteMapper {

    public ReleaseNote toEntity(ReleaseNoteRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        ReleaseNote note = new ReleaseNote();
        note.setReleaseID(dto.getReleaseID());
        note.setVersionNumber(dto.getVersionNumber());
        note.setSummary(dto.getSummary());
        note.setNewFeatures(dto.getNewFeatures());
        note.setBugFixes(dto.getBugFixes());
        note.setKnownIssues(dto.getKnownIssues());
        note.setDeprecations(dto.getDeprecations());
        note.setUpgradeInstructions(dto.getUpgradeInstructions());
        note.setAuthoredByID(dto.getAuthoredByID());
        note.setStatus(dto.getStatus());
        return note;
    }

    /** Apply a request DTO onto an existing entity. */
    public void updateEntity(ReleaseNote note, ReleaseNoteRequestDTO dto) {
        note.setReleaseID(dto.getReleaseID());
        note.setVersionNumber(dto.getVersionNumber());
        note.setSummary(dto.getSummary());
        note.setNewFeatures(dto.getNewFeatures());
        note.setBugFixes(dto.getBugFixes());
        note.setKnownIssues(dto.getKnownIssues());
        note.setDeprecations(dto.getDeprecations());
        note.setUpgradeInstructions(dto.getUpgradeInstructions());
        note.setAuthoredByID(dto.getAuthoredByID());
        note.setStatus(dto.getStatus());
    }

    public ReleaseNoteResponseDTO toResponse(ReleaseNote note) {
        if (note == null) {
            return null;
        }
        ReleaseNoteResponseDTO dto = new ReleaseNoteResponseDTO();
        dto.setNoteID(note.getNoteID());
        dto.setReleaseID(note.getReleaseID());
        dto.setVersionNumber(note.getVersionNumber());
        dto.setSummary(note.getSummary());
        dto.setNewFeatures(note.getNewFeatures());
        dto.setBugFixes(note.getBugFixes());
        dto.setKnownIssues(note.getKnownIssues());
        dto.setDeprecations(note.getDeprecations());
        dto.setUpgradeInstructions(note.getUpgradeInstructions());
        dto.setAuthoredByID(note.getAuthoredByID());
        dto.setStatus(note.getStatus());
        return dto;
    }
}
