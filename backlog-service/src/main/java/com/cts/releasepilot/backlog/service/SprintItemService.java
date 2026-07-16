package com.cts.releasepilot.backlog.service;

import com.cts.releasepilot.backlog.common.SprintItemStatus;
import com.cts.releasepilot.backlog.dto.SprintItemRequestDTO;
import com.cts.releasepilot.backlog.dto.SprintItemResponseDTO;

import java.util.List;

/** Sprint item management operations. */
public interface SprintItemService {

    List<SprintItemResponseDTO> getAllSprintItems();

    SprintItemResponseDTO getSprintItemById(Long id);

    SprintItemResponseDTO createSprintItem(SprintItemRequestDTO dto);

    SprintItemResponseDTO updateSprintItem(Long id, SprintItemRequestDTO dto);

    void deleteSprintItem(Long id);

    List<SprintItemResponseDTO> getSprintItemsBySprint(Long sprintID);

    List<SprintItemResponseDTO> getSprintItemsByStatus(SprintItemStatus status);
}
