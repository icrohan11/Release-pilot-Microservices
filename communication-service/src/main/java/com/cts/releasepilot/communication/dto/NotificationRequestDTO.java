package com.cts.releasepilot.communication.dto;

import com.cts.releasepilot.communication.common.NotificationCategory;
import com.cts.releasepilot.communication.common.NotificationStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

/** Payload for creating a notification. */
public class NotificationRequestDTO {

    @NotNull(message = "UserID is required")
    private Long userID;

    @NotBlank(message = "Message is required")
    private String message;

    @NotNull(message = "Category is required")
    private NotificationCategory category;

    private NotificationStatus status;

    private LocalDateTime createdDate;

    public Long getUserID() { return userID; }
    public void setUserID(Long userID) { this.userID = userID; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public NotificationCategory getCategory() { return category; }
    public void setCategory(NotificationCategory category) { this.category = category; }

    public NotificationStatus getStatus() { return status; }
    public void setStatus(NotificationStatus status) { this.status = status; }

    public LocalDateTime getCreatedDate() { return createdDate; }
    public void setCreatedDate(LocalDateTime createdDate) { this.createdDate = createdDate; }
}
