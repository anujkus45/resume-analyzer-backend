package com.resumeanalyzer.backend.service.impl;

import com.resumeanalyzer.backend.dto.request.LoginRequest;
import com.resumeanalyzer.backend.dto.request.RegisterRequest;
import com.resumeanalyzer.backend.dto.response.AuthResponse;
import com.resumeanalyzer.backend.dto.response.UserResponse;
import com.resumeanalyzer.backend.entity.User;
import com.resumeanalyzer.backend.enums.Role;
import com.resumeanalyzer.backend.exception.BadRequestException;
import com.resumeanalyzer.backend.exception.EmailAlreadyExistsException;
import com.resumeanalyzer.backend.exception.ResourceNotFoundException;
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

        // Step 1: Duplicate email check
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException(request.getEmail());
        }

        // Step 2: Role validate karo
        Role role;
        try {
            role = Role.valueOf(request.getRole().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException(
                    "Invalid role. Allowed: STUDENT, RECRUITER, ADMIN"
            );
        }

        // Step 3: User banao
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(role)
                .isVerified(false)
                .isActive(true)
                .build();

        // Step 4: Save karo
        User savedUser = userRepository.save(user);

        // Step 5: Response return karo
        return mapToUserResponse(savedUser);
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        throw new BadRequestException(
                "Login — JWT Day pe implement hoga"
        );
    }

    @Override
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User", id));
        return mapToUserResponse(user);
    }

    @Override
    public UserResponse getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with email: " + email
                        ));
        return mapToUserResponse(user);
    }

    // Entity → DTO
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