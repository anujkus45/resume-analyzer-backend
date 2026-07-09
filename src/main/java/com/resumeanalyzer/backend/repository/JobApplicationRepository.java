package com.resumeanalyzer.backend.repository;

import com.resumeanalyzer.backend.entity.JobApplication;
import com.resumeanalyzer.backend.enums.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface JobApplicationRepository
        extends JpaRepository<JobApplication, Long> {

    // Student ki saari applications
    List<JobApplication> findByApplicantId(Long applicantId);

    // Job ke liye saari applications
    List<JobApplication> findByJobId(Long jobId);

    // Already applied check karo
    boolean existsByJobIdAndApplicantId(Long jobId, Long applicantId);

    // Status se dhundo
    List<JobApplication> findByApplicantIdAndStatus(
            Long applicantId, ApplicationStatus status
    );

    // Specific application dhundo
    Optional<JobApplication> findByJobIdAndApplicantId(
            Long jobId, Long applicantId
    );
}