package com.cts.releasepilot.release.service;

import com.cts.releasepilot.release.common.DependencyType;
import com.cts.releasepilot.release.dto.PatchDependencyRequestDTO;
import com.cts.releasepilot.release.dto.PatchDependencyResponseDTO;
import com.cts.releasepilot.release.entity.PatchDependency;
import com.cts.releasepilot.release.exception.ResourceNotFoundException;
import com.cts.releasepilot.release.mapper.PatchDependencyMapper;
import com.cts.releasepilot.release.repository.PatchDependencyRepository;
import com.cts.releasepilot.release.service.impl.PatchDependencyServiceImpl;
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
class PatchDependencyServiceImplTest {

    @Mock private PatchDependencyRepository patchDependencyRepository;
    @Mock private PatchDependencyMapper patchDependencyMapper;
    @InjectMocks private PatchDependencyServiceImpl service;

    private PatchDependency entity;

    @BeforeEach
    void setUp() {
        entity = new PatchDependency(1L, 10L, 20L, DependencyType.MustInstallBefore, "note");
    }

    @Test
    void getById_found() {
        when(patchDependencyRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(patchDependencyMapper.toResponse(entity)).thenReturn(new PatchDependencyResponseDTO());
        assertNotNull(service.getById(1L));
    }

    @Test
    void getById_notFound_throws() {
        when(patchDependencyRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.getById(99L));
    }

    @Test
    void create_success() {
        PatchDependencyRequestDTO dto = new PatchDependencyRequestDTO();
        dto.setReleaseID(10L);
        dto.setDependsOnReleaseID(20L);
        dto.setDependencyType(DependencyType.MustInstallBefore);

        when(patchDependencyMapper.toEntity(dto)).thenReturn(entity);
        when(patchDependencyRepository.save(entity)).thenReturn(entity);
        when(patchDependencyMapper.toResponse(entity)).thenReturn(new PatchDependencyResponseDTO());

        assertNotNull(service.create(dto));
        verify(patchDependencyRepository).save(entity);
    }

    @Test
    void delete_notFound_throws() {
        when(patchDependencyRepository.findById(5L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.delete(5L));
        verify(patchDependencyRepository, never()).delete(any());
    }
}
