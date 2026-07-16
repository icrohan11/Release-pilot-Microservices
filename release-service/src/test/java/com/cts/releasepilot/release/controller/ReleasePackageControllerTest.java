package com.cts.releasepilot.release.controller;

import com.cts.releasepilot.release.dto.ReleasePackageResponseDTO;
import com.cts.releasepilot.release.service.ReleasePackageService;
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

class ReleasePackageControllerTest {

    private MockMvc mockMvc;
    private ReleasePackageService releasePackageService;

    @BeforeEach
    void setUp() {
        releasePackageService = Mockito.mock(ReleasePackageService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new ReleasePackageController(releasePackageService)).build();
    }

    @Test
    void getAll_returns200() throws Exception {
        when(releasePackageService.getAll())
                .thenReturn(List.of(new ReleasePackageResponseDTO(), new ReleasePackageResponseDTO()));
        mockMvc.perform(get("/releases/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getById_returns200() throws Exception {
        when(releasePackageService.getById(1L)).thenReturn(new ReleasePackageResponseDTO());
        mockMvc.perform(get("/releases/view/1"))
                .andExpect(status().isOk());
    }

    @Test
    void delete_returns200() throws Exception {
        mockMvc.perform(delete("/releases/delete/1"))
                .andExpect(status().isOk());
        verify(releasePackageService).delete(1L);
    }
}
