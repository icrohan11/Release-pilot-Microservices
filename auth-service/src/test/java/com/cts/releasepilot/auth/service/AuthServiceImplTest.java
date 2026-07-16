package com.cts.releasepilot.auth.service;

import com.cts.releasepilot.auth.common.Role;
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
import com.cts.releasepilot.auth.service.impl.AuthServiceImpl;
import com.cts.releasepilot.auth.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @Mock private UserRepository userRepository;
    @Mock private PasswordEncoder passwordEncoder;
    @Mock private JwtUtil jwtUtil;
    @Mock private UserMapper userMapper;

    @InjectMocks private AuthServiceImpl authService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User(1L, "Alice", Role.Admin, "alice@cts.com", "hashed",
                "1234567", 10L, UserStatus.Active);
    }

    @Test
    void login_success_returnsToken() {
        LoginRequestDTO req = new LoginRequestDTO();
        req.setEmail("alice@cts.com");
        req.setPassword("secret");

        when(userRepository.findByEmail("alice@cts.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("secret", "hashed")).thenReturn(true);
        when(jwtUtil.generateToken("alice@cts.com", "Admin")).thenReturn("token-123");

        AuthResponseDTO res = authService.login(req);

        assertEquals("token-123", res.getToken());
        assertEquals("Admin", res.getRole());
        assertEquals(1L, res.getUserID());
    }

    @Test
    void login_wrongPassword_throws() {
        LoginRequestDTO req = new LoginRequestDTO();
        req.setEmail("alice@cts.com");
        req.setPassword("bad");

        when(userRepository.findByEmail("alice@cts.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("bad", "hashed")).thenReturn(false);

        assertThrows(InvalidCredentialsException.class, () -> authService.login(req));
        verify(jwtUtil, never()).generateToken(anyString(), anyString());
    }

    @Test
    void login_unknownEmail_throws() {
        LoginRequestDTO req = new LoginRequestDTO();
        req.setEmail("ghost@cts.com");
        req.setPassword("x");
        when(userRepository.findByEmail("ghost@cts.com")).thenReturn(Optional.empty());

        assertThrows(InvalidCredentialsException.class, () -> authService.login(req));
    }

    @Test
    void register_duplicateEmail_throws() {
        RegisterRequestDTO req = new RegisterRequestDTO();
        req.setEmail("alice@cts.com");
        when(userRepository.existsByEmail("alice@cts.com")).thenReturn(true);

        assertThrows(DuplicateResourceException.class, () -> authService.register(req));
        verify(userRepository, never()).save(any());
    }

    @Test
    void register_success_encodesPasswordAndSaves() {
        RegisterRequestDTO req = new RegisterRequestDTO();
        req.setEmail("bob@cts.com");
        req.setPassword("plain");
        req.setName("Bob");
        req.setRole(Role.DevLead);

        User mapped = new User();
        mapped.setEmail("bob@cts.com");
        mapped.setPassword("plain");

        when(userRepository.existsByEmail("bob@cts.com")).thenReturn(false);
        when(userMapper.toEntity(req)).thenReturn(mapped);
        when(passwordEncoder.encode("plain")).thenReturn("hashedPlain");
        when(userRepository.save(any(User.class))).thenAnswer(inv -> inv.getArgument(0));
        when(userMapper.toResponse(any(User.class))).thenReturn(new UserResponseDTO());

        authService.register(req);

        assertEquals("hashedPlain", mapped.getPassword());
        assertEquals(UserStatus.Active, mapped.getStatus());
        verify(userRepository).save(mapped);
    }
}
