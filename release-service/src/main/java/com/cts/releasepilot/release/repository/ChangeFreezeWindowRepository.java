package com.cts.releasepilot.release.repository;

import com.cts.releasepilot.release.common.FreezeStatus;
import com.cts.releasepilot.release.entity.ChangeFreezeWindow;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/** Change freeze window repository. */
@Repository
public interface ChangeFreezeWindowRepository extends ListCrudRepository<ChangeFreezeWindow, Long> {

    List<ChangeFreezeWindow> findByProductID(Long productID);

    List<ChangeFreezeWindow> findByStatus(FreezeStatus status);
}
