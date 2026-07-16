package com.cts.releasepilot.release.repository;

import com.cts.releasepilot.release.common.ReleaseStatus;
import com.cts.releasepilot.release.entity.ReleasePackage;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Release package repository. CRUD plus pagination/sorting for release
 * listings - no JpaRepository-specific methods are required.
 */
@Repository
public interface ReleasePackageRepository extends ListCrudRepository<ReleasePackage, Long>,
        PagingAndSortingRepository<ReleasePackage, Long> {

    List<ReleasePackage> findByProductID(Long productID);

    List<ReleasePackage> findByStatus(ReleaseStatus status);
}
