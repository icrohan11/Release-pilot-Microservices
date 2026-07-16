package com.cts.releasepilot.communication.dto;

import com.cts.releasepilot.communication.common.NoteStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/** Payload for creating/updating a release note. */
public class ReleaseNoteRequestDTO {

    @NotNull(message = "ReleaseID is required")
    private Long releaseID;

    @NotBlank(message = "Version number is required")
    private String versionNumber;

    private String summary;
    private String newFeatures;
    private String bugFixes;
    private String knownIssues;
    private String deprecations;
    private String upgradeInstructions;

    private Long authoredByID;

    private NoteStatus status;

    public Long getReleaseID() { return releaseID; }
    public void setReleaseID(Long releaseID) { this.releaseID = releaseID; }

    public String getVersionNumber() { return versionNumber; }
    public void setVersionNumber(String versionNumber) { this.versionNumber = versionNumber; }

    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }

    public String getNewFeatures() { return newFeatures; }
    public void setNewFeatures(String newFeatures) { this.newFeatures = newFeatures; }

    public String getBugFixes() { return bugFixes; }
    public void setBugFixes(String bugFixes) { this.bugFixes = bugFixes; }

    public String getKnownIssues() { return knownIssues; }
    public void setKnownIssues(String knownIssues) { this.knownIssues = knownIssues; }

    public String getDeprecations() { return deprecations; }
    public void setDeprecations(String deprecations) { this.deprecations = deprecations; }

    public String getUpgradeInstructions() { return upgradeInstructions; }
    public void setUpgradeInstructions(String upgradeInstructions) { this.upgradeInstructions = upgradeInstructions; }

    public Long getAuthoredByID() { return authoredByID; }
    public void setAuthoredByID(Long authoredByID) { this.authoredByID = authoredByID; }

    public NoteStatus getStatus() { return status; }
    public void setStatus(NoteStatus status) { this.status = status; }
}
