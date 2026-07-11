package com.resumeanalyzer.backend.controller;

import com.resumeanalyzer.backend.dto.request.LoginRequest;
import com.resumeanalyzer.backend.dto.request.RegisterRequest;
import com.resumeanalyzer.backend.dto.response.ApiResponse;
import com.resumeanalyzer.backend.dto.response.AuthResponse;
import com.resumeanalyzer.backend.dto.response.UserResponse;
import com.resumeanalyzer.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponse>> register(
            @RequestBody RegisterRequest request) {

        UserResponse userResponse = userService.register(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("User registered successfully", userResponse));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(
            @RequestBody LoginRequest request) {

        AuthResponse authResponse = userService.login(request);

        return ResponseEntity
                .ok(ApiResponse.success("Login successful", authResponse));
    }
}