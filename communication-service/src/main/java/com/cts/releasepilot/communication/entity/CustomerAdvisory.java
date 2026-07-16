package com.cts.releasepilot.communication.entity;

import com.cts.releasepilot.communication.common.AdvisoryStatus;
import com.cts.releasepilot.communication.common.Severity;
import jakarta.persistence.*;

import java.time.LocalDate;

/** Persistent advisory sent to customers about a release. */
@Entity
@Table(name = "customer_advisories")
public class CustomerAdvisory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long advisoryID;

    private Long releaseID;

    private String title;

    @Enumerated(EnumType.STRING)
    private Severity severity;

    private String affectedVersions;

    @Column(columnDefinition = "TEXT")
    private String resolution;

    private LocalDate publishedDate;

    @Enumerated(EnumType.STRING)
    private AdvisoryStatus status;

    public CustomerAdvisory() {
    }

    public CustomerAdvisory(Long advisoryID, Long releaseID, String title, Severity severity,
                            String affectedVersions, String resolution, LocalDate publishedDate,
                            AdvisoryStatus status) {
        this.advisoryID = advisoryID;
        this.releaseID = releaseID;
        this.title = title;
        this.severity = severity;
        this.affectedVersions = affectedVersions;
        this.resolution = resolution;
        this.publishedDate = publishedDate;
        this.status = status;
    }

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
