package com.cts.releasepilot.backlog.service;

import com.cts.releasepilot.backlog.common.SprintItemStatus;
import com.cts.releasepilot.backlog.dto.SprintItemRequestDTO;
import com.cts.releasepilot.backlog.dto.SprintItemResponseDTO;
import com.cts.releasepilot.backlog.entity.SprintItem;
import com.cts.releasepilot.backlog.exception.ResourceNotFoundException;
import com.cts.releasepilot.backlog.mapper.SprintItemMapper;
import com.cts.releasepilot.backlog.repository.SprintItemRepository;
import com.cts.releasepilot.backlog.service.impl.SprintItemServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SprintItemServiceImplTest {

    @Mock private SprintItemRepository sprintItemRepository;
    @Mock private SprintItemMapper sprintItemMapper;
    @InjectMocks private SprintItemServiceImpl sprintItemService;

    private SprintItem item;

    @BeforeEach
    void setUp() {
        item = new SprintItem(1L, 1L, 2L, 3L, 8.0, 4.0, SprintItemStatus.ToDo);
    }

    @Test
    void getSprintItemById_found() {
        when(sprintItemRepository.findById(1L)).thenReturn(Optional.of(item));
        when(sprintItemMapper.toResponse(item)).thenReturn(new SprintItemResponseDTO());
        assertNotNull(sprintItemService.getSprintItemById(1L));
    }

    @Test
    void getSprintItemById_notFound_throws() {
        when(sprintItemRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> sprintItemService.getSprintItemById(99L));
    }

    @Test
    void createSprintItem_success() {
        SprintItemRequestDTO dto = new SprintItemRequestDTO();
        dto.setSprintID(1L);
        dto.setBacklogItemID(2L);

        when(sprintItemMapper.toEntity(dto)).thenReturn(item);
        when(sprintItemRepository.save(item)).thenReturn(item);
        when(sprintItemMapper.toResponse(item)).thenReturn(new SprintItemResponseDTO());

        assertNotNull(sprintItemService.createSprintItem(dto));
        verify(sprintItemRepository).save(item);
    }

    @Test
    void deleteSprintItem_notFound_throws() {
        when(sprintItemRepository.findById(5L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> sprintItemService.deleteSprintItem(5L));
        verify(sprintItemRepository, never()).delete(any());
    }
}
