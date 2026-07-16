package com.cts.releasepilot.auth.service.impl;

import com.cts.releasepilot.auth.dto.AuditLogDTO;
import com.cts.releasepilot.auth.entity.AuditLog;
import com.cts.releasepilot.auth.mapper.AuditLogMapper;
import com.cts.releasepilot.auth.repository.AuditLogRepository;
import com.cts.releasepilot.auth.service.AuditLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuditLogServiceImpl implements AuditLogService {

    private final AuditLogRepository auditLogRepository;
    private final AuditLogMapper auditLogMapper;

    public AuditLogServiceImpl(AuditLogRepository auditLogRepository, AuditLogMapper auditLogMapper) {
        this.auditLogRepository = auditLogRepository;
        this.auditLogMapper = auditLogMapper;
    }

    @Override
    @Transactional
    public AuditLogDTO record(AuditLogDTO dto) {
        AuditLog entity = auditLogMapper.toEntity(dto);
        if (entity.getTimestamp() == null) {
            entity.setTimestamp(LocalDateTime.now());
        }
        entity.setAuditID(null);
        return auditLogMapper.toDto(auditLogRepository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuditLogDTO> getAll() {
        return auditLogRepository.findAll().stream().map(auditLogMapper::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuditLogDTO> getByUser(Long userID) {
        return auditLogRepository.findByUserID(userID).stream().map(auditLogMapper::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuditLogDTO> getByEntityType(String entityType) {
        return auditLogRepository.findByEntityType(entityType).stream().map(auditLogMapper::toDto).toList();
    }
}
