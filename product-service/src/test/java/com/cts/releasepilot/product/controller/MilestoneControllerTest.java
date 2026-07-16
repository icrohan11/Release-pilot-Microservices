package com.cts.releasepilot.product.controller;

import com.cts.releasepilot.product.common.MilestoneStatus;
import com.cts.releasepilot.product.dto.RoadmapMilestoneRequestDTO;
import com.cts.releasepilot.product.dto.RoadmapMilestoneResponseDTO;
import com.cts.releasepilot.product.service.RoadmapMilestoneService;
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

/** Controller test using standalone MockMvc + Mockito (no Spring context / security). */
class MilestoneControllerTest {

    private MockMvc mockMvc;
    private RoadmapMilestoneService milestoneService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        milestoneService = Mockito.mock(RoadmapMilestoneService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new MilestoneController(milestoneService)).build();
    }

    @Test
    void getAll_returns200() throws Exception {
        when(milestoneService.getAllMilestones())
                .thenReturn(List.of(new RoadmapMilestoneResponseDTO(), new RoadmapMilestoneResponseDTO()));
        mockMvc.perform(get("/milestones/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void save_returns201() throws Exception {
        RoadmapMilestoneRequestDTO req = new RoadmapMilestoneRequestDTO();
        req.setProductID(10L);
        req.setMilestoneName("GA Launch");
        req.setStatus(MilestoneStatus.Planned);

        when(milestoneService.saveMilestone(any(RoadmapMilestoneRequestDTO.class)))
                .thenReturn(new RoadmapMilestoneResponseDTO());

        mockMvc.perform(post("/milestones/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isCreated());
    }

    @Test
    void delete_returns200() throws Exception {
        mockMvc.perform(delete("/milestones/delete/1"))
                .andExpect(status().isOk());
        verify(milestoneService).deleteMilestone(1L);
    }
}
