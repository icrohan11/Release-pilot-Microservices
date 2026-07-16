package com.cts.releasepilot.backlog.controller;

import com.cts.releasepilot.backlog.common.SprintItemStatus;
import com.cts.releasepilot.backlog.dto.SprintItemRequestDTO;
import com.cts.releasepilot.backlog.dto.SprintItemResponseDTO;
import com.cts.releasepilot.backlog.service.SprintItemService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** Sprint item management endpoints. */
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/sprint-items")
public class SprintItemController {

    private final SprintItemService sprintItemService;

    public SprintItemController(SprintItemService sprintItemService) {
        this.sprintItemService = sprintItemService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<SprintItemResponseDTO>> getAll() {
        return ResponseEntity.ok(sprintItemService.getAllSprintItems());
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<SprintItemResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(sprintItemService.getSprintItemById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<SprintItemResponseDTO> save(@Valid @RequestBody SprintItemRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sprintItemService.createSprintItem(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SprintItemResponseDTO> update(@PathVariable Long id,
                                                        @Valid @RequestBody SprintItemRequestDTO dto) {
        return ResponseEntity.ok(sprintItemService.updateSprintItem(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        sprintItemService.deleteSprintItem(id);
        return ResponseEntity.ok("Sprint item deleted successfully");
    }

    @GetMapping("/sprint/{sprintID}")
    public ResponseEntity<List<SprintItemResponseDTO>> getBySprint(@PathVariable Long sprintID) {
        return ResponseEntity.ok(sprintItemService.getSprintItemsBySprint(sprintID));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<SprintItemResponseDTO>> getByStatus(@PathVariable SprintItemStatus status) {
        return ResponseEntity.ok(sprintItemService.getSprintItemsByStatus(status));
    }
}
