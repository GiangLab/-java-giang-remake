package com.example.KurGiangremake.controller;

import com.example.KurGiangremake.domain.User;
import com.example.KurGiangremake.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setUsername("john@example.com");
        user.setCreatedAt(LocalDateTime.now());
        user.setActive(true);
    }

    @Test
    void testRegisterUser() {
        when(userService.addUser(user)).thenReturn(user);

        ResponseEntity<User> response = userController.registerUser(user);

        assertEquals("John", response.getBody().getFirstName());
        verify(userService, times(1)).addUser(user);
    }

    @Test
    void testLoginUser() {
        when(userService.getUserById(1L)).thenReturn(Optional.of(user));

        ResponseEntity<User> response = userController.loginUser(1L);

        assertEquals("John", response.getBody().getFirstName());
        verify(userService, times(1)).getUserById(1L);
    }
}
