package com.resumeanalyzer.backend.repository;

import com.resumeanalyzer.backend.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    // Recruiter ki saari companies
    List<Company> findByRecruiterId(Long recruiterId);

    // Company naam se dhundo
    boolean existsByName(String name);
}