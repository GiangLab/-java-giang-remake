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
        userService.register(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/login")
    public ResponseEntity<User> login(@RequestParam String username) {
        return userService.login(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.addUser(user));
    }

    @GetMapping("/login/{id}")
    public ResponseEntity<User> loginUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id).orElse(null));
    }
}
