package com.cts.releasepilot.release.service;

import com.cts.releasepilot.release.common.PromotionStatus;
import com.cts.releasepilot.release.dto.PromotionRequestRequestDTO;
import com.cts.releasepilot.release.dto.PromotionRequestResponseDTO;

import java.util.List;

/** Promotion request operations. */
public interface PromotionRequestService {

    List<PromotionRequestResponseDTO> getAll();

    PromotionRequestResponseDTO getById(Long id);

    PromotionRequestResponseDTO create(PromotionRequestRequestDTO dto);

    PromotionRequestResponseDTO update(Long id, PromotionRequestRequestDTO dto);

    void delete(Long id);

    List<PromotionRequestResponseDTO> getByRelease(Long releaseID);

    List<PromotionRequestResponseDTO> getByStatus(PromotionStatus status);
}
