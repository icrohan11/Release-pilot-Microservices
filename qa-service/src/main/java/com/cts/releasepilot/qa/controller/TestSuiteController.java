package com.cts.releasepilot.qa.controller;

import com.cts.releasepilot.qa.common.SuiteStatus;
import com.cts.releasepilot.qa.common.SuiteType;
import com.cts.releasepilot.qa.dto.TestSuiteRequestDTO;
import com.cts.releasepilot.qa.dto.TestSuiteResponseDTO;
import com.cts.releasepilot.qa.service.TestSuiteService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** Test suite management (QAEngineer / ReleaseManager / Admin). */
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/test-suites")
public class TestSuiteController {

    private final TestSuiteService testSuiteService;

    public TestSuiteController(TestSuiteService testSuiteService) {
        this.testSuiteService = testSuiteService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<TestSuiteResponseDTO>> getAll() {
        return ResponseEntity.ok(testSuiteService.getAllSuites());
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<TestSuiteResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(testSuiteService.getSuiteById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<TestSuiteResponseDTO> save(@Valid @RequestBody TestSuiteRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(testSuiteService.createSuite(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TestSuiteResponseDTO> update(@PathVariable Long id,
                                                       @Valid @RequestBody TestSuiteRequestDTO dto) {
        return ResponseEntity.ok(testSuiteService.updateSuite(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        testSuiteService.deleteSuite(id);
        return ResponseEntity.ok("Test suite deleted successfully");
    }

    @GetMapping("/product/{productID}")
    public ResponseEntity<List<TestSuiteResponseDTO>> getByProduct(@PathVariable Long productID) {
        return ResponseEntity.ok(testSuiteService.getSuitesByProduct(productID));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<TestSuiteResponseDTO>> getByStatus(@PathVariable SuiteStatus status) {
        return ResponseEntity.ok(testSuiteService.getSuitesByStatus(status));
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<TestSuiteResponseDTO>> getByType(@PathVariable SuiteType type) {
        return ResponseEntity.ok(testSuiteService.getSuitesByType(type));
    }
}
