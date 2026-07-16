package com.cts.releasepilot.auth.service.impl;

import com.cts.releasepilot.auth.common.Role;
import com.cts.releasepilot.auth.common.UserStatus;
import com.cts.releasepilot.auth.dto.UserResponseDTO;
import com.cts.releasepilot.auth.dto.UserUpdateDTO;
import com.cts.releasepilot.auth.entity.User;
import com.cts.releasepilot.auth.exception.DuplicateResourceException;
import com.cts.releasepilot.auth.exception.ResourceNotFoundException;
import com.cts.releasepilot.auth.mapper.UserMapper;
import com.cts.releasepilot.auth.repository.UserRepository;
import com.cts.releasepilot.auth.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDTO getUserById(Long id) {
        return userMapper.toResponse(findOrThrow(id));
    }

    @Override
    @Transactional
    public UserResponseDTO updateUser(Long id, UserUpdateDTO dto) {
        User user = findOrThrow(id);
        // Block changing e-mail to one already used by a different account
        userRepository.findByEmail(dto.getEmail())
                .filter(other -> !other.getUserID().equals(id))
                .ifPresent(other -> {
                    throw new DuplicateResourceException("Email already in use: " + dto.getEmail());
                });
        userMapper.updateEntity(user, dto);
        return userMapper.toResponse(userRepository.save(user));
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        User user = findOrThrow(id);
        userRepository.delete(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDTO> getUsersByRole(Role role) {
        return userRepository.findByRole(role).stream().map(userMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDTO> getUsersByStatus(UserStatus status) {
        return userRepository.findByStatus(status).stream().map(userMapper::toResponse).toList();
    }

    private User findOrThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
    }
}
