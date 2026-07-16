package com.cts.releasepilot.backlog.dto;

import com.cts.releasepilot.backlog.common.BacklogStatus;
import com.cts.releasepilot.backlog.common.BacklogType;
import com.cts.releasepilot.backlog.common.Priority;

/** Backlog item representation returned to clients. */
public class BacklogItemResponseDTO {

    private Long itemID;
    private Long productID;
    private String title;
    private BacklogType type;
    private Priority priority;
    private Integer storyPoints;
    private Long requestedByID;
    private Long milestoneID;
    private BacklogStatus status;

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
