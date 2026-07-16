package com.cts.releasepilot.communication.dto;

import com.cts.releasepilot.communication.common.AdvisoryStatus;
import com.cts.releasepilot.communication.common.Severity;

import java.time.LocalDate;

/** Customer advisory representation returned to clients. */
public class CustomerAdvisoryResponseDTO {

    private Long advisoryID;
    private Long releaseID;
    private String title;
    private Severity severity;
    private String affectedVersions;
    private String resolution;
    private LocalDate publishedDate;
    private AdvisoryStatus status;

    public Long getAdvisoryID() { return advisoryID; }
    public void setAdvisoryID(Long advisoryID) { this.advisoryID = advisoryID; }

    public Long getReleaseID() { return releaseID; }
    public void setReleaseID(Long releaseID) { this.releaseID = releaseID; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Severity getSeverity() { return severity; }
    public void setSeverity(Severity severity) { this.severity = severity; }

    public String getAffectedVersions() { return affectedVersions; }
    public void setAffectedVersions(String affectedVersions) { this.affectedVersions = affectedVersions; }

    public String getResolution() { return resolution; }
    public void setResolution(String resolution) { this.resolution = resolution; }

    public LocalDate getPublishedDate() { return publishedDate; }
    public void setPublishedDate(LocalDate publishedDate) { this.publishedDate = publishedDate; }

    public AdvisoryStatus getStatus() { return status; }
    public void setStatus(AdvisoryStatus status) { this.status = status; }
}
