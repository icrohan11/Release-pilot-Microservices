package com.cts.releasepilot.auth.mapper;

import com.cts.releasepilot.auth.dto.AuditLogDTO;
import com.cts.releasepilot.auth.entity.AuditLog;
import org.springframework.stereotype.Component;

/** Converts between {@link AuditLog} entities and DTOs. */
@Component
public class AuditLogMapper {

    public AuditLog toEntity(AuditLogDTO dto) {
        if (dto == null) {
            return null;
        }
        AuditLog log = new AuditLog();
        log.setAuditID(dto.getAuditID());
        log.setUserID(dto.getUserID());
        log.setAction(dto.getAction());
        log.setEntityType(dto.getEntityType());
        log.setRecordID(dto.getRecordID());
        log.setTimestamp(dto.getTimestamp());
        return log;
    }

    public AuditLogDTO toDto(AuditLog log) {
        if (log == null) {
            return null;
        }
        AuditLogDTO dto = new AuditLogDTO();
        dto.setAuditID(log.getAuditID());
        dto.setUserID(log.getUserID());
        dto.setAction(log.getAction());
        dto.setEntityType(log.getEntityType());
        dto.setRecordID(log.getRecordID());
        dto.setTimestamp(log.getTimestamp());
        return dto;
    }
}
