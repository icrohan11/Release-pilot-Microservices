package com.cts.releasepilot.release.controller;

import com.cts.releasepilot.release.common.EnvStatus;
import com.cts.releasepilot.release.dto.EnvironmentRequestDTO;
import com.cts.releasepilot.release.dto.EnvironmentResponseDTO;
import com.cts.releasepilot.release.service.EnvironmentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** Environment management endpoints. */
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/environments")
public class EnvironmentController {

    private final EnvironmentService environmentService;

    public EnvironmentController(EnvironmentService environmentService) {
        this.environmentService = environmentService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<EnvironmentResponseDTO>> getAll() {
        return ResponseEntity.ok(environmentService.getAll());
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<EnvironmentResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(environmentService.getById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<EnvironmentResponseDTO> save(@Valid @RequestBody EnvironmentRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(environmentService.create(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EnvironmentResponseDTO> update(@PathVariable Long id,
                                                         @Valid @RequestBody EnvironmentRequestDTO dto) {
        return ResponseEntity.ok(environmentService.update(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        environmentService.delete(id);
        return ResponseEntity.ok("Environment deleted successfully");
    }

    @GetMapping("/product/{productID}")
    public ResponseEntity<List<EnvironmentResponseDTO>> getByProduct(@PathVariable Long productID) {
        return ResponseEntity.ok(environmentService.getByProduct(productID));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<EnvironmentResponseDTO>> getByStatus(@PathVariable EnvStatus status) {
        return ResponseEntity.ok(environmentService.getByStatus(status));
    }
}
