package com.resumeanalyzer.backend.dto.response;

import com.resumeanalyzer.backend.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private Role role;
    private Boolean isVerified;
    private LocalDateTime createdAt;
    // Password? Nahi hai! ✅
}