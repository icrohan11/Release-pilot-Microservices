package com.cts.releasepilot.qa.service;

import com.cts.releasepilot.qa.common.GateOutcome;
import com.cts.releasepilot.qa.common.GateStatus;
import com.cts.releasepilot.qa.dto.ReleaseGateRequestDTO;
import com.cts.releasepilot.qa.dto.ReleaseGateResponseDTO;

import java.util.List;

/** Release quality gate management operations. */
public interface ReleaseGateService {

    List<ReleaseGateResponseDTO> getAllGates();

    ReleaseGateResponseDTO getGateById(Long id);

    ReleaseGateResponseDTO createGate(ReleaseGateRequestDTO dto);

    ReleaseGateResponseDTO updateGate(Long id, ReleaseGateRequestDTO dto);

    void deleteGate(Long id);

    List<ReleaseGateResponseDTO> getGatesByRelease(Long releaseID);

    List<ReleaseGateResponseDTO> getGatesByOutcome(GateOutcome outcome);

    List<ReleaseGateResponseDTO> getGatesByStatus(GateStatus status);
}
