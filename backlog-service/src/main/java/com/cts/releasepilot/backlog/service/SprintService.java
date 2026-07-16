package com.cts.releasepilot.backlog.service;

import com.cts.releasepilot.backlog.common.SprintStatus;
import com.cts.releasepilot.backlog.dto.SprintRequestDTO;
import com.cts.releasepilot.backlog.dto.SprintResponseDTO;

import java.util.List;

/** Sprint management operations. */
public interface SprintService {

    List<SprintResponseDTO> getAllSprints();

    SprintResponseDTO getSprintById(Long id);

    SprintResponseDTO createSprint(SprintRequestDTO dto);

    SprintResponseDTO updateSprint(Long id, SprintRequestDTO dto);

    void deleteSprint(Long id);

    List<SprintResponseDTO> getSprintsByProduct(Long productID);

    List<SprintResponseDTO> getSprintsByStatus(SprintStatus status);
}
