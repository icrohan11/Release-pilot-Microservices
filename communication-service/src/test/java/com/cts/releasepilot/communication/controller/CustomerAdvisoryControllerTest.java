package com.cts.releasepilot.communication.controller;

import com.cts.releasepilot.communication.dto.CustomerAdvisoryResponseDTO;
import com.cts.releasepilot.communication.service.CustomerAdvisoryService;
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

class CustomerAdvisoryControllerTest {

    private MockMvc mockMvc;
    private CustomerAdvisoryService advisoryService;

    @BeforeEach
    void setUp() {
        advisoryService = Mockito.mock(CustomerAdvisoryService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new CustomerAdvisoryController(advisoryService)).build();
    }

    @Test
    void getAll_returns200() throws Exception {
        when(advisoryService.getAllAdvisories())
                .thenReturn(List.of(new CustomerAdvisoryResponseDTO(), new CustomerAdvisoryResponseDTO()));
        mockMvc.perform(get("/advisories/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getById_returns200() throws Exception {
        when(advisoryService.getAdvisoryById(1L)).thenReturn(new CustomerAdvisoryResponseDTO());
        mockMvc.perform(get("/advisories/view/1"))
                .andExpect(status().isOk());
    }

    @Test
    void delete_returns200() throws Exception {
        mockMvc.perform(delete("/advisories/delete/1"))
                .andExpect(status().isOk());
        verify(advisoryService).deleteAdvisory(1L);
    }
}
