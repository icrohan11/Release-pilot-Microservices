package com.cts.releasepilot.communication.service;

import com.cts.releasepilot.communication.common.NoteStatus;
import com.cts.releasepilot.communication.dto.ReleaseNoteRequestDTO;
import com.cts.releasepilot.communication.dto.ReleaseNoteResponseDTO;

import java.util.List;

/** Release note operations. */
public interface ReleaseNoteService {

    List<ReleaseNoteResponseDTO> getAllReleaseNotes();

    ReleaseNoteResponseDTO getReleaseNoteById(Long id);

    ReleaseNoteResponseDTO createReleaseNote(ReleaseNoteRequestDTO dto);

    ReleaseNoteResponseDTO updateReleaseNote(Long id, ReleaseNoteRequestDTO dto);

    void deleteReleaseNote(Long id);

    List<ReleaseNoteResponseDTO> getReleaseNotesByRelease(Long releaseID);

    List<ReleaseNoteResponseDTO> getReleaseNotesByStatus(NoteStatus status);
}
