package com.resumeanalyzer.backend.repository;

import com.resumeanalyzer.backend.entity.User;
import com.resumeanalyzer.backend.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Email se user dhundo (Login ke liye)
    Optional<User> findByEmail(String email);

    // Email exist karta hai? (Registration ke liye)
    boolean existsByEmail(String email);

    // Role se saare users dhundo (Admin ke liye)
    List<User> findByRole(Role role);

    // Active users dhundo
    List<User> findByIsActiveTrue();

    // Email aur active status se dhundo
    Optional<User> findByEmailAndIsActiveTrue(String email);
}