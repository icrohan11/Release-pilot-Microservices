package com.cts.releasepilot.qa.dto;

import com.cts.releasepilot.qa.common.ExecutionStatus;

import java.time.LocalDateTime;

/** Test execution representation returned to clients. */
public class TestExecutionResponseDTO {

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
    private ExecutionStatus status;

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
