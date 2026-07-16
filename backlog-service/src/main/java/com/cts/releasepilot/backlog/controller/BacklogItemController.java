package com.cts.releasepilot.backlog.controller;

import com.cts.releasepilot.backlog.common.BacklogStatus;
import com.cts.releasepilot.backlog.common.BacklogType;
import com.cts.releasepilot.backlog.common.Priority;
import com.cts.releasepilot.backlog.dto.BacklogItemRequestDTO;
import com.cts.releasepilot.backlog.dto.BacklogItemResponseDTO;
import com.cts.releasepilot.backlog.service.BacklogItemService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** Backlog item management endpoints. */
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/backlog")
public class BacklogItemController {

    private final BacklogItemService backlogItemService;

    public BacklogItemController(BacklogItemService backlogItemService) {
        this.backlogItemService = backlogItemService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<BacklogItemResponseDTO>> getAll() {
        return ResponseEntity.ok(backlogItemService.getAllBacklogItems());
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<BacklogItemResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(backlogItemService.getBacklogItemById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<BacklogItemResponseDTO> save(@Valid @RequestBody BacklogItemRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(backlogItemService.createBacklogItem(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BacklogItemResponseDTO> update(@PathVariable Long id,
                                                         @Valid @RequestBody BacklogItemRequestDTO dto) {
        return ResponseEntity.ok(backlogItemService.updateBacklogItem(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        backlogItemService.deleteBacklogItem(id);
        return ResponseEntity.ok("Backlog item deleted successfully");
    }

    @GetMapping("/product/{productID}")
    public ResponseEntity<List<BacklogItemResponseDTO>> getByProduct(@PathVariable Long productID) {
        return ResponseEntity.ok(backlogItemService.getBacklogItemsByProduct(productID));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<BacklogItemResponseDTO>> getByStatus(@PathVariable BacklogStatus status) {
        return ResponseEntity.ok(backlogItemService.getBacklogItemsByStatus(status));
    }

    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<BacklogItemResponseDTO>> getByPriority(@PathVariable Priority priority) {
        return ResponseEntity.ok(backlogItemService.getBacklogItemsByPriority(priority));
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<BacklogItemResponseDTO>> getByType(@PathVariable BacklogType type) {
        return ResponseEntity.ok(backlogItemService.getBacklogItemsByType(type));
    }
}
