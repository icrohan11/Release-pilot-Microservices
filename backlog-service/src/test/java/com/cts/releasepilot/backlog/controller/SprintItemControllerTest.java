package com.cts.releasepilot.backlog.controller;

import com.cts.releasepilot.backlog.dto.SprintItemRequestDTO;
import com.cts.releasepilot.backlog.dto.SprintItemResponseDTO;
import com.cts.releasepilot.backlog.service.SprintItemService;
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
class SprintItemControllerTest {

    private MockMvc mockMvc;
    private SprintItemService sprintItemService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        sprintItemService = Mockito.mock(SprintItemService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new SprintItemController(sprintItemService)).build();
    }

    @Test
    void getAll_returns200() throws Exception {
        when(sprintItemService.getAllSprintItems())
                .thenReturn(List.of(new SprintItemResponseDTO(), new SprintItemResponseDTO()));
        mockMvc.perform(get("/sprint-items/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void save_returns201() throws Exception {
        SprintItemRequestDTO req = new SprintItemRequestDTO();
        req.setSprintID(1L);
        req.setBacklogItemID(2L);

        when(sprintItemService.createSprintItem(any(SprintItemRequestDTO.class)))
                .thenReturn(new SprintItemResponseDTO());

        mockMvc.perform(post("/sprint-items/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isCreated());
    }

    @Test
    void delete_returns200() throws Exception {
        mockMvc.perform(delete("/sprint-items/delete/1"))
                .andExpect(status().isOk());
        verify(sprintItemService).deleteSprintItem(1L);
    }
}
