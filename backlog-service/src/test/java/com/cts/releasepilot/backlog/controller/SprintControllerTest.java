package com.cts.releasepilot.backlog.controller;

import com.cts.releasepilot.backlog.dto.SprintRequestDTO;
import com.cts.releasepilot.backlog.dto.SprintResponseDTO;
import com.cts.releasepilot.backlog.service.SprintService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/** Controller test using standalone MockMvc + Mockito (no Spring context / security). */
class SprintControllerTest {

    private MockMvc mockMvc;
    private SprintService sprintService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        sprintService = Mockito.mock(SprintService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new SprintController(sprintService)).build();
    }

    @Test
    void getAll_returns200() throws Exception {
        when(sprintService.getAllSprints())
                .thenReturn(List.of(new SprintResponseDTO(), new SprintResponseDTO()));
        mockMvc.perform(get("/sprints/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void save_returns201() throws Exception {
        SprintRequestDTO req = new SprintRequestDTO();
        req.setProductID(10L);
        req.setSprintName("Sprint 1");

        when(sprintService.createSprint(any(SprintRequestDTO.class)))
                .thenReturn(new SprintResponseDTO());

        mockMvc.perform(post("/sprints/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isCreated());
    }

    @Test
    void delete_returns200() throws Exception {
        mockMvc.perform(delete("/sprints/delete/1"))
                .andExpect(status().isOk());
        verify(sprintService).deleteSprint(1L);
    }
}
