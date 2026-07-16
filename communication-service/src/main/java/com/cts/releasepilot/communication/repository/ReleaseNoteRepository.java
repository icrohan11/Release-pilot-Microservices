package com.cts.releasepilot.communication.repository;

import com.cts.releasepilot.communication.common.NoteStatus;
import com.cts.releasepilot.communication.entity.ReleaseNote;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Release note repository. CRUD ({@link ListCrudRepository}) plus release/status
 * finders - no JpaRepository-specific methods are required here.
 */
@Repository
public interface ReleaseNoteRepository extends ListCrudRepository<ReleaseNote, Long> {

    List<ReleaseNote> findByReleaseID(Long releaseID);

    List<ReleaseNote> findByStatus(NoteStatus status);
}
