package com.cts.releasepilot.auth.controller;

import com.cts.releasepilot.auth.dto.UserResponseDTO;
import com.cts.releasepilot.auth.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserControllerTest {

    private MockMvc mockMvc;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = Mockito.mock(UserService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new UserController(userService)).build();
    }

    @Test
    void getAll_returns200() throws Exception {
        when(userService.getAllUsers()).thenReturn(List.of(new UserResponseDTO(), new UserResponseDTO()));
        mockMvc.perform(get("/users/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void delete_returns200() throws Exception {
        mockMvc.perform(delete("/users/delete/1"))
                .andExpect(status().isOk());
        verify(userService).deleteUser(1L);
    }
}
