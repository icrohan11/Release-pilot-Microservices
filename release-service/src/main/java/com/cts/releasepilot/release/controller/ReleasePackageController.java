package com.cts.releasepilot.release.controller;

import com.cts.releasepilot.release.common.ReleaseStatus;
import com.cts.releasepilot.release.dto.ReleasePackageRequestDTO;
import com.cts.releasepilot.release.dto.ReleasePackageResponseDTO;
import com.cts.releasepilot.release.service.ReleasePackageService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** Release package management endpoints. */
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/releases")
public class ReleasePackageController {

    private final ReleasePackageService releasePackageService;

    public ReleasePackageController(ReleasePackageService releasePackageService) {
        this.releasePackageService = releasePackageService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReleasePackageResponseDTO>> getAll() {
        return ResponseEntity.ok(releasePackageService.getAll());
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<ReleasePackageResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(releasePackageService.getById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<ReleasePackageResponseDTO> save(@Valid @RequestBody ReleasePackageRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(releasePackageService.create(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ReleasePackageResponseDTO> update(@PathVariable Long id,
                                                            @Valid @RequestBody ReleasePackageRequestDTO dto) {
        return ResponseEntity.ok(releasePackageService.update(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        releasePackageService.delete(id);
        return ResponseEntity.ok("Release package deleted successfully");
    }

    @GetMapping("/product/{productID}")
    public ResponseEntity<List<ReleasePackageResponseDTO>> getByProduct(@PathVariable Long productID) {
        return ResponseEntity.ok(releasePackageService.getByProduct(productID));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ReleasePackageResponseDTO>> getByStatus(@PathVariable ReleaseStatus status) {
        return ResponseEntity.ok(releasePackageService.getByStatus(status));
    }
}
