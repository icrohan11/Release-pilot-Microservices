package com.cts.releasepilot.backlog.dto;

import com.cts.releasepilot.backlog.common.SprintItemStatus;
import jakarta.validation.constraints.NotNull;

/** Payload for creating or updating a sprint item. */
public class SprintItemRequestDTO {

    @NotNull(message = "Sprint ID is required")
    private Long sprintID;

    @NotNull(message = "Backlog item ID is required")
    private Long backlogItemID;

    private Long assignedDevID;

    private Double estimatedHours;

    private Double actualHours;

    private SprintItemStatus status;

    public Long getSprintID() { return sprintID; }
    public void setSprintID(Long sprintID) { this.sprintID = sprintID; }

    public Long getBacklogItemID() { return backlogItemID; }
    public void setBacklogItemID(Long backlogItemID) { this.backlogItemID = backlogItemID; }

    public Long getAssignedDevID() { return assignedDevID; }
    public void setAssignedDevID(Long assignedDevID) { this.assignedDevID = assignedDevID; }

    public Double getEstimatedHours() { return estimatedHours; }
    public void setEstimatedHours(Double estimatedHours) { this.estimatedHours = estimatedHours; }

    public Double getActualHours() { return actualHours; }
    public void setActualHours(Double actualHours) { this.actualHours = actualHours; }

    public SprintItemStatus getStatus() { return status; }
    public void setStatus(SprintItemStatus status) { this.status = status; }
}
