package com.cts.releasepilot.product.controller;

import com.cts.releasepilot.product.common.MilestoneStatus;
import com.cts.releasepilot.product.dto.RoadmapMilestoneRequestDTO;
import com.cts.releasepilot.product.dto.RoadmapMilestoneResponseDTO;
import com.cts.releasepilot.product.service.RoadmapMilestoneService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** Roadmap milestone endpoints (role-protected by SecurityConfig). */
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/milestones")
public class MilestoneController {

    private final RoadmapMilestoneService milestoneService;

    public MilestoneController(RoadmapMilestoneService milestoneService) {
        this.milestoneService = milestoneService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<RoadmapMilestoneResponseDTO>> getAll() {
        return ResponseEntity.ok(milestoneService.getAllMilestones());
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<RoadmapMilestoneResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(milestoneService.getMilestoneById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<RoadmapMilestoneResponseDTO> save(@Valid @RequestBody RoadmapMilestoneRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(milestoneService.saveMilestone(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RoadmapMilestoneResponseDTO> update(@PathVariable Long id,
                                                              @Valid @RequestBody RoadmapMilestoneRequestDTO dto) {
        return ResponseEntity.ok(milestoneService.updateMilestone(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        milestoneService.deleteMilestone(id);
        return ResponseEntity.ok("Milestone deleted successfully");
    }

    @GetMapping("/product/{productID}")
    public ResponseEntity<List<RoadmapMilestoneResponseDTO>> getByProduct(@PathVariable Long productID) {
        return ResponseEntity.ok(milestoneService.getMilestonesByProduct(productID));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<RoadmapMilestoneResponseDTO>> getByStatus(@PathVariable MilestoneStatus status) {
        return ResponseEntity.ok(milestoneService.getMilestonesByStatus(status));
    }
}
