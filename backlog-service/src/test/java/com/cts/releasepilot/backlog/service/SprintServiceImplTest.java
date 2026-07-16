package com.cts.releasepilot.backlog.service;

import com.cts.releasepilot.backlog.common.SprintStatus;
import com.cts.releasepilot.backlog.dto.SprintRequestDTO;
import com.cts.releasepilot.backlog.dto.SprintResponseDTO;
import com.cts.releasepilot.backlog.entity.Sprint;
import com.cts.releasepilot.backlog.exception.ResourceNotFoundException;
import com.cts.releasepilot.backlog.mapper.SprintMapper;
import com.cts.releasepilot.backlog.repository.SprintRepository;
import com.cts.releasepilot.backlog.service.impl.SprintServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SprintServiceImplTest {

    @Mock private SprintRepository sprintRepository;
    @Mock private SprintMapper sprintMapper;
    @InjectMocks private SprintServiceImpl sprintService;

    private Sprint sprint;

    @BeforeEach
    void setUp() {
        sprint = new Sprint(1L, 10L, "Sprint 1", LocalDate.now(), LocalDate.now().plusDays(14),
                "Ship login", 20, 0, SprintStatus.Planned);
    }

    @Test
    void getSprintById_found() {
        when(sprintRepository.findById(1L)).thenReturn(Optional.of(sprint));
        when(sprintMapper.toResponse(sprint)).thenReturn(new SprintResponseDTO());
        assertNotNull(sprintService.getSprintById(1L));
    }

    @Test
    void getSprintById_notFound_throws() {
        when(sprintRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> sprintService.getSprintById(99L));
    }

    @Test
    void createSprint_success() {
        SprintRequestDTO dto = new SprintRequestDTO();
        dto.setProductID(10L);
        dto.setSprintName("Sprint 1");

        when(sprintMapper.toEntity(dto)).thenReturn(sprint);
        when(sprintRepository.save(sprint)).thenReturn(sprint);
        when(sprintMapper.toResponse(sprint)).thenReturn(new SprintResponseDTO());

        assertNotNull(sprintService.createSprint(dto));
        verify(sprintRepository).save(sprint);
    }

    @Test
    void deleteSprint_notFound_throws() {
        when(sprintRepository.findById(5L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> sprintService.deleteSprint(5L));
        verify(sprintRepository, never()).delete(any());
    }
}
