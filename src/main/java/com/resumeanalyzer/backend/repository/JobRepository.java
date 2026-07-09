package com.resumeanalyzer.backend.repository;

import com.resumeanalyzer.backend.entity.Job;
import com.resumeanalyzer.backend.enums.JobType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    // Company ki saari active jobs
    List<Job> findByCompanyIdAndIsActiveTrue(Long companyId);

    // Job type se dhundo
    List<Job> findByJobTypeAndIsActiveTrue(JobType jobType);

    // Recruiter ki posted jobs
    List<Job> findByPostedById(Long userId);

    // Active jobs
    List<Job> findByIsActiveTrue();
}