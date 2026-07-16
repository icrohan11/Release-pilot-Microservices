package com.cts.releasepilot.communication.repository;

import com.cts.releasepilot.communication.common.NotificationCategory;
import com.cts.releasepilot.communication.common.NotificationStatus;
import com.cts.releasepilot.communication.entity.Notification;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Notification repository. CRUD ({@link ListCrudRepository}) plus
 * user/status/category finders.
 */
@Repository
public interface NotificationRepository extends ListCrudRepository<Notification, Long> {

    List<Notification> findByUserID(Long userID);

    List<Notification> findByStatus(NotificationStatus status);

    List<Notification> findByCategory(NotificationCategory category);
}
