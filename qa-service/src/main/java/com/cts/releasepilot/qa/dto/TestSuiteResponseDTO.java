package com.cts.releasepilot.qa.dto;

import com.cts.releasepilot.qa.common.SuiteStatus;
import com.cts.releasepilot.qa.common.SuiteType;

/** Test suite representation returned to clients. */
public class TestSuiteResponseDTO {

    private Long suiteID;
    private Long productID;
    private String suiteName;
    private SuiteType type;
    private Integer totalTestCases;
    private SuiteStatus status;

    public Long getSuiteID() { return suiteID; }
    public void setSuiteID(Long suiteID) { this.suiteID = suiteID; }

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
