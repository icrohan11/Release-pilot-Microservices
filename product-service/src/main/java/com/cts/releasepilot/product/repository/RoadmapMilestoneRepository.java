package com.cts.releasepilot.product.repository;

import com.cts.releasepilot.product.common.MilestoneStatus;
import com.cts.releasepilot.product.entity.RoadmapMilestone;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Roadmap milestone repository. Extends only what is needed: CRUD
 * ({@link ListCrudRepository}) plus pagination/sorting for milestone listings.
 */
@Repository
public interface RoadmapMilestoneRepository extends ListCrudRepository<RoadmapMilestone, Long>,
        PagingAndSortingRepository<RoadmapMilestone, Long> {

    List<RoadmapMilestone> findByProductID(Long productID);

    List<RoadmapMilestone> findByStatus(MilestoneStatus status);
}
