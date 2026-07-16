package com.cts.releasepilot.qa.controller;

import com.cts.releasepilot.qa.common.GateType;
import com.cts.releasepilot.qa.dto.ReleaseGateRequestDTO;
import com.cts.releasepilot.qa.dto.ReleaseGateResponseDTO;
import com.cts.releasepilot.qa.service.ReleaseGateService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ReleaseGateControllerTest {

    private MockMvc mockMvc;
    private ReleaseGateService releaseGateService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        releaseGateService = Mockito.mock(ReleaseGateService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new ReleaseGateController(releaseGateService)).build();
    }

    @Test
    void getAll_returns200() throws Exception {
        when(releaseGateService.getAllGates())
                .thenReturn(List.of(new ReleaseGateResponseDTO(), new ReleaseGateResponseDTO()));
        mockMvc.perform(get("/gates/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void save_returns201() throws Exception {
        ReleaseGateRequestDTO req = new ReleaseGateRequestDTO();
        req.setReleaseID(100L);
        req.setGateName("Test Pass Rate");
        req.setGateType(GateType.TestPassRate);

        when(releaseGateService.createGate(any(ReleaseGateRequestDTO.class)))
                .thenReturn(new ReleaseGateResponseDTO());

        mockMvc.perform(post("/gates/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isCreated());
    }

    @Test
    void delete_returns200() throws Exception {
        mockMvc.perform(delete("/gates/delete/1"))
                .andExpect(status().isOk());
        verify(releaseGateService).deleteGate(1L);
    }
}
