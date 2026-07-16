package com.cts.releasepilot.qa.controller;

import com.cts.releasepilot.qa.common.GateOutcome;
import com.cts.releasepilot.qa.common.GateStatus;
import com.cts.releasepilot.qa.dto.ReleaseGateRequestDTO;
import com.cts.releasepilot.qa.dto.ReleaseGateResponseDTO;
import com.cts.releasepilot.qa.service.ReleaseGateService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** Release quality gate management (ReleaseManager / QAEngineer / Admin). */
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/gates")
public class ReleaseGateController {

    private final ReleaseGateService releaseGateService;

    public ReleaseGateController(ReleaseGateService releaseGateService) {
        this.releaseGateService = releaseGateService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReleaseGateResponseDTO>> getAll() {
        return ResponseEntity.ok(releaseGateService.getAllGates());
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<ReleaseGateResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(releaseGateService.getGateById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<ReleaseGateResponseDTO> save(@Valid @RequestBody ReleaseGateRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(releaseGateService.createGate(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ReleaseGateResponseDTO> update(@PathVariable Long id,
                                                         @Valid @RequestBody ReleaseGateRequestDTO dto) {
        return ResponseEntity.ok(releaseGateService.updateGate(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        releaseGateService.deleteGate(id);
        return ResponseEntity.ok("Release gate deleted successfully");
    }

    @GetMapping("/release/{releaseID}")
    public ResponseEntity<List<ReleaseGateResponseDTO>> getByRelease(@PathVariable Long releaseID) {
        return ResponseEntity.ok(releaseGateService.getGatesByRelease(releaseID));
    }

    @GetMapping("/outcome/{outcome}")
    public ResponseEntity<List<ReleaseGateResponseDTO>> getByOutcome(@PathVariable GateOutcome outcome) {
        return ResponseEntity.ok(releaseGateService.getGatesByOutcome(outcome));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ReleaseGateResponseDTO>> getByStatus(@PathVariable GateStatus status) {
        return ResponseEntity.ok(releaseGateService.getGatesByStatus(status));
    }
}
