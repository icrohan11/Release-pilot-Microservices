package com.cts.releasepilot.release.dto;

import com.cts.releasepilot.release.common.DependencyType;
import jakarta.validation.constraints.NotNull;

/** Payload for creating/updating a patch dependency. */
public class PatchDependencyRequestDTO {

    @NotNull(message = "ReleaseID is required")
    private Long releaseID;

    @NotNull(message = "DependsOnReleaseID is required")
    private Long dependsOnReleaseID;

    @NotNull(message = "Dependency type is required")
    private DependencyType dependencyType;

    private String notes;

    public Long getReleaseID() { return releaseID; }
    public void setReleaseID(Long releaseID) { this.releaseID = releaseID; }

    public Long getDependsOnReleaseID() { return dependsOnReleaseID; }
    public void setDependsOnReleaseID(Long dependsOnReleaseID) { this.dependsOnReleaseID = dependsOnReleaseID; }

    public DependencyType getDependencyType() { return dependencyType; }
    public void setDependencyType(DependencyType dependencyType) { this.dependencyType = dependencyType; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
