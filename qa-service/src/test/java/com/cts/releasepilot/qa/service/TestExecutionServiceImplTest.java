package com.cts.releasepilot.qa.service;

import com.cts.releasepilot.qa.client.ReleaseClientService;
import com.cts.releasepilot.qa.common.ExecutionStatus;
import com.cts.releasepilot.qa.dto.ReleaseDTO;
import com.cts.releasepilot.qa.dto.TestExecutionRequestDTO;
import com.cts.releasepilot.qa.dto.TestExecutionResponseDTO;
import com.cts.releasepilot.qa.entity.TestExecution;
import com.cts.releasepilot.qa.exception.ResourceNotFoundException;
import com.cts.releasepilot.qa.mapper.TestExecutionMapper;
import com.cts.releasepilot.qa.repository.TestExecutionRepository;
import com.cts.releasepilot.qa.service.impl.TestExecutionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestExecutionServiceImplTest {

    @Mock private TestExecutionRepository testExecutionRepository;
    @Mock private TestExecutionMapper testExecutionMapper;
    @Mock private ReleaseClientService releaseClientService;
    @InjectMocks private TestExecutionServiceImpl testExecutionService;

    private TestExecution execution;

    @BeforeEach
    void setUp() {
        execution = new TestExecution(1L, 100L, 10L, 5L, null, null,
                20, 18, 1, 1, 92.5, ExecutionStatus.Completed);
    }

    @Test
    void getExecutionById_found() {
        when(testExecutionRepository.findById(1L)).thenReturn(Optional.of(execution));
        when(testExecutionMapper.toResponse(execution)).thenReturn(new TestExecutionResponseDTO());
        assertNotNull(testExecutionService.getExecutionById(1L));
    }

    @Test
    void getExecutionById_notFound_throws() {
        when(testExecutionRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> testExecutionService.getExecutionById(99L));
    }

    @Test
    void createExecution_validatesReleaseThenSaves() {
        TestExecutionRequestDTO dto = new TestExecutionRequestDTO();
        dto.setReleaseID(100L);
        dto.setSuiteID(10L);

        when(releaseClientService.getRelease(100L)).thenReturn(new ReleaseDTO(100L, "1.0.0", "Planned"));
        when(testExecutionMapper.toEntity(dto)).thenReturn(execution);
        when(testExecutionRepository.save(execution)).thenReturn(execution);
        when(testExecutionMapper.toResponse(execution)).thenReturn(new TestExecutionResponseDTO());

        assertNotNull(testExecutionService.createExecution(dto));
        verify(releaseClientService).getRelease(100L);
        verify(testExecutionRepository).save(execution);
    }

    @Test
    void createExecution_releaseServiceDegraded_stillSaves() {
        TestExecutionRequestDTO dto = new TestExecutionRequestDTO();
        dto.setReleaseID(100L);
        dto.setSuiteID(10L);

        // Circuit breaker fallback already produced a degraded release response.
        when(releaseClientService.getRelease(100L)).thenReturn(new ReleaseDTO(100L, "UNKNOWN", "UNAVAILABLE"));
        when(testExecutionMapper.toEntity(dto)).thenReturn(execution);
        when(testExecutionRepository.save(execution)).thenReturn(execution);
        when(testExecutionMapper.toResponse(execution)).thenReturn(new TestExecutionResponseDTO());

        assertNotNull(testExecutionService.createExecution(dto));
        verify(testExecutionRepository).save(execution);
    }

    @Test
    void getAllExecutions_returnsMappedList() {
        when(testExecutionRepository.findAll()).thenReturn(List.of(execution));
        when(testExecutionMapper.toResponse(execution)).thenReturn(new TestExecutionResponseDTO());
        assertEquals(1, testExecutionService.getAllExecutions().size());
    }

    @Test
    void deleteExecution_notFound_throws() {
        when(testExecutionRepository.findById(5L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> testExecutionService.deleteExecution(5L));
        verify(testExecutionRepository, never()).delete(any());
    }
}
