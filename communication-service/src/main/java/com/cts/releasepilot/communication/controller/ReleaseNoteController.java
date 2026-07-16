package com.cts.releasepilot.communication.controller;

import com.cts.releasepilot.communication.common.NoteStatus;
import com.cts.releasepilot.communication.dto.ReleaseNoteRequestDTO;
import com.cts.releasepilot.communication.dto.ReleaseNoteResponseDTO;
import com.cts.releasepilot.communication.service.ReleaseNoteService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** Release note endpoints (ReleaseManager / ProductManager / Admin / CustomerAdmin). */
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/release-notes")
public class ReleaseNoteController {

    private final ReleaseNoteService releaseNoteService;

    public ReleaseNoteController(ReleaseNoteService releaseNoteService) {
        this.releaseNoteService = releaseNoteService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReleaseNoteResponseDTO>> getAll() {
        return ResponseEntity.ok(releaseNoteService.getAllReleaseNotes());
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<ReleaseNoteResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(releaseNoteService.getReleaseNoteById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<ReleaseNoteResponseDTO> save(@Valid @RequestBody ReleaseNoteRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(releaseNoteService.createReleaseNote(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ReleaseNoteResponseDTO> update(@PathVariable Long id,
                                                         @Valid @RequestBody ReleaseNoteRequestDTO dto) {
        return ResponseEntity.ok(releaseNoteService.updateReleaseNote(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        releaseNoteService.deleteReleaseNote(id);
        return ResponseEntity.ok("Release note deleted successfully");
    }

    @GetMapping("/release/{releaseID}")
    public ResponseEntity<List<ReleaseNoteResponseDTO>> getByRelease(@PathVariable Long releaseID) {
        return ResponseEntity.ok(releaseNoteService.getReleaseNotesByRelease(releaseID));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ReleaseNoteResponseDTO>> getByStatus(@PathVariable NoteStatus status) {
        return ResponseEntity.ok(releaseNoteService.getReleaseNotesByStatus(status));
    }
}
