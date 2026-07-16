package com.cts.releasepilot.backlog.controller;

import com.cts.releasepilot.backlog.common.SprintStatus;
import com.cts.releasepilot.backlog.dto.SprintRequestDTO;
import com.cts.releasepilot.backlog.dto.SprintResponseDTO;
import com.cts.releasepilot.backlog.service.SprintService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** Sprint management endpoints. */
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/sprints")
public class SprintController {

    private final SprintService sprintService;

    public SprintController(SprintService sprintService) {
        this.sprintService = sprintService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<SprintResponseDTO>> getAll() {
        return ResponseEntity.ok(sprintService.getAllSprints());
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<SprintResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(sprintService.getSprintById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<SprintResponseDTO> save(@Valid @RequestBody SprintRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sprintService.createSprint(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SprintResponseDTO> update(@PathVariable Long id,
                                                    @Valid @RequestBody SprintRequestDTO dto) {
        return ResponseEntity.ok(sprintService.updateSprint(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        sprintService.deleteSprint(id);
        return ResponseEntity.ok("Sprint deleted successfully");
    }

    @GetMapping("/product/{productID}")
    public ResponseEntity<List<SprintResponseDTO>> getByProduct(@PathVariable Long productID) {
        return ResponseEntity.ok(sprintService.getSprintsByProduct(productID));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<SprintResponseDTO>> getByStatus(@PathVariable SprintStatus status) {
        return ResponseEntity.ok(sprintService.getSprintsByStatus(status));
    }
}
