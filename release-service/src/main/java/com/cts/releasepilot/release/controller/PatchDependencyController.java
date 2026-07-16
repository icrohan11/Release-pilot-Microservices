package com.cts.releasepilot.release.controller;

import com.cts.releasepilot.release.dto.PatchDependencyRequestDTO;
import com.cts.releasepilot.release.dto.PatchDependencyResponseDTO;
import com.cts.releasepilot.release.service.PatchDependencyService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** Patch dependency management endpoints. */
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/patches")
public class PatchDependencyController {

    private final PatchDependencyService patchDependencyService;

    public PatchDependencyController(PatchDependencyService patchDependencyService) {
        this.patchDependencyService = patchDependencyService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<PatchDependencyResponseDTO>> getAll() {
        return ResponseEntity.ok(patchDependencyService.getAll());
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<PatchDependencyResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(patchDependencyService.getById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<PatchDependencyResponseDTO> save(@Valid @RequestBody PatchDependencyRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(patchDependencyService.create(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PatchDependencyResponseDTO> update(@PathVariable Long id,
                                                             @Valid @RequestBody PatchDependencyRequestDTO dto) {
        return ResponseEntity.ok(patchDependencyService.update(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        patchDependencyService.delete(id);
        return ResponseEntity.ok("Patch dependency deleted successfully");
    }

    @GetMapping("/release/{releaseID}")
    public ResponseEntity<List<PatchDependencyResponseDTO>> getByRelease(@PathVariable Long releaseID) {
        return ResponseEntity.ok(patchDependencyService.getByRelease(releaseID));
    }
}
