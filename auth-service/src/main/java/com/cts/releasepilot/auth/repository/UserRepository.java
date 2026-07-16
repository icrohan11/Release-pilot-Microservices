package com.cts.releasepilot.auth.repository;

import com.cts.releasepilot.auth.common.Role;
import com.cts.releasepilot.auth.common.UserStatus;
import com.cts.releasepilot.auth.entity.User;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * User repository. Extends only what is needed: CRUD ({@link ListCrudRepository})
 * plus pagination/sorting for user listings - no JpaRepository-specific methods
 * (flush, batch) are required here.
 */
@Repository
public interface UserRepository extends ListCrudRepository<User, Long>,
        PagingAndSortingRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    List<User> findByRole(Role role);

    List<User> findByStatus(UserStatus status);
}
