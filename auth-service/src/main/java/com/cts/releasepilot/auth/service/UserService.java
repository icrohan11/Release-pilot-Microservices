package com.cts.releasepilot.auth.service;

import com.cts.releasepilot.auth.common.Role;
import com.cts.releasepilot.auth.common.UserStatus;
import com.cts.releasepilot.auth.dto.UserResponseDTO;
import com.cts.releasepilot.auth.dto.UserUpdateDTO;

import java.util.List;

/** User administration operations. */
public interface UserService {

    List<UserResponseDTO> getAllUsers();

    UserResponseDTO getUserById(Long id);

    UserResponseDTO updateUser(Long id, UserUpdateDTO dto);

    void deleteUser(Long id);

    List<UserResponseDTO> getUsersByRole(Role role);

    List<UserResponseDTO> getUsersByStatus(UserStatus status);
}
