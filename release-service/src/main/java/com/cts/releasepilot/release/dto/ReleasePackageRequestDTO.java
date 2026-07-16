package com.cts.releasepilot.release.dto;

import com.cts.releasepilot.release.common.ReleaseStatus;
import com.cts.releasepilot.release.common.ReleaseType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

/** Payload for creating/updating a release package. */
public class ReleasePackageRequestDTO {

    @NotNull(message = "ProductID is required")
    private Long productID;

    @NotBlank(message = "Version number is required")
    private String versionNumber;

    @NotNull(message = "Release type is required")
    private ReleaseType releaseType;

    private String includedItemIDs;

    private String releaseNotesDraft;

    private LocalDate targetReleaseDate;

    private Long releasedByID;

    private ReleaseStatus status;

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
