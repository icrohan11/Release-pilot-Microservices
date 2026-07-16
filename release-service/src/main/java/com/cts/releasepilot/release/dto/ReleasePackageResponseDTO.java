package com.cts.releasepilot.release.dto;

import com.cts.releasepilot.release.common.ReleaseStatus;
import com.cts.releasepilot.release.common.ReleaseType;

import java.time.LocalDate;

/** Release package representation returned to clients. */
public class ReleasePackageResponseDTO {

    private Long releaseID;
    private Long productID;
    private String versionNumber;
    private ReleaseType releaseType;
    private String includedItemIDs;
    private String releaseNotesDraft;
    private LocalDate targetReleaseDate;
    private Long releasedByID;
    private ReleaseStatus status;

    public Long getReleaseID() { return releaseID; }
    public void setReleaseID(Long releaseID) { this.releaseID = releaseID; }

    public Long getProductID() { return productID; }
    public void setProductID(Long productID) { this.productID = productID; }

    public String getVersionNumber() { return versionNumber; }
    public void setVersionNumber(String versionNumber) { this.versionNumber = versionNumber; }

    public ReleaseType getReleaseType() { return releaseType; }
    public void setReleaseType(ReleaseType releaseType) { this.releaseType = releaseType; }

    public String getIncludedItemIDs() { return includedItemIDs; }
    public void setIncludedItemIDs(String includedItemIDs) { this.includedItemIDs = includedItemIDs; }

    public String getReleaseNotesDraft() { return releaseNotesDraft; }
    public void setReleaseNotesDraft(String releaseNotesDraft) { this.releaseNotesDraft = releaseNotesDraft; }

    public LocalDate getTargetReleaseDate() { return targetReleaseDate; }
    public void setTargetReleaseDate(LocalDate targetReleaseDate) { this.targetReleaseDate = targetReleaseDate; }

    public Long getReleasedByID() { return releasedByID; }
    public void setReleasedByID(Long releasedByID) { this.releasedByID = releasedByID; }

    public ReleaseStatus getStatus() { return status; }
    public void setStatus(ReleaseStatus status) { this.status = status; }
}
