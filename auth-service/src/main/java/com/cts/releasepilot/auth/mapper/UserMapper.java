package com.cts.releasepilot.auth.mapper;

import com.cts.releasepilot.auth.dto.RegisterRequestDTO;
import com.cts.releasepilot.auth.dto.UserResponseDTO;
import com.cts.releasepilot.auth.dto.UserUpdateDTO;
import com.cts.releasepilot.auth.entity.User;
import org.springframework.stereotype.Component;

/** Converts between {@link User} entities and their DTOs. */
@Component
public class UserMapper {

    /** Build a new entity from a registration request (password handled by the service). */
    public User toEntity(RegisterRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        User user = new User();
        user.setName(dto.getName());
        user.setRole(dto.getRole());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setPhone(dto.getPhone());
        user.setProductID(dto.getProductID());
        return user;
    }

    /** Apply an update DTO onto an existing entity. */
    public void updateEntity(User user, UserUpdateDTO dto) {
        user.setName(dto.getName());
        user.setRole(dto.getRole());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setProductID(dto.getProductID());
        user.setStatus(dto.getStatus());
    }

    public UserResponseDTO toResponse(User user) {
        if (user == null) {
            return null;
        }
        UserResponseDTO dto = new UserResponseDTO();
        dto.setUserID(user.getUserID());
        dto.setName(user.getName());
        dto.setRole(user.getRole());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setProductID(user.getProductID());
        dto.setStatus(user.getStatus());
        return dto;
    }
}
