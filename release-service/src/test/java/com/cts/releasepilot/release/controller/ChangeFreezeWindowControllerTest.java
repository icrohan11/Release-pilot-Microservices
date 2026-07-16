package com.cts.releasepilot.release.controller;

import com.cts.releasepilot.release.dto.ChangeFreezeWindowResponseDTO;
import com.cts.releasepilot.release.service.ChangeFreezeWindowService;
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

class ChangeFreezeWindowControllerTest {

    private MockMvc mockMvc;
    private ChangeFreezeWindowService changeFreezeWindowService;

    @BeforeEach
    void setUp() {
        changeFreezeWindowService = Mockito.mock(ChangeFreezeWindowService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new ChangeFreezeWindowController(changeFreezeWindowService)).build();
    }

    @Test
    void getAll_returns200() throws Exception {
        when(changeFreezeWindowService.getAll())
                .thenReturn(List.of(new ChangeFreezeWindowResponseDTO(), new ChangeFreezeWindowResponseDTO()));
        mockMvc.perform(get("/freezes/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getByProduct_returns200() throws Exception {
        when(changeFreezeWindowService.getByProduct(10L))
                .thenReturn(List.of(new ChangeFreezeWindowResponseDTO()));
        mockMvc.perform(get("/freezes/product/10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void delete_returns200() throws Exception {
        mockMvc.perform(delete("/freezes/delete/1"))
                .andExpect(status().isOk());
        verify(changeFreezeWindowService).delete(1L);
    }
}
