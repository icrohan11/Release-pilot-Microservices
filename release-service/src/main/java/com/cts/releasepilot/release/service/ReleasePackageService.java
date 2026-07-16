package com.cts.releasepilot.release.service;

import com.cts.releasepilot.release.common.ReleaseStatus;
import com.cts.releasepilot.release.dto.ReleasePackageRequestDTO;
import com.cts.releasepilot.release.dto.ReleasePackageResponseDTO;

import java.util.List;

/** Release package operations. */
public interface ReleasePackageService {

    List<ReleasePackageResponseDTO> getAll();

    ReleasePackageResponseDTO getById(Long id);

    ReleasePackageResponseDTO create(ReleasePackageRequestDTO dto);

    ReleasePackageResponseDTO update(Long id, ReleasePackageRequestDTO dto);

    void delete(Long id);

    List<ReleasePackageResponseDTO> getByProduct(Long productID);

    List<ReleasePackageResponseDTO> getByStatus(ReleaseStatus status);
}
