package com.cts.releasepilot.release.controller;

import com.cts.releasepilot.release.dto.EnvironmentResponseDTO;
import com.cts.releasepilot.release.service.EnvironmentService;
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

class EnvironmentControllerTest {

    private MockMvc mockMvc;
    private EnvironmentService environmentService;

    @BeforeEach
    void setUp() {
        environmentService = Mockito.mock(EnvironmentService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new EnvironmentController(environmentService)).build();
    }

    @Test
    void getAll_returns200() throws Exception {
        when(environmentService.getAll())
                .thenReturn(List.of(new EnvironmentResponseDTO(), new EnvironmentResponseDTO()));
        mockMvc.perform(get("/environments/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getById_returns200() throws Exception {
        when(environmentService.getById(1L)).thenReturn(new EnvironmentResponseDTO());
        mockMvc.perform(get("/environments/view/1"))
                .andExpect(status().isOk());
    }

    @Test
    void delete_returns200() throws Exception {
        mockMvc.perform(delete("/environments/delete/1"))
                .andExpect(status().isOk());
        verify(environmentService).delete(1L);
    }
}
