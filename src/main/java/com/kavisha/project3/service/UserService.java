package com.kavisha.project3.service;

import com.kavisha.project3.dto.UserLoginRequestDto;
import com.kavisha.project3.dto.UserLoginResponseDto;
import com.kavisha.project3.dto.UserRegisterRequestDto;
import com.kavisha.project3.dto.UserResponseDto;
import com.kavisha.project3.entity.User;
import com.kavisha.project3.exception.ResourceNotFoundException;
import com.kavisha.project3.repository.UserRepository;
import com.kavisha.project3.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager,
                       JwtUtil jwtUtil) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    // Register User
    public UserResponseDto registerUser(UserRegisterRequestDto request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        User u = new User();

        u.setName(request.getName());
        u.setEmail(request.getEmail());

        u.setPassword(
                passwordEncoder.encode(
                        request.getPassword()
                )
        );

        u.setRole(request.getRole());

        User savedUser = userRepository.save(u);

        return new UserResponseDto(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getRole()
        );
    }

    // Login User
    public UserLoginResponseDto loginUser(
            UserLoginRequestDto request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User u = userRepository.findByEmail(
                        request.getEmail())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"));

        String token =
                jwtUtil.generateToken(
                        u.getEmail()
                );

        return new UserLoginResponseDto(
                u.getId(),
                u.getName(),
                u.getEmail(),
                u.getRole(),
                token
        );
    }

    // Get All Users
    public List<UserResponseDto> getAllUsers() {

        List<User> users = userRepository.findAll();
        List<UserResponseDto> response = new ArrayList<>();

        for (User u : users) {

            response.add(
                    new UserResponseDto(
                            u.getId(),
                            u.getName(),
                            u.getEmail(),
                            u.getRole()
                    )
            );
        }

        return response;
    }

    // Get User By Id
    public UserResponseDto getUserById(Long id) {

        User u = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"));

        return new UserResponseDto(
                u.getId(),
                u.getName(),
                u.getEmail(),
                u.getRole()
        );
    }

    // Update User
    public UserResponseDto updateUser(
            Long id,
            UserRegisterRequestDto request) {

        User u = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"));

        u.setName(request.getName());
        u.setEmail(request.getEmail());

        u.setPassword(
                passwordEncoder.encode(
                        request.getPassword()
                )
        );

        u.setRole(request.getRole());

        User savedUser =
                userRepository.save(u);

        return new UserResponseDto(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getRole()
        );
    }

    // Delete User
    public String deleteUserById(Long id) {

        User u = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"));

        userRepository.delete(u);

        return "User Deleted Successfully";
    }
}