package com.cts.releasepilot.communication.dto;

import com.cts.releasepilot.communication.common.AdvisoryStatus;
import com.cts.releasepilot.communication.common.Severity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

/** Payload for creating/updating a customer advisory. */
public class CustomerAdvisoryRequestDTO {

    @NotNull(message = "ReleaseID is required")
    private Long releaseID;

    @NotBlank(message = "Title is required")
    private String title;

    @NotNull(message = "Severity is required")
    private Severity severity;

    private String affectedVersions;

    private String resolution;

    private LocalDate publishedDate;

    private AdvisoryStatus status;

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
