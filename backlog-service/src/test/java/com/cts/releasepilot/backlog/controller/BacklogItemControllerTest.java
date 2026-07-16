package com.cts.releasepilot.backlog.controller;

import com.cts.releasepilot.backlog.common.BacklogType;
import com.cts.releasepilot.backlog.common.Priority;
import com.cts.releasepilot.backlog.dto.BacklogItemRequestDTO;
import com.cts.releasepilot.backlog.dto.BacklogItemResponseDTO;
import com.cts.releasepilot.backlog.service.BacklogItemService;
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
class BacklogItemControllerTest {

    private MockMvc mockMvc;
    private BacklogItemService backlogItemService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        backlogItemService = Mockito.mock(BacklogItemService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new BacklogItemController(backlogItemService)).build();
    }

    @Test
    void getAll_returns200() throws Exception {
        when(backlogItemService.getAllBacklogItems())
                .thenReturn(List.of(new BacklogItemResponseDTO(), new BacklogItemResponseDTO()));
        mockMvc.perform(get("/backlog/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void save_returns201() throws Exception {
        BacklogItemRequestDTO req = new BacklogItemRequestDTO();
        req.setProductID(10L);
        req.setTitle("Add login");
        req.setType(BacklogType.Feature);
        req.setPriority(Priority.High);

        when(backlogItemService.createBacklogItem(any(BacklogItemRequestDTO.class)))
                .thenReturn(new BacklogItemResponseDTO());

        mockMvc.perform(post("/backlog/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isCreated());
    }

    @Test
    void delete_returns200() throws Exception {
        mockMvc.perform(delete("/backlog/delete/1"))
                .andExpect(status().isOk());
        verify(backlogItemService).deleteBacklogItem(1L);
    }
}
