package com.cts.releasepilot.auth.controller;

import com.cts.releasepilot.auth.dto.AuditLogDTO;
import com.cts.releasepilot.auth.service.AuditLogService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** Audit trail endpoints (Admin / ReleaseManager). */
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/audit")
public class AuditLogController {

    private final AuditLogService auditLogService;

    public AuditLogController(AuditLogService auditLogService) {
        this.auditLogService = auditLogService;
    }

    @PostMapping("/record")
    public ResponseEntity<AuditLogDTO> record(@Valid @RequestBody AuditLogDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(auditLogService.record(dto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<AuditLogDTO>> getAll() {
        return ResponseEntity.ok(auditLogService.getAll());
    }

    @GetMapping("/user/{userID}")
    public ResponseEntity<List<AuditLogDTO>> getByUser(@PathVariable Long userID) {
        return ResponseEntity.ok(auditLogService.getByUser(userID));
    }

    @GetMapping("/entity/{entityType}")
    public ResponseEntity<List<AuditLogDTO>> getByEntityType(@PathVariable String entityType) {
        return ResponseEntity.ok(auditLogService.getByEntityType(entityType));
    }
}
