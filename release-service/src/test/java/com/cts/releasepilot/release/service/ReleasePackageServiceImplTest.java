package com.cts.releasepilot.release.service;

import com.cts.releasepilot.release.client.ProductClientService;
import com.cts.releasepilot.release.common.ReleaseStatus;
import com.cts.releasepilot.release.common.ReleaseType;
import com.cts.releasepilot.release.dto.ProductDTO;
import com.cts.releasepilot.release.dto.ReleasePackageRequestDTO;
import com.cts.releasepilot.release.dto.ReleasePackageResponseDTO;
import com.cts.releasepilot.release.entity.ReleasePackage;
import com.cts.releasepilot.release.exception.ResourceNotFoundException;
import com.cts.releasepilot.release.mapper.ReleasePackageMapper;
import com.cts.releasepilot.release.repository.ReleasePackageRepository;
import com.cts.releasepilot.release.service.impl.ReleasePackageServiceImpl;
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
class ReleasePackageServiceImplTest {

    @Mock private ReleasePackageRepository releasePackageRepository;
    @Mock private ReleasePackageMapper releasePackageMapper;
    @Mock private ProductClientService productClientService;
    @InjectMocks private ReleasePackageServiceImpl service;

    private ReleasePackage entity;

    @BeforeEach
    void setUp() {
        entity = new ReleasePackage(1L, 10L, "1.0.0", ReleaseType.Major, "1,2",
                "notes", null, 5L, ReleaseStatus.Draft);
    }

    @Test
    void getById_found() {
        when(releasePackageRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(releasePackageMapper.toResponse(entity)).thenReturn(new ReleasePackageResponseDTO());
        assertNotNull(service.getById(1L));
    }

    @Test
    void getById_notFound_throws() {
        when(releasePackageRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.getById(99L));
    }

    @Test
    void create_validatesProductAndSaves() {
        ReleasePackageRequestDTO dto = new ReleasePackageRequestDTO();
        dto.setProductID(10L);
        dto.setVersionNumber("1.0.0");
        dto.setReleaseType(ReleaseType.Major);

        when(productClientService.getProduct(10L)).thenReturn(new ProductDTO(10L, "Prod", "Active"));
        when(releasePackageMapper.toEntity(dto)).thenReturn(entity);
        when(releasePackageRepository.save(entity)).thenReturn(entity);
        when(releasePackageMapper.toResponse(entity)).thenReturn(new ReleasePackageResponseDTO());

        assertNotNull(service.create(dto));
        verify(releasePackageRepository).save(entity);
    }

    @Test
    void create_stillSaves_whenProductFallbackUsed() {
        ReleasePackageRequestDTO dto = new ReleasePackageRequestDTO();
        dto.setProductID(10L);
        dto.setVersionNumber("1.0.0");
        dto.setReleaseType(ReleaseType.Major);

        // The circuit breaker fallback (in ProductClientService) produced a degraded product;
        // the release save path must still proceed with that degraded response.
        ProductDTO degraded = new ProductDTO(10L, "Unavailable", "UNKNOWN");
        assertEquals("UNKNOWN", degraded.getStatus());

        when(productClientService.getProduct(10L)).thenReturn(degraded);
        when(releasePackageMapper.toEntity(dto)).thenReturn(entity);
        when(releasePackageRepository.save(entity)).thenReturn(entity);
        when(releasePackageMapper.toResponse(entity)).thenReturn(new ReleasePackageResponseDTO());

        assertNotNull(service.create(dto));
        verify(releasePackageRepository).save(entity);
    }

    @Test
    void getAll_returnsMappedList() {
        when(releasePackageRepository.findAll()).thenReturn(List.of(entity));
        when(releasePackageMapper.toResponse(entity)).thenReturn(new ReleasePackageResponseDTO());
        assertEquals(1, service.getAll().size());
    }

    @Test
    void delete_notFound_throws() {
        when(releasePackageRepository.findById(5L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.delete(5L));
        verify(releasePackageRepository, never()).delete(any());
    }
}
