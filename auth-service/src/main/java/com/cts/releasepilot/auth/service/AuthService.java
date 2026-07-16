package com.cts.releasepilot.auth.service;

import com.cts.releasepilot.auth.dto.AuthResponseDTO;
import com.cts.releasepilot.auth.dto.LoginRequestDTO;
import com.cts.releasepilot.auth.dto.RegisterRequestDTO;
import com.cts.releasepilot.auth.dto.UserResponseDTO;

/** Authentication operations: login (token issuing) and registration. */
public interface AuthService {

    AuthResponseDTO login(LoginRequestDTO request);

    UserResponseDTO register(RegisterRequestDTO request);
}
