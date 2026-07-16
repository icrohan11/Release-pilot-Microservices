package com.cts.releasepilot.communication.controller;

import com.cts.releasepilot.communication.dto.NotificationResponseDTO;
import com.cts.releasepilot.communication.service.NotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class NotificationControllerTest {

    private MockMvc mockMvc;
    private NotificationService notificationService;

    @BeforeEach
    void setUp() {
        notificationService = Mockito.mock(NotificationService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new NotificationController(notificationService)).build();
    }

    @Test
    void getAll_returns200() throws Exception {
        when(notificationService.getAllNotifications())
                .thenReturn(List.of(new NotificationResponseDTO(), new NotificationResponseDTO()));
        mockMvc.perform(get("/notifications/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getByUser_returns200() throws Exception {
        when(notificationService.getNotificationsByUser(42L))
                .thenReturn(List.of(new NotificationResponseDTO()));
        mockMvc.perform(get("/notifications/user/42"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void delete_returns200() throws Exception {
        mockMvc.perform(delete("/notifications/delete/1"))
                .andExpect(status().isOk());
        verify(notificationService).deleteNotification(1L);
    }
}
