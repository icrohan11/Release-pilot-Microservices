package com.cts.releasepilot.release.controller;

import com.cts.releasepilot.release.dto.PromotionRequestResponseDTO;
import com.cts.releasepilot.release.service.PromotionRequestService;
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

class PromotionRequestControllerTest {

    private MockMvc mockMvc;
    private PromotionRequestService promotionRequestService;

    @BeforeEach
    void setUp() {
        promotionRequestService = Mockito.mock(PromotionRequestService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new PromotionRequestController(promotionRequestService)).build();
    }

    @Test
    void getAll_returns200() throws Exception {
        when(promotionRequestService.getAll())
                .thenReturn(List.of(new PromotionRequestResponseDTO(), new PromotionRequestResponseDTO()));
        mockMvc.perform(get("/promotions/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getByRelease_returns200() throws Exception {
        when(promotionRequestService.getByRelease(10L))
                .thenReturn(List.of(new PromotionRequestResponseDTO()));
        mockMvc.perform(get("/promotions/release/10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void delete_returns200() throws Exception {
        mockMvc.perform(delete("/promotions/delete/1"))
                .andExpect(status().isOk());
        verify(promotionRequestService).delete(1L);
    }
}
