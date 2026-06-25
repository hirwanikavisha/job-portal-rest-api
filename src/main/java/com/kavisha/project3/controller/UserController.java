package com.kavisha.project3.controller;

import com.kavisha.project3.dto.UserLoginRequestDto;
import com.kavisha.project3.dto.UserLoginResponseDto;
import com.kavisha.project3.dto.UserRegisterRequestDto;
import com.kavisha.project3.dto.UserResponseDto;
import com.kavisha.project3.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Public Endpoint
    @PostMapping("/register")
    public UserResponseDto registerUser(
            @Valid @RequestBody UserRegisterRequestDto request) {

        return userService.registerUser(request);
    }

    // Public Endpoint
    @PostMapping("/login")
    public UserLoginResponseDto loginUser(
            @Valid @RequestBody UserLoginRequestDto request) {

        return userService.loginUser(request);
    }

    // Admin Only
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponseDto> getAllUsers() {

        return userService.getAllUsers();
    }

    // Any authenticated user
    @GetMapping("/{id}")
    public UserResponseDto getUserById(
            @PathVariable Long id) {

        return userService.getUserById(id);
    }

    // Admin Only
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponseDto updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserRegisterRequestDto request) {

        return userService.updateUser(id, request);
    }

    // Admin Only
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteUserById(
            @PathVariable Long id) {

        return userService.deleteUserById(id);
    }
}