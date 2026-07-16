package com.cts.releasepilot.auth.service.impl;

import com.cts.releasepilot.auth.common.UserStatus;
import com.cts.releasepilot.auth.dto.AuthResponseDTO;
import com.cts.releasepilot.auth.dto.LoginRequestDTO;
import com.cts.releasepilot.auth.dto.RegisterRequestDTO;
import com.cts.releasepilot.auth.dto.UserResponseDTO;
import com.cts.releasepilot.auth.entity.User;
import com.cts.releasepilot.auth.exception.DuplicateResourceException;
import com.cts.releasepilot.auth.exception.InvalidCredentialsException;
import com.cts.releasepilot.auth.mapper.UserMapper;
import com.cts.releasepilot.auth.repository.UserRepository;
import com.cts.releasepilot.auth.service.AuthService;
import com.cts.releasepilot.auth.util.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UserMapper userMapper;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
                           JwtUtil jwtUtil, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public AuthResponseDTO login(LoginRequestDTO request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

        if (user.getStatus() == UserStatus.Inactive) {
            throw new InvalidCredentialsException("User account is inactive");
        }
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
        return new AuthResponseDTO(token, user.getRole().name(), user.getName(),
                user.getEmail(), user.getUserID());
    }

    @Override
    @Transactional
    public UserResponseDTO register(RegisterRequestDTO request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Email already registered: " + request.getEmail());
        }
        User user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setStatus(UserStatus.Active);
        User saved = userRepository.save(user);
        return userMapper.toResponse(saved);
    }
}
