package com.resumeanalyzer.backend.service.impl;

import com.resumeanalyzer.backend.dto.request.LoginRequest;
import com.resumeanalyzer.backend.dto.request.RegisterRequest;
import com.resumeanalyzer.backend.dto.response.AuthResponse;
import com.resumeanalyzer.backend.dto.response.UserResponse;
import com.resumeanalyzer.backend.entity.User;
import com.resumeanalyzer.backend.enums.Role;
import com.resumeanalyzer.backend.repository.UserRepository;
import com.resumeanalyzer.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse register(RegisterRequest request) {

        // Step 1: Email already exist karta hai?
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        // Step 2: User entity banao
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword()) // Plain text abhi — Day 8 mein encrypt karenge
                .role(Role.valueOf(request.getRole().toUpperCase()))
                .build();

        // Step 3: Database mein save karo
        User savedUser = userRepository.save(user);

        // Step 4: Response DTO banao aur return karo
        return mapToUserResponse(savedUser);
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        // Day 8 mein implement karenge — JWT ke saath
        throw new RuntimeException("Login — JWT Day pe implement hoga");
    }

    @Override
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return mapToUserResponse(user);
    }

    @Override
    public UserResponse getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return mapToUserResponse(user);
    }

    // Entity → DTO conversion
    private UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .isVerified(user.getIsVerified())
                .createdAt(user.getCreatedAt())
                .build();
    }
}