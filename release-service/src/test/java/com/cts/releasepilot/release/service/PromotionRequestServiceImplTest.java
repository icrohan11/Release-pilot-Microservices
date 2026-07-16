package com.cts.releasepilot.release.service;

import com.cts.releasepilot.release.common.PromotionStatus;
import com.cts.releasepilot.release.dto.PromotionRequestRequestDTO;
import com.cts.releasepilot.release.dto.PromotionRequestResponseDTO;
import com.cts.releasepilot.release.entity.PromotionRequest;
import com.cts.releasepilot.release.exception.ResourceNotFoundException;
import com.cts.releasepilot.release.mapper.PromotionRequestMapper;
import com.cts.releasepilot.release.repository.PromotionRequestRepository;
import com.cts.releasepilot.release.service.impl.PromotionRequestServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PromotionRequestServiceImplTest {

    @Mock private PromotionRequestRepository promotionRequestRepository;
    @Mock private PromotionRequestMapper promotionRequestMapper;
    @InjectMocks private PromotionRequestServiceImpl service;

    private PromotionRequest entity;

    @BeforeEach
    void setUp() {
        entity = new PromotionRequest(1L, 10L, 1L, 2L, 5L, 6L, null, null, PromotionStatus.Pending);
    }

    @Test
    void getById_found() {
        when(promotionRequestRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(promotionRequestMapper.toResponse(entity)).thenReturn(new PromotionRequestResponseDTO());
        assertNotNull(service.getById(1L));
    }

    @Test
    void getById_notFound_throws() {
        when(promotionRequestRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.getById(99L));
    }

    @Test
    void create_success() {
        PromotionRequestRequestDTO dto = new PromotionRequestRequestDTO();
        dto.setReleaseID(10L);
        dto.setFromEnvID(1L);
        dto.setToEnvID(2L);

        when(promotionRequestMapper.toEntity(dto)).thenReturn(entity);
        when(promotionRequestRepository.save(entity)).thenReturn(entity);
        when(promotionRequestMapper.toResponse(entity)).thenReturn(new PromotionRequestResponseDTO());

        assertNotNull(service.create(dto));
        verify(promotionRequestRepository).save(entity);
    }

    @Test
    void delete_notFound_throws() {
        when(promotionRequestRepository.findById(5L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.delete(5L));
        verify(promotionRequestRepository, never()).delete(any());
    }
}
