package com.cts.releasepilot.release.dto;

import com.cts.releasepilot.release.common.PromotionStatus;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

/** Payload for creating/updating a promotion request. */
public class PromotionRequestRequestDTO {

    @NotNull(message = "ReleaseID is required")
    private Long releaseID;

    @NotNull(message = "FromEnvID is required")
    private Long fromEnvID;

    @NotNull(message = "ToEnvID is required")
    private Long toEnvID;

    private Long requestedByID;

    private Long approvedByID;

    private LocalDateTime scheduledDateTime;

    private LocalDateTime actualDateTime;

    private PromotionStatus status;

    public Long getReleaseID() { return releaseID; }
    public void setReleaseID(Long releaseID) { this.releaseID = releaseID; }

    public Long getFromEnvID() { return fromEnvID; }
    public void setFromEnvID(Long fromEnvID) { this.fromEnvID = fromEnvID; }

    public Long getToEnvID() { return toEnvID; }
    public void setToEnvID(Long toEnvID) { this.toEnvID = toEnvID; }

    public Long getRequestedByID() { return requestedByID; }
    public void setRequestedByID(Long requestedByID) { this.requestedByID = requestedByID; }

    public Long getApprovedByID() { return approvedByID; }
    public void setApprovedByID(Long approvedByID) { this.approvedByID = approvedByID; }

    public LocalDateTime getScheduledDateTime() { return scheduledDateTime; }
    public void setScheduledDateTime(LocalDateTime scheduledDateTime) { this.scheduledDateTime = scheduledDateTime; }

    public LocalDateTime getActualDateTime() { return actualDateTime; }
    public void setActualDateTime(LocalDateTime actualDateTime) { this.actualDateTime = actualDateTime; }

    public PromotionStatus getStatus() { return status; }
    public void setStatus(PromotionStatus status) { this.status = status; }
}
