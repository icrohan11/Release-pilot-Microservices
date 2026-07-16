package com.cts.releasepilot.backlog.dto;

import com.cts.releasepilot.backlog.common.SprintItemStatus;

/** Sprint item representation returned to clients. */
public class SprintItemResponseDTO {

    private Long sprintItemID;
    private Long sprintID;
    private Long backlogItemID;
    private Long assignedDevID;
    private Double estimatedHours;
    private Double actualHours;
    private SprintItemStatus status;

    public Long getSprintItemID() { return sprintItemID; }
    public void setSprintItemID(Long sprintItemID) { this.sprintItemID = sprintItemID; }

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
