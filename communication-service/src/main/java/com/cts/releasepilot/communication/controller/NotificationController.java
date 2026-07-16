package com.cts.releasepilot.communication.controller;

import com.cts.releasepilot.communication.common.NotificationCategory;
import com.cts.releasepilot.communication.common.NotificationStatus;
import com.cts.releasepilot.communication.dto.NotificationRequestDTO;
import com.cts.releasepilot.communication.dto.NotificationResponseDTO;
import com.cts.releasepilot.communication.service.NotificationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** User notification endpoints (any authenticated user). */
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<NotificationResponseDTO>> getAll() {
        return ResponseEntity.ok(notificationService.getAllNotifications());
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<NotificationResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(notificationService.getNotificationById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<NotificationResponseDTO> save(@Valid @RequestBody NotificationRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(notificationService.createNotification(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<NotificationResponseDTO> update(@PathVariable Long id,
                                                          @Valid @RequestBody NotificationRequestDTO dto) {
        return ResponseEntity.ok(notificationService.updateNotification(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        notificationService.deleteNotification(id);
        return ResponseEntity.ok("Notification deleted successfully");
    }

    @GetMapping("/user/{userID}")
    public ResponseEntity<List<NotificationResponseDTO>> getByUser(@PathVariable Long userID) {
        return ResponseEntity.ok(notificationService.getNotificationsByUser(userID));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<NotificationResponseDTO>> getByStatus(@PathVariable NotificationStatus status) {
        return ResponseEntity.ok(notificationService.getNotificationsByStatus(status));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<NotificationResponseDTO>> getByCategory(@PathVariable NotificationCategory category) {
        return ResponseEntity.ok(notificationService.getNotificationsByCategory(category));
    }
}
