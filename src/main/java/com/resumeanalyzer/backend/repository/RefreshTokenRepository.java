package com.resumeanalyzer.backend.repository;

import com.resumeanalyzer.backend.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository
        extends JpaRepository<RefreshToken, Long> {

    // Token string se dhundo
    Optional<RefreshToken> findByToken(String token);

    // User ka token dhundo
    Optional<RefreshToken> findByUserId(Long userId);

    // User ke saare tokens delete karo (logout)
    void deleteByUserId(Long userId);
}