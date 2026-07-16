package com.cts.releasepilot.backlog.repository;

import com.cts.releasepilot.backlog.common.SprintStatus;
import com.cts.releasepilot.backlog.entity.Sprint;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Sprint repository. Extends CRUD ({@link ListCrudRepository}) plus
 * pagination/sorting for sprint listings.
 */
@Repository
public interface SprintRepository extends ListCrudRepository<Sprint, Long>,
        PagingAndSortingRepository<Sprint, Long> {

    List<Sprint> findByProductID(Long productID);

    List<Sprint> findByStatus(SprintStatus status);
}
