package com.cts.releasepilot.qa.entity;

import com.cts.releasepilot.qa.common.ExecutionStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

/** A single run of a test suite against a release. */
@Entity
@Table(name = "test_executions")
public class TestExecution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long executionID;

    private Long releaseID;

    private Long suiteID;

    private Long executedByID;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Integer totalRun;

    private Integer passed;

    private Integer failed;

    private Integer skipped;

    private Double coveragePercent;

    @Enumerated(EnumType.STRING)
    private ExecutionStatus status;

    public TestExecution() {
    }

    public TestExecution(Long executionID, Long releaseID, Long suiteID, Long executedByID,
                         LocalDateTime startDate, LocalDateTime endDate, Integer totalRun,
                         Integer passed, Integer failed, Integer skipped, Double coveragePercent,
                         ExecutionStatus status) {
        this.executionID = executionID;
        this.releaseID = releaseID;
        this.suiteID = suiteID;
        this.executedByID = executedByID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalRun = totalRun;
        this.passed = passed;
        this.failed = failed;
        this.skipped = skipped;
        this.coveragePercent = coveragePercent;
        this.status = status;
    }

    public Long getExecutionID() { return executionID; }
    public void setExecutionID(Long executionID) { this.executionID = executionID; }

    public Long getReleaseID() { return releaseID; }
    public void setReleaseID(Long releaseID) { this.releaseID = releaseID; }

    public Long getSuiteID() { return suiteID; }
    public void setSuiteID(Long suiteID) { this.suiteID = suiteID; }

    public Long getExecutedByID() { return executedByID; }
    public void setExecutedByID(Long executedByID) { this.executedByID = executedByID; }

    public LocalDateTime getStartDate() { return startDate; }
    public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }

    public LocalDateTime getEndDate() { return endDate; }
    public void setEndDate(LocalDateTime endDate) { this.endDate = endDate; }

    public Integer getTotalRun() { return totalRun; }
    public void setTotalRun(Integer totalRun) { this.totalRun = totalRun; }

    public Integer getPassed() { return passed; }
    public void setPassed(Integer passed) { this.passed = passed; }

    public Integer getFailed() { return failed; }
    public void setFailed(Integer failed) { this.failed = failed; }

    public Integer getSkipped() { return skipped; }
    public void setSkipped(Integer skipped) { this.skipped = skipped; }

    public Double getCoveragePercent() { return coveragePercent; }
    public void setCoveragePercent(Double coveragePercent) { this.coveragePercent = coveragePercent; }

    public ExecutionStatus getStatus() { return status; }
    public void setStatus(ExecutionStatus status) { this.status = status; }
}
