package com.resumeanalyzer.backend.repository;

import com.resumeanalyzer.backend.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {

    // User ke saare active resumes
    List<Resume> findByUserIdAndIsActiveTrue(Long userId);

    // User ki latest resume
    Optional<Resume> findTopByUserIdOrderByUploadedAtDesc(Long userId);
}