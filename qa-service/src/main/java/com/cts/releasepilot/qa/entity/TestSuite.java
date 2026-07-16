package com.cts.releasepilot.qa.entity;

import com.cts.releasepilot.qa.common.SuiteStatus;
import com.cts.releasepilot.qa.common.SuiteType;
import jakarta.persistence.*;

/** A named collection of test cases exercised against a product. */
@Entity
@Table(name = "test_suites")
public class TestSuite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long suiteID;

    private Long productID;

    private String suiteName;

    @Enumerated(EnumType.STRING)
    private SuiteType type;

    private Integer totalTestCases;

    @Enumerated(EnumType.STRING)
    private SuiteStatus status;

    public TestSuite() {
    }

    public TestSuite(Long suiteID, Long productID, String suiteName, SuiteType type,
                     Integer totalTestCases, SuiteStatus status) {
        this.suiteID = suiteID;
        this.productID = productID;
        this.suiteName = suiteName;
        this.type = type;
        this.totalTestCases = totalTestCases;
        this.status = status;
    }

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
