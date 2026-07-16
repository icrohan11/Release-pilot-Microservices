package com.cts.releasepilot.auth.service;

import com.cts.releasepilot.auth.dto.AuditLogDTO;

import java.util.List;

/** Audit trail operations. */
public interface AuditLogService {

    AuditLogDTO record(AuditLogDTO dto);

    List<AuditLogDTO> getAll();

    List<AuditLogDTO> getByUser(Long userID);

    List<AuditLogDTO> getByEntityType(String entityType);
}
