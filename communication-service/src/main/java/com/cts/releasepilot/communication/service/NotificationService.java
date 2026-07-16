package com.cts.releasepilot.communication.service;

import com.cts.releasepilot.communication.common.NotificationCategory;
import com.cts.releasepilot.communication.common.NotificationStatus;
import com.cts.releasepilot.communication.dto.NotificationRequestDTO;
import com.cts.releasepilot.communication.dto.NotificationResponseDTO;

import java.util.List;

/** User notification operations. */
public interface NotificationService {

    List<NotificationResponseDTO> getAllNotifications();

    NotificationResponseDTO getNotificationById(Long id);

    NotificationResponseDTO createNotification(NotificationRequestDTO dto);

    NotificationResponseDTO updateNotification(Long id, NotificationRequestDTO dto);

    void deleteNotification(Long id);

    List<NotificationResponseDTO> getNotificationsByUser(Long userID);

    List<NotificationResponseDTO> getNotificationsByStatus(NotificationStatus status);

    List<NotificationResponseDTO> getNotificationsByCategory(NotificationCategory category);
}
