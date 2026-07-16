package com.cts.releasepilot.communication.mapper;

import com.cts.releasepilot.communication.dto.NotificationRequestDTO;
import com.cts.releasepilot.communication.dto.NotificationResponseDTO;
import com.cts.releasepilot.communication.entity.Notification;
import org.springframework.stereotype.Component;

/** Converts between {@link Notification} entities and their DTOs. */
@Component
public class NotificationMapper {

    public Notification toEntity(NotificationRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        Notification notification = new Notification();
        notification.setUserID(dto.getUserID());
        notification.setMessage(dto.getMessage());
        notification.setCategory(dto.getCategory());
        notification.setStatus(dto.getStatus());
        notification.setCreatedDate(dto.getCreatedDate());
        return notification;
    }

    public NotificationResponseDTO toResponse(Notification notification) {
        if (notification == null) {
            return null;
        }
        NotificationResponseDTO dto = new NotificationResponseDTO();
        dto.setNotificationID(notification.getNotificationID());
        dto.setUserID(notification.getUserID());
        dto.setMessage(notification.getMessage());
        dto.setCategory(notification.getCategory());
        dto.setStatus(notification.getStatus());
        dto.setCreatedDate(notification.getCreatedDate());
        return dto;
    }
}
