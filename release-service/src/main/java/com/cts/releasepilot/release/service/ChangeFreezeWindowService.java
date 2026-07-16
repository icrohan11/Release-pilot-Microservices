package com.cts.releasepilot.release.service;

import com.cts.releasepilot.release.common.FreezeStatus;
import com.cts.releasepilot.release.dto.ChangeFreezeWindowRequestDTO;
import com.cts.releasepilot.release.dto.ChangeFreezeWindowResponseDTO;

import java.util.List;

/** Change freeze window operations. */
public interface ChangeFreezeWindowService {

    List<ChangeFreezeWindowResponseDTO> getAll();

    ChangeFreezeWindowResponseDTO getById(Long id);

    ChangeFreezeWindowResponseDTO create(ChangeFreezeWindowRequestDTO dto);

    ChangeFreezeWindowResponseDTO update(Long id, ChangeFreezeWindowRequestDTO dto);

    void delete(Long id);

    List<ChangeFreezeWindowResponseDTO> getByProduct(Long productID);

    List<ChangeFreezeWindowResponseDTO> getByStatus(FreezeStatus status);
}
