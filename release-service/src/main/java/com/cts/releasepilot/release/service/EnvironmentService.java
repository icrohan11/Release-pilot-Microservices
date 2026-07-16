package com.cts.releasepilot.release.service;

import com.cts.releasepilot.release.common.EnvStatus;
import com.cts.releasepilot.release.dto.EnvironmentRequestDTO;
import com.cts.releasepilot.release.dto.EnvironmentResponseDTO;

import java.util.List;

/** Environment operations. */
public interface EnvironmentService {

    List<EnvironmentResponseDTO> getAll();

    EnvironmentResponseDTO getById(Long id);

    EnvironmentResponseDTO create(EnvironmentRequestDTO dto);

    EnvironmentResponseDTO update(Long id, EnvironmentRequestDTO dto);

    void delete(Long id);

    List<EnvironmentResponseDTO> getByProduct(Long productID);

    List<EnvironmentResponseDTO> getByStatus(EnvStatus status);
}
