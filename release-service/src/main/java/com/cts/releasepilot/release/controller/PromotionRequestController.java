package com.cts.releasepilot.release.controller;

import com.cts.releasepilot.release.common.PromotionStatus;
import com.cts.releasepilot.release.dto.PromotionRequestRequestDTO;
import com.cts.releasepilot.release.dto.PromotionRequestResponseDTO;
import com.cts.releasepilot.release.service.PromotionRequestService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** Promotion request management endpoints. */
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/promotions")
public class PromotionRequestController {

    private final PromotionRequestService promotionRequestService;

    public PromotionRequestController(PromotionRequestService promotionRequestService) {
        this.promotionRequestService = promotionRequestService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<PromotionRequestResponseDTO>> getAll() {
        return ResponseEntity.ok(promotionRequestService.getAll());
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<PromotionRequestResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(promotionRequestService.getById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<PromotionRequestResponseDTO> save(@Valid @RequestBody PromotionRequestRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(promotionRequestService.create(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PromotionRequestResponseDTO> update(@PathVariable Long id,
                                                              @Valid @RequestBody PromotionRequestRequestDTO dto) {
        return ResponseEntity.ok(promotionRequestService.update(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        promotionRequestService.delete(id);
        return ResponseEntity.ok("Promotion request deleted successfully");
    }

    @GetMapping("/release/{releaseID}")
    public ResponseEntity<List<PromotionRequestResponseDTO>> getByRelease(@PathVariable Long releaseID) {
        return ResponseEntity.ok(promotionRequestService.getByRelease(releaseID));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<PromotionRequestResponseDTO>> getByStatus(@PathVariable PromotionStatus status) {
        return ResponseEntity.ok(promotionRequestService.getByStatus(status));
    }
}
