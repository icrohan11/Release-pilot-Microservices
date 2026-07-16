package com.cts.releasepilot.communication.repository;

import com.cts.releasepilot.communication.common.AdvisoryStatus;
import com.cts.releasepilot.communication.common.Severity;
import com.cts.releasepilot.communication.entity.CustomerAdvisory;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Customer advisory repository. CRUD ({@link ListCrudRepository}) plus
 * release/severity/status finders.
 */
@Repository
public interface CustomerAdvisoryRepository extends ListCrudRepository<CustomerAdvisory, Long> {

    List<CustomerAdvisory> findByReleaseID(Long releaseID);

    List<CustomerAdvisory> findBySeverity(Severity severity);

    List<CustomerAdvisory> findByStatus(AdvisoryStatus status);
}
