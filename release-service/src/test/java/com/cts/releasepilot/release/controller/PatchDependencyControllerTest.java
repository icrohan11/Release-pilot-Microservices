package com.cts.releasepilot.release.controller;

import com.cts.releasepilot.release.dto.PatchDependencyResponseDTO;
import com.cts.releasepilot.release.service.PatchDependencyService;
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

class PatchDependencyControllerTest {

    private MockMvc mockMvc;
    private PatchDependencyService patchDependencyService;

    @BeforeEach
    void setUp() {
        patchDependencyService = Mockito.mock(PatchDependencyService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new PatchDependencyController(patchDependencyService)).build();
    }

    @Test
    void getAll_returns200() throws Exception {
        when(patchDependencyService.getAll())
                .thenReturn(List.of(new PatchDependencyResponseDTO(), new PatchDependencyResponseDTO()));
        mockMvc.perform(get("/patches/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getByRelease_returns200() throws Exception {
        when(patchDependencyService.getByRelease(10L))
                .thenReturn(List.of(new PatchDependencyResponseDTO()));
        mockMvc.perform(get("/patches/release/10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void delete_returns200() throws Exception {
        mockMvc.perform(delete("/patches/delete/1"))
                .andExpect(status().isOk());
        verify(patchDependencyService).delete(1L);
    }
}
