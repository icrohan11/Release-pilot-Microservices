package com.cts.releasepilot.release.repository;

import com.cts.releasepilot.release.entity.PatchDependency;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/** Patch dependency repository. */
@Repository
public interface PatchDependencyRepository extends ListCrudRepository<PatchDependency, Long> {

    List<PatchDependency> findByReleaseID(Long releaseID);
}
