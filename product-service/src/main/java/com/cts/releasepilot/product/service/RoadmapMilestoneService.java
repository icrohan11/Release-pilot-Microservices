package com.cts.releasepilot.product.service;

import com.cts.releasepilot.product.common.MilestoneStatus;
import com.cts.releasepilot.product.dto.RoadmapMilestoneRequestDTO;
import com.cts.releasepilot.product.dto.RoadmapMilestoneResponseDTO;

import java.util.List;

/** Roadmap milestone operations. */
public interface RoadmapMilestoneService {

    List<RoadmapMilestoneResponseDTO> getAllMilestones();

    RoadmapMilestoneResponseDTO getMilestoneById(Long id);

    RoadmapMilestoneResponseDTO saveMilestone(RoadmapMilestoneRequestDTO dto);

    RoadmapMilestoneResponseDTO updateMilestone(Long id, RoadmapMilestoneRequestDTO dto);

    void deleteMilestone(Long id);

    List<RoadmapMilestoneResponseDTO> getMilestonesByProduct(Long productID);

    List<RoadmapMilestoneResponseDTO> getMilestonesByStatus(MilestoneStatus status);
}
