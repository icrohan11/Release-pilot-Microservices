package com.cts.releasepilot.communication.service.impl;

import com.cts.releasepilot.communication.common.NotificationCategory;
import com.cts.releasepilot.communication.common.NotificationStatus;
import com.cts.releasepilot.communication.dto.NotificationRequestDTO;
import com.cts.releasepilot.communication.dto.NotificationResponseDTO;
import com.cts.releasepilot.communication.entity.Notification;
import com.cts.releasepilot.communication.exception.ResourceNotFoundException;
import com.cts.releasepilot.communication.mapper.NotificationMapper;
import com.cts.releasepilot.communication.repository.NotificationRepository;
import com.cts.releasepilot.communication.service.NotificationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    public NotificationServiceImpl(NotificationRepository notificationRepository,
                                   NotificationMapper notificationMapper) {
        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<NotificationResponseDTO> getAllNotifications() {
        return notificationRepository.findAll().stream().map(notificationMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public NotificationResponseDTO getNotificationById(Long id) {
        return notificationMapper.toResponse(findOrThrow(id));
    }

    @Override
    @Transactional
    public NotificationResponseDTO createNotification(NotificationRequestDTO dto) {
        Notification notification = notificationMapper.toEntity(dto);
        notification.setNotificationID(null);
        if (notification.getCreatedDate() == null) {
            notification.setCreatedDate(LocalDateTime.now());
        }
        if (notification.getStatus() == null) {
            notification.setStatus(NotificationStatus.Unread);
        }
        return notificationMapper.toResponse(notificationRepository.save(notification));
    }

    @Override
    @Transactional
    public NotificationResponseDTO updateNotification(Long id, NotificationRequestDTO dto) {
        Notification notification = findOrThrow(id);
        notification.setUserID(dto.getUserID());
        notification.setMessage(dto.getMessage());
        notification.setCategory(dto.getCategory());
        if (dto.getStatus() != null) {
            notification.setStatus(dto.getStatus());
        }
        if (dto.getCreatedDate() != null) {
            notification.setCreatedDate(dto.getCreatedDate());
        }
        return notificationMapper.toResponse(notificationRepository.save(notification));
    }

    @Override
    @Transactional
    public void deleteNotification(Long id) {
        Notification notification = findOrThrow(id);
        notificationRepository.delete(notification);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NotificationResponseDTO> getNotificationsByUser(Long userID) {
        return notificationRepository.findByUserID(userID).stream()
                .map(notificationMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<NotificationResponseDTO> getNotificationsByStatus(NotificationStatus status) {
        return notificationRepository.findByStatus(status).stream()
                .map(notificationMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<NotificationResponseDTO> getNotificationsByCategory(NotificationCategory category) {
        return notificationRepository.findByCategory(category).stream()
                .map(notificationMapper::toResponse).toList();
    }

    private Notification findOrThrow(Long id) {
        return notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found with ID: " + id));
    }
}
