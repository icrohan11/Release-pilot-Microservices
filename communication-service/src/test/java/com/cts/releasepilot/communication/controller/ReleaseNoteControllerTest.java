package com.cts.releasepilot.communication.controller;

import com.cts.releasepilot.communication.dto.ReleaseNoteResponseDTO;
import com.cts.releasepilot.communication.service.ReleaseNoteService;
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

class ReleaseNoteControllerTest {

    private MockMvc mockMvc;
    private ReleaseNoteService releaseNoteService;

    @BeforeEach
    void setUp() {
        releaseNoteService = Mockito.mock(ReleaseNoteService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new ReleaseNoteController(releaseNoteService)).build();
    }

    @Test
    void getAll_returns200() throws Exception {
        when(releaseNoteService.getAllReleaseNotes())
                .thenReturn(List.of(new ReleaseNoteResponseDTO(), new ReleaseNoteResponseDTO()));
        mockMvc.perform(get("/release-notes/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getById_returns200() throws Exception {
        when(releaseNoteService.getReleaseNoteById(1L)).thenReturn(new ReleaseNoteResponseDTO());
        mockMvc.perform(get("/release-notes/view/1"))
                .andExpect(status().isOk());
    }

    @Test
    void delete_returns200() throws Exception {
        mockMvc.perform(delete("/release-notes/delete/1"))
                .andExpect(status().isOk());
        verify(releaseNoteService).deleteReleaseNote(1L);
    }
}
