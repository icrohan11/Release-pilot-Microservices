package com.cts.releasepilot.communication.service;

import com.cts.releasepilot.communication.client.ReleaseClientService;
import com.cts.releasepilot.communication.common.NoteStatus;
import com.cts.releasepilot.communication.dto.ReleaseDTO;
import com.cts.releasepilot.communication.dto.ReleaseNoteRequestDTO;
import com.cts.releasepilot.communication.dto.ReleaseNoteResponseDTO;
import com.cts.releasepilot.communication.entity.ReleaseNote;
import com.cts.releasepilot.communication.exception.ResourceNotFoundException;
import com.cts.releasepilot.communication.mapper.ReleaseNoteMapper;
import com.cts.releasepilot.communication.repository.ReleaseNoteRepository;
import com.cts.releasepilot.communication.service.impl.ReleaseNoteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReleaseNoteServiceImplTest {

    @Mock private ReleaseNoteRepository releaseNoteRepository;
    @Mock private ReleaseNoteMapper releaseNoteMapper;
    @Mock private ReleaseClientService releaseClientService;
    @InjectMocks private ReleaseNoteServiceImpl releaseNoteService;

    private ReleaseNote note;

    @BeforeEach
    void setUp() {
        note = new ReleaseNote(1L, 100L, "v1.0", "summary", null, null,
                null, null, null, 5L, NoteStatus.Draft);
    }

    @Test
    void getReleaseNoteById_found() {
        when(releaseNoteRepository.findById(1L)).thenReturn(Optional.of(note));
        when(releaseNoteMapper.toResponse(note)).thenReturn(new ReleaseNoteResponseDTO());
        assertNotNull(releaseNoteService.getReleaseNoteById(1L));
    }

    @Test
    void getReleaseNoteById_notFound_throws() {
        when(releaseNoteRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> releaseNoteService.getReleaseNoteById(99L));
    }

    @Test
    void createReleaseNote_validatesReleaseAndSaves() {
        ReleaseNoteRequestDTO dto = new ReleaseNoteRequestDTO();
        dto.setReleaseID(100L);
        dto.setVersionNumber("v1.0");

        when(releaseClientService.getRelease(100L)).thenReturn(new ReleaseDTO(100L, "v1.0", "Released"));
        when(releaseNoteMapper.toEntity(dto)).thenReturn(note);
        when(releaseNoteRepository.save(note)).thenReturn(note);
        when(releaseNoteMapper.toResponse(note)).thenReturn(new ReleaseNoteResponseDTO());

        assertNotNull(releaseNoteService.createReleaseNote(dto));
        verify(releaseClientService).getRelease(100L);
        verify(releaseNoteRepository).save(note);
    }

    @Test
    void createReleaseNote_releaseServiceDegraded_stillSaves() {
        ReleaseNoteRequestDTO dto = new ReleaseNoteRequestDTO();
        dto.setReleaseID(100L);
        dto.setVersionNumber("v1.0");

        // Circuit breaker fallback already produced a degraded release response.
        when(releaseClientService.getRelease(100L)).thenReturn(new ReleaseDTO(100L, "UNKNOWN", "UNAVAILABLE"));
        when(releaseNoteMapper.toEntity(dto)).thenReturn(note);
        when(releaseNoteRepository.save(note)).thenReturn(note);
        when(releaseNoteMapper.toResponse(note)).thenReturn(new ReleaseNoteResponseDTO());

        assertNotNull(releaseNoteService.createReleaseNote(dto));
        verify(releaseNoteRepository).save(note);
    }

    @Test
    void getAllReleaseNotes_returnsMappedList() {
        when(releaseNoteRepository.findAll()).thenReturn(List.of(note));
        when(releaseNoteMapper.toResponse(note)).thenReturn(new ReleaseNoteResponseDTO());
        assertEquals(1, releaseNoteService.getAllReleaseNotes().size());
    }
}
