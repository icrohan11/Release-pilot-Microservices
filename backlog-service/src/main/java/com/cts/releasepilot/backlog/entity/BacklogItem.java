package com.cts.releasepilot.backlog.entity;

import com.cts.releasepilot.backlog.common.BacklogStatus;
import com.cts.releasepilot.backlog.common.BacklogType;
import com.cts.releasepilot.backlog.common.Priority;
import jakarta.persistence.*;

/** A single item in the product backlog. */
@Entity
@Table(name = "backlog_items")
public class BacklogItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemID;

    private Long productID;

    private String title;

    @Enumerated(EnumType.STRING)
    private BacklogType type;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    private Integer storyPoints;

    private Long requestedByID;

    private Long milestoneID;

    @Enumerated(EnumType.STRING)
    private BacklogStatus status;

    public BacklogItem() {
    }

    public BacklogItem(Long itemID, Long productID, String title, BacklogType type, Priority priority,
                       Integer storyPoints, Long requestedByID, Long milestoneID, BacklogStatus status) {
        this.itemID = itemID;
        this.productID = productID;
        this.title = title;
        this.type = type;
        this.priority = priority;
        this.storyPoints = storyPoints;
        this.requestedByID = requestedByID;
        this.milestoneID = milestoneID;
        this.status = status;
    }

    public Long getItemID() { return itemID; }
    public void setItemID(Long itemID) { this.itemID = itemID; }

    public Long getProductID() { return productID; }
    public void setProductID(Long productID) { this.productID = productID; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public BacklogType getType() { return type; }
    public void setType(BacklogType type) { this.type = type; }

    public Priority getPriority() { return priority; }
    public void setPriority(Priority priority) { this.priority = priority; }

    public Integer getStoryPoints() { return storyPoints; }
    public void setStoryPoints(Integer storyPoints) { this.storyPoints = storyPoints; }

    public Long getRequestedByID() { return requestedByID; }
    public void setRequestedByID(Long requestedByID) { this.requestedByID = requestedByID; }

    public Long getMilestoneID() { return milestoneID; }
    public void setMilestoneID(Long milestoneID) { this.milestoneID = milestoneID; }

    public BacklogStatus getStatus() { return status; }
    public void setStatus(BacklogStatus status) { this.status = status; }
}
