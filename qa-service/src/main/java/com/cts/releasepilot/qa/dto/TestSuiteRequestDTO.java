package com.cts.releasepilot.qa.dto;

import com.cts.releasepilot.qa.common.SuiteStatus;
import com.cts.releasepilot.qa.common.SuiteType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/** Payload for creating/updating a test suite. */
public class TestSuiteRequestDTO {

    @NotNull(message = "ProductID is required")
    private Long productID;

    @NotBlank(message = "Suite name is required")
    private String suiteName;

    @NotNull(message = "Type is required")
    private SuiteType type;

    private Integer totalTestCases;

    private SuiteStatus status;

    public Long getProductID() { return productID; }
    public void setProductID(Long productID) { this.productID = productID; }

    public String getSuiteName() { return suiteName; }
    public void setSuiteName(String suiteName) { this.suiteName = suiteName; }

    public SuiteType getType() { return type; }
    public void setType(SuiteType type) { this.type = type; }

    public Integer getTotalTestCases() { return totalTestCases; }
    public void setTotalTestCases(Integer totalTestCases) { this.totalTestCases = totalTestCases; }

    public SuiteStatus getStatus() { return status; }
    public void setStatus(SuiteStatus status) { this.status = status; }
}
