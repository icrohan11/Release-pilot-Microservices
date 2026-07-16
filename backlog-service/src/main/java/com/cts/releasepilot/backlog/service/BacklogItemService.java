package com.cts.releasepilot.backlog.service;

import com.cts.releasepilot.backlog.common.BacklogStatus;
import com.cts.releasepilot.backlog.common.BacklogType;
import com.cts.releasepilot.backlog.common.Priority;
import com.cts.releasepilot.backlog.dto.BacklogItemRequestDTO;
import com.cts.releasepilot.backlog.dto.BacklogItemResponseDTO;

import java.util.List;

/** Backlog item management operations. */
public interface BacklogItemService {

    List<BacklogItemResponseDTO> getAllBacklogItems();

    BacklogItemResponseDTO getBacklogItemById(Long id);

    BacklogItemResponseDTO createBacklogItem(BacklogItemRequestDTO dto);

    BacklogItemResponseDTO updateBacklogItem(Long id, BacklogItemRequestDTO dto);

    void deleteBacklogItem(Long id);

    List<BacklogItemResponseDTO> getBacklogItemsByProduct(Long productID);

    List<BacklogItemResponseDTO> getBacklogItemsByStatus(BacklogStatus status);

    List<BacklogItemResponseDTO> getBacklogItemsByPriority(Priority priority);

    List<BacklogItemResponseDTO> getBacklogItemsByType(BacklogType type);
}
