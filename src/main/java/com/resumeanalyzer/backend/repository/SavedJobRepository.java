package com.resumeanalyzer.backend.repository;

import com.resumeanalyzer.backend.entity.SavedJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface SavedJobRepository
        extends JpaRepository<SavedJob, Long> {

    // User ke saved jobs
    List<SavedJob> findByUserId(Long userId);

    // Already saved check
    boolean existsByUserIdAndJobId(Long userId, Long jobId);

    // Specific saved job
    Optional<SavedJob> findByUserIdAndJobId(Long userId, Long jobId);
}