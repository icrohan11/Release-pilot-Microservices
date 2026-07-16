package com.cts.releasepilot.release.entity;

import com.cts.releasepilot.release.common.ReleaseStatus;
import com.cts.releasepilot.release.common.ReleaseType;
import jakarta.persistence.*;

import java.time.LocalDate;

/** A packaged release of a product, bundling a set of backlog items for delivery. */
@Entity
@Table(name = "release_packages")
public class ReleasePackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long releaseID;

    private Long productID;

    private String versionNumber;

    @Enumerated(EnumType.STRING)
    private ReleaseType releaseType;

    private String includedItemIDs;

    @Column(columnDefinition = "TEXT")
    private String releaseNotesDraft;

    private LocalDate targetReleaseDate;

    private Long releasedByID;

    @Enumerated(EnumType.STRING)
    private ReleaseStatus status;

    public ReleasePackage() {
    }

    public ReleasePackage(Long releaseID, Long productID, String versionNumber, ReleaseType releaseType,
                          String includedItemIDs, String releaseNotesDraft, LocalDate targetReleaseDate,
                          Long releasedByID, ReleaseStatus status) {
        this.releaseID = releaseID;
        this.productID = productID;
        this.versionNumber = versionNumber;
        this.releaseType = releaseType;
        this.includedItemIDs = includedItemIDs;
        this.releaseNotesDraft = releaseNotesDraft;
        this.targetReleaseDate = targetReleaseDate;
        this.releasedByID = releasedByID;
        this.status = status;
    }

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
