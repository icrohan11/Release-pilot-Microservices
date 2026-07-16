package com.cts.releasepilot.release.service;

import com.cts.releasepilot.release.common.EnvName;
import com.cts.releasepilot.release.common.EnvStatus;
import com.cts.releasepilot.release.dto.EnvironmentRequestDTO;
import com.cts.releasepilot.release.dto.EnvironmentResponseDTO;
import com.cts.releasepilot.release.entity.Environment;
import com.cts.releasepilot.release.exception.ResourceNotFoundException;
import com.cts.releasepilot.release.mapper.EnvironmentMapper;
import com.cts.releasepilot.release.repository.EnvironmentRepository;
import com.cts.releasepilot.release.service.impl.EnvironmentServiceImpl;
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
class EnvironmentServiceImplTest {

    @Mock private EnvironmentRepository environmentRepository;
    @Mock private EnvironmentMapper environmentMapper;
    @InjectMocks private EnvironmentServiceImpl service;

    private Environment entity;

    @BeforeEach
    void setUp() {
        entity = new Environment(1L, 10L, EnvName.Dev, 5L, "1.0.0", null, EnvStatus.Active);
    }

    @Test
    void getById_found() {
        when(environmentRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(environmentMapper.toResponse(entity)).thenReturn(new EnvironmentResponseDTO());
        assertNotNull(service.getById(1L));
    }

    @Test
    void getById_notFound_throws() {
        when(environmentRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.getById(99L));
    }

    @Test
    void create_success() {
        EnvironmentRequestDTO dto = new EnvironmentRequestDTO();
        dto.setProductID(10L);
        dto.setEnvName(EnvName.Dev);

        when(environmentMapper.toEntity(dto)).thenReturn(entity);
        when(environmentRepository.save(entity)).thenReturn(entity);
        when(environmentMapper.toResponse(entity)).thenReturn(new EnvironmentResponseDTO());

        assertNotNull(service.create(dto));
        verify(environmentRepository).save(entity);
    }

    @Test
    void delete_notFound_throws() {
        when(environmentRepository.findById(5L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.delete(5L));
        verify(environmentRepository, never()).delete(any());
    }
}
