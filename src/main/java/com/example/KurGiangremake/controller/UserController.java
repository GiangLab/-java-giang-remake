package com.example.KurGiangremake.controller;

import com.example.KurGiangremake.domain.User;
import com.example.KurGiangremake.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) { this.userService = userService; }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.ok(userService.register(user));
    }

    @GetMapping("/login")
    public ResponseEntity<User> login(@RequestParam String username, @RequestParam String password) {
        return userService.login(username, password)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(401).build());
    }
}

