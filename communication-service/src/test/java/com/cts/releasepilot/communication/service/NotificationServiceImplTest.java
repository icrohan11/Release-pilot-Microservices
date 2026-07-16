package com.cts.releasepilot.communication.service;

import com.cts.releasepilot.communication.common.NotificationCategory;
import com.cts.releasepilot.communication.common.NotificationStatus;
import com.cts.releasepilot.communication.dto.NotificationRequestDTO;
import com.cts.releasepilot.communication.dto.NotificationResponseDTO;
import com.cts.releasepilot.communication.entity.Notification;
import com.cts.releasepilot.communication.exception.ResourceNotFoundException;
import com.cts.releasepilot.communication.mapper.NotificationMapper;
import com.cts.releasepilot.communication.repository.NotificationRepository;
import com.cts.releasepilot.communication.service.impl.NotificationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificationServiceImplTest {

    @Mock private NotificationRepository notificationRepository;
    @Mock private NotificationMapper notificationMapper;
    @InjectMocks private NotificationServiceImpl notificationService;

    private Notification notification;

    @BeforeEach
    void setUp() {
        notification = new Notification(1L, 42L, "Release published",
                NotificationCategory.Release, NotificationStatus.Unread, LocalDateTime.now());
    }

    @Test
    void getNotificationById_found() {
        when(notificationRepository.findById(1L)).thenReturn(Optional.of(notification));
        when(notificationMapper.toResponse(notification)).thenReturn(new NotificationResponseDTO());
        assertNotNull(notificationService.getNotificationById(1L));
    }

    @Test
    void getNotificationById_notFound_throws() {
        when(notificationRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> notificationService.getNotificationById(99L));
    }

    @Test
    void createNotification_defaultsCreatedDateAndStatus() {
        NotificationRequestDTO dto = new NotificationRequestDTO();
        dto.setUserID(42L);
        dto.setMessage("Release published");
        dto.setCategory(NotificationCategory.Release);

        Notification toSave = new Notification();
        toSave.setUserID(42L);
        toSave.setMessage("Release published");
        toSave.setCategory(NotificationCategory.Release);

        when(notificationMapper.toEntity(dto)).thenReturn(toSave);
        when(notificationRepository.save(toSave)).thenReturn(notification);
        when(notificationMapper.toResponse(notification)).thenReturn(new NotificationResponseDTO());

        assertNotNull(notificationService.createNotification(dto));
        assertNotNull(toSave.getCreatedDate());
        assertEquals(NotificationStatus.Unread, toSave.getStatus());
        verify(notificationRepository).save(toSave);
    }

    @Test
    void getAllNotifications_returnsMappedList() {
        when(notificationRepository.findAll()).thenReturn(List.of(notification));
        when(notificationMapper.toResponse(notification)).thenReturn(new NotificationResponseDTO());
        assertEquals(1, notificationService.getAllNotifications().size());
    }
}
