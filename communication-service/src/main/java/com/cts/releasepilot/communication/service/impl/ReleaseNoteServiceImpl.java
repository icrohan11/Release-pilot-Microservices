package com.cts.releasepilot.communication.service.impl;

import com.cts.releasepilot.communication.client.ReleaseClientService;
import com.cts.releasepilot.communication.common.NoteStatus;
import com.cts.releasepilot.communication.dto.ReleaseDTO;
import com.cts.releasepilot.communication.dto.ReleaseNoteRequestDTO;
import com.cts.releasepilot.communication.dto.ReleaseNoteResponseDTO;
import com.cts.releasepilot.communication.entity.ReleaseNote;
import com.cts.releasepilot.communication.exception.ResourceNotFoundException;
import com.cts.releasepilot.communication.mapper.ReleaseNoteMapper;
import com.cts.releasepilot.communication.repository.ReleaseNoteRepository;
import com.cts.releasepilot.communication.service.ReleaseNoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReleaseNoteServiceImpl implements ReleaseNoteService {

    private static final Logger log = LoggerFactory.getLogger(ReleaseNoteServiceImpl.class);

    private final ReleaseNoteRepository releaseNoteRepository;
    private final ReleaseNoteMapper releaseNoteMapper;
    private final ReleaseClientService releaseClientService;

    public ReleaseNoteServiceImpl(ReleaseNoteRepository releaseNoteRepository,
                                  ReleaseNoteMapper releaseNoteMapper,
                                  ReleaseClientService releaseClientService) {
        this.releaseNoteRepository = releaseNoteRepository;
        this.releaseNoteMapper = releaseNoteMapper;
        this.releaseClientService = releaseClientService;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReleaseNoteResponseDTO> getAllReleaseNotes() {
        return releaseNoteRepository.findAll().stream().map(releaseNoteMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ReleaseNoteResponseDTO getReleaseNoteById(Long id) {
        return releaseNoteMapper.toResponse(findOrThrow(id));
    }

    @Override
    @Transactional
    public ReleaseNoteResponseDTO createReleaseNote(ReleaseNoteRequestDTO dto) {
        // Validate the referenced release via the release-service (circuit-breaker protected).
        ReleaseDTO release = releaseClientService.getRelease(dto.getReleaseID());
        log.info("Creating release note for release {} (version {})",
                dto.getReleaseID(), release.getVersionNumber());

        ReleaseNote note = releaseNoteMapper.toEntity(dto);
        note.setNoteID(null);
        if (note.getStatus() == null) {
            note.setStatus(NoteStatus.Draft);
        }
        return releaseNoteMapper.toResponse(releaseNoteRepository.save(note));
    }

    @Override
    @Transactional
    public ReleaseNoteResponseDTO updateReleaseNote(Long id, ReleaseNoteRequestDTO dto) {
        ReleaseNote note = findOrThrow(id);
        releaseNoteMapper.updateEntity(note, dto);
        return releaseNoteMapper.toResponse(releaseNoteRepository.save(note));
    }

    @Override
    @Transactional
    public void deleteReleaseNote(Long id) {
        ReleaseNote note = findOrThrow(id);
        releaseNoteRepository.delete(note);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReleaseNoteResponseDTO> getReleaseNotesByRelease(Long releaseID) {
        return releaseNoteRepository.findByReleaseID(releaseID).stream()
                .map(releaseNoteMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReleaseNoteResponseDTO> getReleaseNotesByStatus(NoteStatus status) {
        return releaseNoteRepository.findByStatus(status).stream()
                .map(releaseNoteMapper::toResponse).toList();
    }

    private ReleaseNote findOrThrow(Long id) {
        return releaseNoteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Release note not found with ID: " + id));
    }
}
