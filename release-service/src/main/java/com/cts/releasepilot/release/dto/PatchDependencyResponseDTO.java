package com.cts.releasepilot.release.dto;

import com.cts.releasepilot.release.common.DependencyType;

/** Patch dependency representation returned to clients. */
public class PatchDependencyResponseDTO {

    private Long dependencyID;
    private Long releaseID;
    private Long dependsOnReleaseID;
    private DependencyType dependencyType;
    private String notes;

    public Long getDependencyID() { return dependencyID; }
    public void setDependencyID(Long dependencyID) { this.dependencyID = dependencyID; }

    public Long getReleaseID() { return releaseID; }
    public void setReleaseID(Long releaseID) { this.releaseID = releaseID; }

    public Long getDependsOnReleaseID() { return dependsOnReleaseID; }
    public void setDependsOnReleaseID(Long dependsOnReleaseID) { this.dependsOnReleaseID = dependsOnReleaseID; }

    public DependencyType getDependencyType() { return dependencyType; }
    public void setDependencyType(DependencyType dependencyType) { this.dependencyType = dependencyType; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
