package com.cts.releasepilot.backlog.dto;

import com.cts.releasepilot.backlog.common.SprintStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

/** Payload for creating or updating a sprint. */
public class SprintRequestDTO {

    @NotNull(message = "Product ID is required")
    private Long productID;

    @NotBlank(message = "Sprint name is required")
    private String sprintName;

    private LocalDate startDate;

    private LocalDate endDate;

    private String goal;

    private Integer totalStoryPoints;

    private Integer completedPoints;

    private SprintStatus status;

    public Long getProductID() { return productID; }
    public void setProductID(Long productID) { this.productID = productID; }

    public String getSprintName() { return sprintName; }
    public void setSprintName(String sprintName) { this.sprintName = sprintName; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public String getGoal() { return goal; }
    public void setGoal(String goal) { this.goal = goal; }

    public Integer getTotalStoryPoints() { return totalStoryPoints; }
    public void setTotalStoryPoints(Integer totalStoryPoints) { this.totalStoryPoints = totalStoryPoints; }

    public Integer getCompletedPoints() { return completedPoints; }
    public void setCompletedPoints(Integer completedPoints) { this.completedPoints = completedPoints; }

    public SprintStatus getStatus() { return status; }
    public void setStatus(SprintStatus status) { this.status = status; }
}
