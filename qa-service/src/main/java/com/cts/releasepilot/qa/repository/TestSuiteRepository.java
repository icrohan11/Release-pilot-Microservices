package com.cts.releasepilot.qa.repository;

import com.cts.releasepilot.qa.common.SuiteStatus;
import com.cts.releasepilot.qa.common.SuiteType;
import com.cts.releasepilot.qa.entity.TestSuite;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/** Test suite repository. CRUD plus product/status/type lookups. */
@Repository
public interface TestSuiteRepository extends ListCrudRepository<TestSuite, Long> {

    List<TestSuite> findByProductID(Long productID);

    List<TestSuite> findByStatus(SuiteStatus status);

    List<TestSuite> findByType(SuiteType type);
}
