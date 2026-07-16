package com.cts.releasepilot.communication.dto;

/** Minimal view of a release as returned by the release-service via Feign. */
public class ReleaseDTO {

    private Long releaseID;
    private String versionNumber;
    private String status;

    public ReleaseDTO() {
    }

    public ReleaseDTO(Long releaseID, String versionNumber, String status) {
        this.releaseID = releaseID;
        this.versionNumber = versionNumber;
        this.status = status;
    }

    public Long getReleaseID() { return releaseID; }
    public void setReleaseID(Long releaseID) { this.releaseID = releaseID; }

    public String getVersionNumber() { return versionNumber; }
    public void setVersionNumber(String versionNumber) { this.versionNumber = versionNumber; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
