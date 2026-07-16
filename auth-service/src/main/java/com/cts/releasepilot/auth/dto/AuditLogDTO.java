package com.cts.releasepilot.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

/** DTO for reading/writing audit records. */
public class AuditLogDTO {

    private Long auditID;

    @NotNull(message = "UserID is required")
    private Long userID;

    @NotBlank(message = "Action is required")
    private String action;

    @NotBlank(message = "EntityType is required")
    private String entityType;

    private Long recordID;

    private LocalDateTime timestamp;

    public Long getAuditID() { return auditID; }
    public void setAuditID(Long auditID) { this.auditID = auditID; }

    public Long getUserID() { return userID; }
    public void setUserID(Long userID) { this.userID = userID; }

    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }

    public String getEntityType() { return entityType; }
    public void setEntityType(String entityType) { this.entityType = entityType; }

    public Long getRecordID() { return recordID; }
    public void setRecordID(Long recordID) { this.recordID = recordID; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
