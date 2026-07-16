package com.cts.releasepilot.qa.dto;

import com.cts.releasepilot.qa.common.GateOutcome;
import com.cts.releasepilot.qa.common.GateStatus;
import com.cts.releasepilot.qa.common.GateType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/** Payload for creating/updating a release quality gate. */
public class ReleaseGateRequestDTO {

    @NotNull(message = "ReleaseID is required")
    private Long releaseID;

    @NotBlank(message = "Gate name is required")
    private String gateName;

    @NotNull(message = "Gate type is required")
    private GateType gateType;

    private Double threshold;

    private Double actualValue;

    private GateOutcome outcome;

    private Long evaluatedByID;

    private GateStatus status;

    public Long getReleaseID() { return releaseID; }
    public void setReleaseID(Long releaseID) { this.releaseID = releaseID; }

    public String getGateName() { return gateName; }
    public void setGateName(String gateName) { this.gateName = gateName; }

    public GateType getGateType() { return gateType; }
    public void setGateType(GateType gateType) { this.gateType = gateType; }

    public Double getThreshold() { return threshold; }
    public void setThreshold(Double threshold) { this.threshold = threshold; }

    public Double getActualValue() { return actualValue; }
    public void setActualValue(Double actualValue) { this.actualValue = actualValue; }

    public GateOutcome getOutcome() { return outcome; }
    public void setOutcome(GateOutcome outcome) { this.outcome = outcome; }

    public Long getEvaluatedByID() { return evaluatedByID; }
    public void setEvaluatedByID(Long evaluatedByID) { this.evaluatedByID = evaluatedByID; }

    public GateStatus getStatus() { return status; }
    public void setStatus(GateStatus status) { this.status = status; }
}
