package com.cts.releasepilot.product.dto;

import com.cts.releasepilot.product.common.MilestoneStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/** Payload for creating or updating a roadmap milestone. */
public class RoadmapMilestoneRequestDTO {

    @NotNull(message = "Product ID is required")
    private Long productID;

    @NotBlank(message = "Milestone name is required")
    @Size(min = 2, max = 150, message = "Milestone name must be between 2 and 150 characters")
    private String milestoneName;

    private String targetQuarter;

    private String strategicTheme;

    private String expectedVersion;

    @NotNull(message = "Status is required")
    private MilestoneStatus status;

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
