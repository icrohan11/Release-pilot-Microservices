package com.cts.releasepilot.release.repository;

import com.cts.releasepilot.release.common.PromotionStatus;
import com.cts.releasepilot.release.entity.PromotionRequest;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/** Promotion request repository. */
@Repository
public interface PromotionRequestRepository extends ListCrudRepository<PromotionRequest, Long> {

    List<PromotionRequest> findByReleaseID(Long releaseID);

    List<PromotionRequest> findByStatus(PromotionStatus status);
}
