package com.cts.releasepilot.communication.service;

import com.cts.releasepilot.communication.common.AdvisoryStatus;
import com.cts.releasepilot.communication.common.Severity;
import com.cts.releasepilot.communication.dto.CustomerAdvisoryRequestDTO;
import com.cts.releasepilot.communication.dto.CustomerAdvisoryResponseDTO;
import com.cts.releasepilot.communication.entity.CustomerAdvisory;
import com.cts.releasepilot.communication.exception.ResourceNotFoundException;
import com.cts.releasepilot.communication.mapper.CustomerAdvisoryMapper;
import com.cts.releasepilot.communication.repository.CustomerAdvisoryRepository;
import com.cts.releasepilot.communication.service.impl.CustomerAdvisoryServiceImpl;
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
class CustomerAdvisoryServiceImplTest {

    @Mock private CustomerAdvisoryRepository advisoryRepository;
    @Mock private CustomerAdvisoryMapper advisoryMapper;
    @InjectMocks private CustomerAdvisoryServiceImpl advisoryService;

    private CustomerAdvisory advisory;

    @BeforeEach
    void setUp() {
        advisory = new CustomerAdvisory(1L, 100L, "Security patch", Severity.Critical,
                "v1.0", "Upgrade to v1.1", null, AdvisoryStatus.Draft);
    }

    @Test
    void getAdvisoryById_found() {
        when(advisoryRepository.findById(1L)).thenReturn(Optional.of(advisory));
        when(advisoryMapper.toResponse(advisory)).thenReturn(new CustomerAdvisoryResponseDTO());
        assertNotNull(advisoryService.getAdvisoryById(1L));
    }

    @Test
    void getAdvisoryById_notFound_throws() {
        when(advisoryRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> advisoryService.getAdvisoryById(99L));
    }

    @Test
    void createAdvisory_defaultsStatusAndSaves() {
        CustomerAdvisoryRequestDTO dto = new CustomerAdvisoryRequestDTO();
        dto.setReleaseID(100L);
        dto.setTitle("Security patch");
        dto.setSeverity(Severity.Critical);

        CustomerAdvisory toSave = new CustomerAdvisory();
        when(advisoryMapper.toEntity(dto)).thenReturn(toSave);
        when(advisoryRepository.save(toSave)).thenReturn(advisory);
        when(advisoryMapper.toResponse(advisory)).thenReturn(new CustomerAdvisoryResponseDTO());

        assertNotNull(advisoryService.createAdvisory(dto));
        assertEquals(AdvisoryStatus.Draft, toSave.getStatus());
        verify(advisoryRepository).save(toSave);
    }

    @Test
    void deleteAdvisory_notFound_throws() {
        when(advisoryRepository.findById(5L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> advisoryService.deleteAdvisory(5L));
        verify(advisoryRepository, never()).delete(any());
    }

    @Test
    void getAllAdvisories_returnsMappedList() {
        when(advisoryRepository.findAll()).thenReturn(List.of(advisory));
        when(advisoryMapper.toResponse(advisory)).thenReturn(new CustomerAdvisoryResponseDTO());
        assertEquals(1, advisoryService.getAllAdvisories().size());
    }
}
