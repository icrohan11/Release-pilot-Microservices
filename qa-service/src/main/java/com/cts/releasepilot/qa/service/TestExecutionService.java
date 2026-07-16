package com.cts.releasepilot.qa.service;

import com.cts.releasepilot.qa.common.ExecutionStatus;
import com.cts.releasepilot.qa.dto.TestExecutionRequestDTO;
import com.cts.releasepilot.qa.dto.TestExecutionResponseDTO;

import java.util.List;

/** Test execution management operations. */
public interface TestExecutionService {

    List<TestExecutionResponseDTO> getAllExecutions();

    TestExecutionResponseDTO getExecutionById(Long id);

    TestExecutionResponseDTO createExecution(TestExecutionRequestDTO dto);

    TestExecutionResponseDTO updateExecution(Long id, TestExecutionRequestDTO dto);

    void deleteExecution(Long id);

    List<TestExecutionResponseDTO> getExecutionsByRelease(Long releaseID);

    List<TestExecutionResponseDTO> getExecutionsBySuite(Long suiteID);

    List<TestExecutionResponseDTO> getExecutionsByStatus(ExecutionStatus status);
}
