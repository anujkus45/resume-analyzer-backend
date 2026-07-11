package com.resumeanalyzer.backend.controller;

import com.resumeanalyzer.backend.dto.response.ApiResponse;
import com.resumeanalyzer.backend.dto.response.UserResponse;
import com.resumeanalyzer.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> getUserById(
            @PathVariable Long id) {

        UserResponse userResponse = userService.getUserById(id);

        return ResponseEntity
                .ok(ApiResponse.success("User fetched successfully", userResponse));
    }
}