package com.cts.releasepilot.qa.mapper;

import com.cts.releasepilot.qa.dto.ReleaseGateRequestDTO;
import com.cts.releasepilot.qa.dto.ReleaseGateResponseDTO;
import com.cts.releasepilot.qa.entity.ReleaseGate;
import org.springframework.stereotype.Component;

/** Converts between {@link ReleaseGate} entities and their DTOs. */
@Component
public class ReleaseGateMapper {

    public ReleaseGate toEntity(ReleaseGateRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        ReleaseGate gate = new ReleaseGate();
        gate.setReleaseID(dto.getReleaseID());
        gate.setGateName(dto.getGateName());
        gate.setGateType(dto.getGateType());
        gate.setThreshold(dto.getThreshold());
        gate.setActualValue(dto.getActualValue());
        gate.setOutcome(dto.getOutcome());
        gate.setEvaluatedByID(dto.getEvaluatedByID());
        gate.setStatus(dto.getStatus());
        return gate;
    }

    public void updateEntity(ReleaseGate gate, ReleaseGateRequestDTO dto) {
        gate.setReleaseID(dto.getReleaseID());
        gate.setGateName(dto.getGateName());
        gate.setGateType(dto.getGateType());
        gate.setThreshold(dto.getThreshold());
        gate.setActualValue(dto.getActualValue());
        gate.setOutcome(dto.getOutcome());
        gate.setEvaluatedByID(dto.getEvaluatedByID());
        gate.setStatus(dto.getStatus());
    }

    public ReleaseGateResponseDTO toResponse(ReleaseGate gate) {
        if (gate == null) {
            return null;
        }
        ReleaseGateResponseDTO dto = new ReleaseGateResponseDTO();
        dto.setGateID(gate.getGateID());
        dto.setReleaseID(gate.getReleaseID());
        dto.setGateName(gate.getGateName());
        dto.setGateType(gate.getGateType());
        dto.setThreshold(gate.getThreshold());
        dto.setActualValue(gate.getActualValue());
        dto.setOutcome(gate.getOutcome());
        dto.setEvaluatedByID(gate.getEvaluatedByID());
        dto.setStatus(gate.getStatus());
        return dto;
    }
}
