package com.cts.releasepilot.backlog.entity;

import com.cts.releasepilot.backlog.common.SprintStatus;
import jakarta.persistence.*;

import java.time.LocalDate;

/** A time-boxed sprint containing sprint items. */
@Entity
@Table(name = "sprints")
public class Sprint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sprintID;

    private Long productID;

    private String sprintName;

    private LocalDate startDate;

    private LocalDate endDate;

    private String goal;

    private Integer totalStoryPoints;

    private Integer completedPoints;

    @Enumerated(EnumType.STRING)
    private SprintStatus status;

    public Sprint() {
    }

    public Sprint(Long sprintID, Long productID, String sprintName, LocalDate startDate, LocalDate endDate,
                  String goal, Integer totalStoryPoints, Integer completedPoints, SprintStatus status) {
        this.sprintID = sprintID;
        this.productID = productID;
        this.sprintName = sprintName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.goal = goal;
        this.totalStoryPoints = totalStoryPoints;
        this.completedPoints = completedPoints;
        this.status = status;
    }

    public Long getSprintID() { return sprintID; }
    public void setSprintID(Long sprintID) { this.sprintID = sprintID; }

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
