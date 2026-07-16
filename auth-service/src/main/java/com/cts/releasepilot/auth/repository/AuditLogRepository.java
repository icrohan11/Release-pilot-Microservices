package com.cts.releasepilot.auth.repository;

import com.cts.releasepilot.auth.entity.AuditLog;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Audit log repository. Only create + read operations are required,
 * so a simple {@link ListCrudRepository} is sufficient.
 */
@Repository
public interface AuditLogRepository extends ListCrudRepository<AuditLog, Long> {

    List<AuditLog> findByUserID(Long userID);

    List<AuditLog> findByEntityType(String entityType);
}
