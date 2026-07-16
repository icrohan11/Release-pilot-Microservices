package com.cts.releasepilot.release.service;

import com.cts.releasepilot.release.dto.PatchDependencyRequestDTO;
import com.cts.releasepilot.release.dto.PatchDependencyResponseDTO;

import java.util.List;

/** Patch dependency operations. */
public interface PatchDependencyService {

    List<PatchDependencyResponseDTO> getAll();

    PatchDependencyResponseDTO getById(Long id);

    PatchDependencyResponseDTO create(PatchDependencyRequestDTO dto);

    PatchDependencyResponseDTO update(Long id, PatchDependencyRequestDTO dto);

    void delete(Long id);

    List<PatchDependencyResponseDTO> getByRelease(Long releaseID);
}
