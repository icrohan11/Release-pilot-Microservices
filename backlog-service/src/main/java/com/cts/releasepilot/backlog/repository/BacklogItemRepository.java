package com.cts.releasepilot.backlog.repository;

import com.cts.releasepilot.backlog.common.BacklogStatus;
import com.cts.releasepilot.backlog.common.BacklogType;
import com.cts.releasepilot.backlog.common.Priority;
import com.cts.releasepilot.backlog.entity.BacklogItem;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Backlog item repository. Extends CRUD ({@link ListCrudRepository}) plus
 * pagination/sorting for backlog listings - no JpaRepository-specific methods
 * are required here.
 */
@Repository
public interface BacklogItemRepository extends ListCrudRepository<BacklogItem, Long>,
        PagingAndSortingRepository<BacklogItem, Long> {

    List<BacklogItem> findByProductID(Long productID);

    List<BacklogItem> findByStatus(BacklogStatus status);

    List<BacklogItem> findByPriority(Priority priority);

    List<BacklogItem> findByType(BacklogType type);
}
