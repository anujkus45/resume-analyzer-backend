package com.resumeanalyzer.backend.service;

import com.resumeanalyzer.backend.dto.request.LoginRequest;
import com.resumeanalyzer.backend.dto.request.RegisterRequest;
import com.resumeanalyzer.backend.dto.response.AuthResponse;
import com.resumeanalyzer.backend.dto.response.UserResponse;

public interface UserService {
    UserResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
    UserResponse getUserById(Long id);
    UserResponse getUserByEmail(String email);
}