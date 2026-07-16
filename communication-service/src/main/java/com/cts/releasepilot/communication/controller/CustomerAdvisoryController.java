package com.cts.releasepilot.communication.controller;

import com.cts.releasepilot.communication.common.AdvisoryStatus;
import com.cts.releasepilot.communication.common.Severity;
import com.cts.releasepilot.communication.dto.CustomerAdvisoryRequestDTO;
import com.cts.releasepilot.communication.dto.CustomerAdvisoryResponseDTO;
import com.cts.releasepilot.communication.service.CustomerAdvisoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** Customer advisory endpoints (ReleaseManager / ProductManager / Admin / CustomerAdmin). */
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/advisories")
public class CustomerAdvisoryController {

    private final CustomerAdvisoryService advisoryService;

    public CustomerAdvisoryController(CustomerAdvisoryService advisoryService) {
        this.advisoryService = advisoryService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CustomerAdvisoryResponseDTO>> getAll() {
        return ResponseEntity.ok(advisoryService.getAllAdvisories());
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<CustomerAdvisoryResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(advisoryService.getAdvisoryById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<CustomerAdvisoryResponseDTO> save(@Valid @RequestBody CustomerAdvisoryRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(advisoryService.createAdvisory(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CustomerAdvisoryResponseDTO> update(@PathVariable Long id,
                                                              @Valid @RequestBody CustomerAdvisoryRequestDTO dto) {
        return ResponseEntity.ok(advisoryService.updateAdvisory(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        advisoryService.deleteAdvisory(id);
        return ResponseEntity.ok("Advisory deleted successfully");
    }

    @GetMapping("/release/{releaseID}")
    public ResponseEntity<List<CustomerAdvisoryResponseDTO>> getByRelease(@PathVariable Long releaseID) {
        return ResponseEntity.ok(advisoryService.getAdvisoriesByRelease(releaseID));
    }

    @GetMapping("/severity/{severity}")
    public ResponseEntity<List<CustomerAdvisoryResponseDTO>> getBySeverity(@PathVariable Severity severity) {
        return ResponseEntity.ok(advisoryService.getAdvisoriesBySeverity(severity));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<CustomerAdvisoryResponseDTO>> getByStatus(@PathVariable AdvisoryStatus status) {
        return ResponseEntity.ok(advisoryService.getAdvisoriesByStatus(status));
    }
}
