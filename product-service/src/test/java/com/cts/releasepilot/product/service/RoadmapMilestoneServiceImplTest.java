package com.cts.releasepilot.product.service;

import com.cts.releasepilot.product.common.MilestoneStatus;
import com.cts.releasepilot.product.dto.RoadmapMilestoneRequestDTO;
import com.cts.releasepilot.product.dto.RoadmapMilestoneResponseDTO;
import com.cts.releasepilot.product.entity.RoadmapMilestone;
import com.cts.releasepilot.product.exception.ResourceNotFoundException;
import com.cts.releasepilot.product.mapper.RoadmapMilestoneMapper;
import com.cts.releasepilot.product.repository.RoadmapMilestoneRepository;
import com.cts.releasepilot.product.service.impl.RoadmapMilestoneServiceImpl;
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
class RoadmapMilestoneServiceImplTest {

    @Mock private RoadmapMilestoneRepository milestoneRepository;
    @Mock private RoadmapMilestoneMapper milestoneMapper;
    @InjectMocks private RoadmapMilestoneServiceImpl milestoneService;

    private RoadmapMilestone milestone;

    @BeforeEach
    void setUp() {
        milestone = new RoadmapMilestone(1L, 10L, "GA Launch", "Q3-2026",
                "Scale", "2.0.0", MilestoneStatus.Planned);
    }

    @Test
    void getMilestoneById_found() {
        when(milestoneRepository.findById(1L)).thenReturn(Optional.of(milestone));
        when(milestoneMapper.toResponse(milestone)).thenReturn(new RoadmapMilestoneResponseDTO());
        assertNotNull(milestoneService.getMilestoneById(1L));
    }

    @Test
    void getMilestoneById_notFound_throws() {
        when(milestoneRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> milestoneService.getMilestoneById(99L));
    }

    @Test
    void getAllMilestones_returnsMappedList() {
        when(milestoneRepository.findAll()).thenReturn(List.of(milestone));
        when(milestoneMapper.toResponse(milestone)).thenReturn(new RoadmapMilestoneResponseDTO());
        assertEquals(1, milestoneService.getAllMilestones().size());
    }

    @Test
    void saveMilestone_success() {
        RoadmapMilestoneRequestDTO dto = new RoadmapMilestoneRequestDTO();
        dto.setProductID(10L);
        dto.setMilestoneName("GA Launch");
        dto.setStatus(MilestoneStatus.Planned);

        when(milestoneMapper.toEntity(dto)).thenReturn(milestone);
        when(milestoneRepository.save(milestone)).thenReturn(milestone);
        when(milestoneMapper.toResponse(milestone)).thenReturn(new RoadmapMilestoneResponseDTO());

        assertNotNull(milestoneService.saveMilestone(dto));
        verify(milestoneRepository).save(milestone);
    }

    @Test
    void updateMilestone_notFound_throws() {
        RoadmapMilestoneRequestDTO dto = new RoadmapMilestoneRequestDTO();
        when(milestoneRepository.findById(7L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> milestoneService.updateMilestone(7L, dto));
        verify(milestoneRepository, never()).save(any());
    }

    @Test
    void deleteMilestone_notFound_throws() {
        when(milestoneRepository.findById(5L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> milestoneService.deleteMilestone(5L));
        verify(milestoneRepository, never()).delete(any());
    }
}
