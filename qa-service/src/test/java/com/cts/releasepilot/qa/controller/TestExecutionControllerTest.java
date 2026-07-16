package com.cts.releasepilot.qa.controller;

import com.cts.releasepilot.qa.dto.TestExecutionRequestDTO;
import com.cts.releasepilot.qa.dto.TestExecutionResponseDTO;
import com.cts.releasepilot.qa.service.TestExecutionService;
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

class TestExecutionControllerTest {

    private MockMvc mockMvc;
    private TestExecutionService testExecutionService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        testExecutionService = Mockito.mock(TestExecutionService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new TestExecutionController(testExecutionService)).build();
    }

    @Test
    void getAll_returns200() throws Exception {
        when(testExecutionService.getAllExecutions())
                .thenReturn(List.of(new TestExecutionResponseDTO(), new TestExecutionResponseDTO()));
        mockMvc.perform(get("/test-executions/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void save_returns201() throws Exception {
        TestExecutionRequestDTO req = new TestExecutionRequestDTO();
        req.setReleaseID(100L);
        req.setSuiteID(10L);

        when(testExecutionService.createExecution(any(TestExecutionRequestDTO.class)))
                .thenReturn(new TestExecutionResponseDTO());

        mockMvc.perform(post("/test-executions/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isCreated());
    }

    @Test
    void delete_returns200() throws Exception {
        mockMvc.perform(delete("/test-executions/delete/1"))
                .andExpect(status().isOk());
        verify(testExecutionService).deleteExecution(1L);
    }
}
