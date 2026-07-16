package com.cts.releasepilot.qa.repository;

import com.cts.releasepilot.qa.common.GateOutcome;
import com.cts.releasepilot.qa.common.GateStatus;
import com.cts.releasepilot.qa.entity.ReleaseGate;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/** Release quality gate repository. CRUD plus release/outcome/status lookups. */
@Repository
public interface ReleaseGateRepository extends ListCrudRepository<ReleaseGate, Long> {

    List<ReleaseGate> findByReleaseID(Long releaseID);

    List<ReleaseGate> findByOutcome(GateOutcome outcome);

    List<ReleaseGate> findByStatus(GateStatus status);
}
