package com.cts.releasepilot.backlog.repository;

import com.cts.releasepilot.backlog.common.SprintItemStatus;
import com.cts.releasepilot.backlog.entity.SprintItem;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Sprint item repository. Only CRUD operations are required,
 * so a simple {@link ListCrudRepository} is sufficient.
 */
@Repository
public interface SprintItemRepository extends ListCrudRepository<SprintItem, Long> {

    List<SprintItem> findBySprintID(Long sprintID);

    List<SprintItem> findByStatus(SprintItemStatus status);
}
