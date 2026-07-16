package com.cts.releasepilot.backlog.entity;

import com.cts.releasepilot.backlog.common.SprintItemStatus;
import jakarta.persistence.*;

/** A backlog item pulled into a sprint and assigned to a developer. */
@Entity
@Table(name = "sprint_items")
public class SprintItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sprintItemID;

    private Long sprintID;

    private Long backlogItemID;

    private Long assignedDevID;

    private Double estimatedHours;

    private Double actualHours;

    @Enumerated(EnumType.STRING)
    private SprintItemStatus status;

    public SprintItem() {
    }

    public SprintItem(Long sprintItemID, Long sprintID, Long backlogItemID, Long assignedDevID,
                      Double estimatedHours, Double actualHours, SprintItemStatus status) {
        this.sprintItemID = sprintItemID;
        this.sprintID = sprintID;
        this.backlogItemID = backlogItemID;
        this.assignedDevID = assignedDevID;
        this.estimatedHours = estimatedHours;
        this.actualHours = actualHours;
        this.status = status;
    }

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
