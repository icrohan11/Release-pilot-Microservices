package com.cts.releasepilot.qa.repository;

import com.cts.releasepilot.qa.common.ExecutionStatus;
import com.cts.releasepilot.qa.entity.TestExecution;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/** Test execution repository. CRUD plus release/suite/status lookups. */
@Repository
public interface TestExecutionRepository extends ListCrudRepository<TestExecution, Long> {

    List<TestExecution> findByReleaseID(Long releaseID);

    List<TestExecution> findBySuiteID(Long suiteID);

    List<TestExecution> findByStatus(ExecutionStatus status);
}
