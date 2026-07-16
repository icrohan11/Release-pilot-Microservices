package com.cts.releasepilot.release.service;

import com.cts.releasepilot.release.common.FreezeStatus;
import com.cts.releasepilot.release.dto.ChangeFreezeWindowRequestDTO;
import com.cts.releasepilot.release.dto.ChangeFreezeWindowResponseDTO;
import com.cts.releasepilot.release.entity.ChangeFreezeWindow;
import com.cts.releasepilot.release.exception.InvalidOperationException;
import com.cts.releasepilot.release.exception.ResourceNotFoundException;
import com.cts.releasepilot.release.mapper.ChangeFreezeWindowMapper;
import com.cts.releasepilot.release.repository.ChangeFreezeWindowRepository;
import com.cts.releasepilot.release.service.impl.ChangeFreezeWindowServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ChangeFreezeWindowServiceImplTest {

    @Mock private ChangeFreezeWindowRepository changeFreezeWindowRepository;
    @Mock private ChangeFreezeWindowMapper changeFreezeWindowMapper;
    @InjectMocks private ChangeFreezeWindowServiceImpl service;

    private ChangeFreezeWindow entity;

    @BeforeEach
    void setUp() {
        entity = new ChangeFreezeWindow(1L, 10L, LocalDate.of(2026, 1, 1),
                LocalDate.of(2026, 1, 10), "peak", 5L, FreezeStatus.Scheduled);
    }

    @Test
    void getById_found() {
        when(changeFreezeWindowRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(changeFreezeWindowMapper.toResponse(entity)).thenReturn(new ChangeFreezeWindowResponseDTO());
        assertNotNull(service.getById(1L));
    }

    @Test
    void getById_notFound_throws() {
        when(changeFreezeWindowRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.getById(99L));
    }

    @Test
    void create_success() {
        ChangeFreezeWindowRequestDTO dto = new ChangeFreezeWindowRequestDTO();
        dto.setProductID(10L);
        dto.setStartDate(LocalDate.of(2026, 1, 1));
        dto.setEndDate(LocalDate.of(2026, 1, 10));

        when(changeFreezeWindowMapper.toEntity(dto)).thenReturn(entity);
        when(changeFreezeWindowRepository.save(entity)).thenReturn(entity);
        when(changeFreezeWindowMapper.toResponse(entity)).thenReturn(new ChangeFreezeWindowResponseDTO());

        assertNotNull(service.create(dto));
        verify(changeFreezeWindowRepository).save(entity);
    }

    @Test
    void create_endBeforeStart_throws() {
        ChangeFreezeWindowRequestDTO dto = new ChangeFreezeWindowRequestDTO();
        dto.setProductID(10L);
        dto.setStartDate(LocalDate.of(2026, 1, 10));
        dto.setEndDate(LocalDate.of(2026, 1, 1));

        assertThrows(InvalidOperationException.class, () -> service.create(dto));
        verify(changeFreezeWindowRepository, never()).save(any());
    }

    @Test
    void delete_notFound_throws() {
        when(changeFreezeWindowRepository.findById(5L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.delete(5L));
        verify(changeFreezeWindowRepository, never()).delete(any());
    }
}
