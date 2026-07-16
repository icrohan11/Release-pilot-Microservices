package com.cts.releasepilot.product.entity;

import com.cts.releasepilot.product.common.MilestoneStatus;
import jakarta.persistence.*;

/** Persistent roadmap milestone belonging to a product. */
@Entity
@Table(name = "roadmap_milestones")
public class RoadmapMilestone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long milestoneID;

    private Long productID;

    private String milestoneName;

    private String targetQuarter;

    private String strategicTheme;

    private String expectedVersion;

    @Enumerated(EnumType.STRING)
    private MilestoneStatus status;

    public RoadmapMilestone() {
    }

    public RoadmapMilestone(Long milestoneID, Long productID, String milestoneName, String targetQuarter,
                            String strategicTheme, String expectedVersion, MilestoneStatus status) {
        this.milestoneID = milestoneID;
        this.productID = productID;
        this.milestoneName = milestoneName;
        this.targetQuarter = targetQuarter;
        this.strategicTheme = strategicTheme;
        this.expectedVersion = expectedVersion;
        this.status = status;
    }

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
