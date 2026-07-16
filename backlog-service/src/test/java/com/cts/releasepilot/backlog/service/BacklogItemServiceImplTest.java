package com.cts.releasepilot.backlog.service;

import com.cts.releasepilot.backlog.client.ProductClientService;
import com.cts.releasepilot.backlog.common.BacklogStatus;
import com.cts.releasepilot.backlog.common.BacklogType;
import com.cts.releasepilot.backlog.common.Priority;
import com.cts.releasepilot.backlog.dto.BacklogItemRequestDTO;
import com.cts.releasepilot.backlog.dto.BacklogItemResponseDTO;
import com.cts.releasepilot.backlog.dto.ProductDTO;
import com.cts.releasepilot.backlog.entity.BacklogItem;
import com.cts.releasepilot.backlog.exception.ResourceNotFoundException;
import com.cts.releasepilot.backlog.mapper.BacklogItemMapper;
import com.cts.releasepilot.backlog.repository.BacklogItemRepository;
import com.cts.releasepilot.backlog.service.impl.BacklogItemServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BacklogItemServiceImplTest {

    @Mock private BacklogItemRepository backlogItemRepository;
    @Mock private BacklogItemMapper backlogItemMapper;
    @Mock private ProductClientService productClientService;
    @InjectMocks private BacklogItemServiceImpl backlogItemService;

    private BacklogItem item;

    @BeforeEach
    void setUp() {
        item = new BacklogItem(1L, 10L, "Add login", BacklogType.Feature, Priority.High,
                5, 2L, 3L, BacklogStatus.New);
    }

    @Test
    void getBacklogItemById_found() {
        when(backlogItemRepository.findById(1L)).thenReturn(Optional.of(item));
        when(backlogItemMapper.toResponse(item)).thenReturn(new BacklogItemResponseDTO());
        assertNotNull(backlogItemService.getBacklogItemById(1L));
    }

    @Test
    void getBacklogItemById_notFound_throws() {
        when(backlogItemRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> backlogItemService.getBacklogItemById(99L));
    }

    @Test
    void createBacklogItem_success() {
        BacklogItemRequestDTO dto = new BacklogItemRequestDTO();
        dto.setProductID(10L);
        dto.setTitle("Add login");
        dto.setType(BacklogType.Feature);
        dto.setPriority(Priority.High);

        when(productClientService.getProduct(10L)).thenReturn(new ProductDTO(10L, "Portal", "ACTIVE"));
        when(backlogItemMapper.toEntity(dto)).thenReturn(item);
        when(backlogItemRepository.save(item)).thenReturn(item);
        when(backlogItemMapper.toResponse(item)).thenReturn(new BacklogItemResponseDTO());

        assertNotNull(backlogItemService.createBacklogItem(dto));
        verify(backlogItemRepository).save(item);
    }

    @Test
    void createBacklogItem_productServiceDegraded_stillSaves() {
        BacklogItemRequestDTO dto = new BacklogItemRequestDTO();
        dto.setProductID(10L);
        dto.setTitle("Add login");
        dto.setType(BacklogType.Feature);
        dto.setPriority(Priority.High);

        // Circuit breaker fallback already produced a degraded product response.
        when(productClientService.getProduct(10L)).thenReturn(new ProductDTO(10L, "Unavailable", "UNKNOWN"));
        when(backlogItemMapper.toEntity(dto)).thenReturn(item);
        when(backlogItemRepository.save(item)).thenReturn(item);
        when(backlogItemMapper.toResponse(item)).thenReturn(new BacklogItemResponseDTO());

        assertNotNull(backlogItemService.createBacklogItem(dto));
        verify(backlogItemRepository).save(item);
    }
}
