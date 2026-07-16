package com.cts.releasepilot.product.dto;

import com.cts.releasepilot.product.common.MilestoneStatus;

/** Roadmap milestone representation returned to clients. */
public class RoadmapMilestoneResponseDTO {

    private Long milestoneID;
    private Long productID;
    private String milestoneName;
    private String targetQuarter;
    private String strategicTheme;
    private String expectedVersion;
    private MilestoneStatus status;

    public Long getMilestoneID() { return milestoneID; }
    public void setMilestoneID(Long milestoneID) { this.milestoneID = milestoneID; }

    public Long getProductID() { return productID; }
    public void setProductID(Long productID) { this.productID = productID; }

    public String getMilestoneName() { return milestoneName; }
    public void setMilestoneName(String milestoneName) { this.milestoneName = milestoneName; }

    public String getTargetQuarter() { return targetQuarter; }
    public void setTargetQuarter(String targetQuarter) { this.targetQuarter = targetQuarter; }

    public String getStrategicTheme() { return strategicTheme; }
    public void setStrategicTheme(String strategicTheme) { this.strategicTheme = strategicTheme; }

    public String getExpectedVersion() { return expectedVersion; }
    public void setExpectedVersion(String expectedVersion) { this.expectedVersion = expectedVersion; }

    public MilestoneStatus getStatus() { return status; }
    public void setStatus(MilestoneStatus status) { this.status = status; }
}
