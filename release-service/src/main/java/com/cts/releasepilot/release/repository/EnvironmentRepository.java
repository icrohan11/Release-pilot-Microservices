package com.cts.releasepilot.release.repository;

import com.cts.releasepilot.release.common.EnvStatus;
import com.cts.releasepilot.release.entity.Environment;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/** Environment repository. */
@Repository
public interface EnvironmentRepository extends ListCrudRepository<Environment, Long> {

    List<Environment> findByProductID(Long productID);

    List<Environment> findByStatus(EnvStatus status);
}
