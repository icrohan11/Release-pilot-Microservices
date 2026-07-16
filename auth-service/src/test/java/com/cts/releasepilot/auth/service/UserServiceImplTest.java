package com.cts.releasepilot.auth.service;

import com.cts.releasepilot.auth.common.Role;
import com.cts.releasepilot.auth.common.UserStatus;
import com.cts.releasepilot.auth.dto.UserResponseDTO;
import com.cts.releasepilot.auth.dto.UserUpdateDTO;
import com.cts.releasepilot.auth.entity.User;
import com.cts.releasepilot.auth.exception.ResourceNotFoundException;
import com.cts.releasepilot.auth.mapper.UserMapper;
import com.cts.releasepilot.auth.repository.UserRepository;
import com.cts.releasepilot.auth.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock private UserRepository userRepository;
    @Mock private UserMapper userMapper;
    @InjectMocks private UserServiceImpl userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User(1L, "Alice", Role.Admin, "alice@cts.com", "hashed",
                "1234567", 10L, UserStatus.Active);
    }

    @Test
    void getUserById_found() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userMapper.toResponse(user)).thenReturn(new UserResponseDTO());
        assertNotNull(userService.getUserById(1L));
    }

    @Test
    void getUserById_notFound_throws() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> userService.getUserById(99L));
    }

    @Test
    void getAllUsers_returnsMappedList() {
        when(userRepository.findAll()).thenReturn(List.of(user));
        when(userMapper.toResponse(user)).thenReturn(new UserResponseDTO());
        assertEquals(1, userService.getAllUsers().size());
    }

    @Test
    void updateUser_success() {
        UserUpdateDTO dto = new UserUpdateDTO();
        dto.setEmail("alice@cts.com");
        dto.setName("Alice2");
        dto.setRole(Role.Admin);
        dto.setStatus(UserStatus.Active);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.findByEmail("alice@cts.com")).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toResponse(user)).thenReturn(new UserResponseDTO());

        assertNotNull(userService.updateUser(1L, dto));
        verify(userMapper).updateEntity(user, dto);
    }

    @Test
    void deleteUser_notFound_throws() {
        when(userRepository.findById(5L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> userService.deleteUser(5L));
        verify(userRepository, never()).delete(any());
    }
}
