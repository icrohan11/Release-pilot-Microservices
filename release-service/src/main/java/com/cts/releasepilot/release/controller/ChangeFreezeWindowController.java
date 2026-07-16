package com.cts.releasepilot.release.controller;

import com.cts.releasepilot.release.common.FreezeStatus;
import com.cts.releasepilot.release.dto.ChangeFreezeWindowRequestDTO;
import com.cts.releasepilot.release.dto.ChangeFreezeWindowResponseDTO;
import com.cts.releasepilot.release.service.ChangeFreezeWindowService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** Change freeze window management endpoints. */
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/freezes")
public class ChangeFreezeWindowController {

    private final ChangeFreezeWindowService changeFreezeWindowService;

    public ChangeFreezeWindowController(ChangeFreezeWindowService changeFreezeWindowService) {
        this.changeFreezeWindowService = changeFreezeWindowService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ChangeFreezeWindowResponseDTO>> getAll() {
        return ResponseEntity.ok(changeFreezeWindowService.getAll());
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<ChangeFreezeWindowResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(changeFreezeWindowService.getById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<ChangeFreezeWindowResponseDTO> save(@Valid @RequestBody ChangeFreezeWindowRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(changeFreezeWindowService.create(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ChangeFreezeWindowResponseDTO> update(@PathVariable Long id,
                                                                @Valid @RequestBody ChangeFreezeWindowRequestDTO dto) {
        return ResponseEntity.ok(changeFreezeWindowService.update(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        changeFreezeWindowService.delete(id);
        return ResponseEntity.ok("Change freeze window deleted successfully");
    }

    @GetMapping("/product/{productID}")
    public ResponseEntity<List<ChangeFreezeWindowResponseDTO>> getByProduct(@PathVariable Long productID) {
        return ResponseEntity.ok(changeFreezeWindowService.getByProduct(productID));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ChangeFreezeWindowResponseDTO>> getByStatus(@PathVariable FreezeStatus status) {
        return ResponseEntity.ok(changeFreezeWindowService.getByStatus(status));
    }
}
