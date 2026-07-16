package com.cts.releasepilot.qa.mapper;

import com.cts.releasepilot.qa.dto.TestExecutionRequestDTO;
import com.cts.releasepilot.qa.dto.TestExecutionResponseDTO;
import com.cts.releasepilot.qa.entity.TestExecution;
import org.springframework.stereotype.Component;

/** Converts between {@link TestExecution} entities and their DTOs. */
@Component
public class TestExecutionMapper {

    public TestExecution toEntity(TestExecutionRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        TestExecution execution = new TestExecution();
        execution.setReleaseID(dto.getReleaseID());
        execution.setSuiteID(dto.getSuiteID());
        execution.setExecutedByID(dto.getExecutedByID());
        execution.setStartDate(dto.getStartDate());
        execution.setEndDate(dto.getEndDate());
        execution.setTotalRun(dto.getTotalRun());
        execution.setPassed(dto.getPassed());
        execution.setFailed(dto.getFailed());
        execution.setSkipped(dto.getSkipped());
        execution.setCoveragePercent(dto.getCoveragePercent());
        execution.setStatus(dto.getStatus());
        return execution;
    }

    public void updateEntity(TestExecution execution, TestExecutionRequestDTO dto) {
        execution.setReleaseID(dto.getReleaseID());
        execution.setSuiteID(dto.getSuiteID());
        execution.setExecutedByID(dto.getExecutedByID());
        execution.setStartDate(dto.getStartDate());
        execution.setEndDate(dto.getEndDate());
        execution.setTotalRun(dto.getTotalRun());
        execution.setPassed(dto.getPassed());
        execution.setFailed(dto.getFailed());
        execution.setSkipped(dto.getSkipped());
        execution.setCoveragePercent(dto.getCoveragePercent());
        execution.setStatus(dto.getStatus());
    }

    public TestExecutionResponseDTO toResponse(TestExecution execution) {
        if (execution == null) {
            return null;
        }
        TestExecutionResponseDTO dto = new TestExecutionResponseDTO();
        dto.setExecutionID(execution.getExecutionID());
        dto.setReleaseID(execution.getReleaseID());
        dto.setSuiteID(execution.getSuiteID());
        dto.setExecutedByID(execution.getExecutedByID());
        dto.setStartDate(execution.getStartDate());
        dto.setEndDate(execution.getEndDate());
        dto.setTotalRun(execution.getTotalRun());
        dto.setPassed(execution.getPassed());
        dto.setFailed(execution.getFailed());
        dto.setSkipped(execution.getSkipped());
        dto.setCoveragePercent(execution.getCoveragePercent());
        dto.setStatus(execution.getStatus());
        return dto;
    }
}
