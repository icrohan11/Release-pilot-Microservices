package com.cts.releasepilot.qa.controller;

import com.cts.releasepilot.qa.common.SuiteType;
import com.cts.releasepilot.qa.dto.TestSuiteRequestDTO;
import com.cts.releasepilot.qa.dto.TestSuiteResponseDTO;
import com.cts.releasepilot.qa.service.TestSuiteService;
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

class TestSuiteControllerTest {

    private MockMvc mockMvc;
    private TestSuiteService testSuiteService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        testSuiteService = Mockito.mock(TestSuiteService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new TestSuiteController(testSuiteService)).build();
    }

    @Test
    void getAll_returns200() throws Exception {
        when(testSuiteService.getAllSuites())
                .thenReturn(List.of(new TestSuiteResponseDTO(), new TestSuiteResponseDTO()));
        mockMvc.perform(get("/test-suites/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void save_returns201() throws Exception {
        TestSuiteRequestDTO req = new TestSuiteRequestDTO();
        req.setProductID(10L);
        req.setSuiteName("Login Regression");
        req.setType(SuiteType.Regression);

        when(testSuiteService.createSuite(any(TestSuiteRequestDTO.class)))
                .thenReturn(new TestSuiteResponseDTO());

        mockMvc.perform(post("/test-suites/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isCreated());
    }

    @Test
    void delete_returns200() throws Exception {
        mockMvc.perform(delete("/test-suites/delete/1"))
                .andExpect(status().isOk());
        verify(testSuiteService).deleteSuite(1L);
    }
}
