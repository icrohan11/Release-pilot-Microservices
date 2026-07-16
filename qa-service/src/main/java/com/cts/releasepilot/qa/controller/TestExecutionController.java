package com.cts.releasepilot.qa.controller;

import com.cts.releasepilot.qa.common.ExecutionStatus;
import com.cts.releasepilot.qa.dto.TestExecutionRequestDTO;
import com.cts.releasepilot.qa.dto.TestExecutionResponseDTO;
import com.cts.releasepilot.qa.service.TestExecutionService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** Test execution management (QAEngineer / ReleaseManager / Admin). */
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/test-executions")
public class TestExecutionController {

    private final TestExecutionService testExecutionService;

    public TestExecutionController(TestExecutionService testExecutionService) {
        this.testExecutionService = testExecutionService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<TestExecutionResponseDTO>> getAll() {
        return ResponseEntity.ok(testExecutionService.getAllExecutions());
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<TestExecutionResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(testExecutionService.getExecutionById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<TestExecutionResponseDTO> save(@Valid @RequestBody TestExecutionRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(testExecutionService.createExecution(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TestExecutionResponseDTO> update(@PathVariable Long id,
                                                           @Valid @RequestBody TestExecutionRequestDTO dto) {
        return ResponseEntity.ok(testExecutionService.updateExecution(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        testExecutionService.deleteExecution(id);
        return ResponseEntity.ok("Test execution deleted successfully");
    }

    @GetMapping("/release/{releaseID}")
    public ResponseEntity<List<TestExecutionResponseDTO>> getByRelease(@PathVariable Long releaseID) {
        return ResponseEntity.ok(testExecutionService.getExecutionsByRelease(releaseID));
    }

    @GetMapping("/suite/{suiteID}")
    public ResponseEntity<List<TestExecutionResponseDTO>> getBySuite(@PathVariable Long suiteID) {
        return ResponseEntity.ok(testExecutionService.getExecutionsBySuite(suiteID));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<TestExecutionResponseDTO>> getByStatus(@PathVariable ExecutionStatus status) {
        return ResponseEntity.ok(testExecutionService.getExecutionsByStatus(status));
    }
}
