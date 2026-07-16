package com.cts.releasepilot.qa.service;

import com.cts.releasepilot.qa.common.GateOutcome;
import com.cts.releasepilot.qa.common.GateStatus;
import com.cts.releasepilot.qa.common.GateType;
import com.cts.releasepilot.qa.dto.ReleaseGateRequestDTO;
import com.cts.releasepilot.qa.dto.ReleaseGateResponseDTO;
import com.cts.releasepilot.qa.entity.ReleaseGate;
import com.cts.releasepilot.qa.exception.ResourceNotFoundException;
import com.cts.releasepilot.qa.mapper.ReleaseGateMapper;
import com.cts.releasepilot.qa.repository.ReleaseGateRepository;
import com.cts.releasepilot.qa.service.impl.ReleaseGateServiceImpl;
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
class ReleaseGateServiceImplTest {

    @Mock private ReleaseGateRepository releaseGateRepository;
    @Mock private ReleaseGateMapper releaseGateMapper;
    @InjectMocks private ReleaseGateServiceImpl releaseGateService;

    private ReleaseGate gate;

    @BeforeEach
    void setUp() {
        gate = new ReleaseGate(1L, 100L, "Test Pass Rate", GateType.TestPassRate,
                90.0, 95.0, GateOutcome.Pass, 5L, GateStatus.Evaluated);
    }

    @Test
    void getGateById_found() {
        when(releaseGateRepository.findById(1L)).thenReturn(Optional.of(gate));
        when(releaseGateMapper.toResponse(gate)).thenReturn(new ReleaseGateResponseDTO());
        assertNotNull(releaseGateService.getGateById(1L));
    }

    @Test
    void getGateById_notFound_throws() {
        when(releaseGateRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> releaseGateService.getGateById(99L));
    }

    @Test
    void createGate_success() {
        ReleaseGateRequestDTO dto = new ReleaseGateRequestDTO();
        dto.setReleaseID(100L);
        dto.setGateName("Test Pass Rate");
        dto.setGateType(GateType.TestPassRate);

        when(releaseGateMapper.toEntity(dto)).thenReturn(gate);
        when(releaseGateRepository.save(gate)).thenReturn(gate);
        when(releaseGateMapper.toResponse(gate)).thenReturn(new ReleaseGateResponseDTO());

        assertNotNull(releaseGateService.createGate(dto));
        verify(releaseGateRepository).save(gate);
    }

    @Test
    void getAllGates_returnsMappedList() {
        when(releaseGateRepository.findAll()).thenReturn(List.of(gate));
        when(releaseGateMapper.toResponse(gate)).thenReturn(new ReleaseGateResponseDTO());
        assertEquals(1, releaseGateService.getAllGates().size());
    }

    @Test
    void deleteGate_notFound_throws() {
        when(releaseGateRepository.findById(5L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> releaseGateService.deleteGate(5L));
        verify(releaseGateRepository, never()).delete(any());
    }
}
