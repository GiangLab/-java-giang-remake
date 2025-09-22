package com.example.KurGiangremake.service;

import com.example.KurGiangremake.domain.User;
import com.example.KurGiangremake.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Add a new user
    public User addUser(User user) {
        return userRepository.save(user);
    }

    // Get user by ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Register a user (same as addUser)
    public User register(User user) {
        return userRepository.save(user);
    }

    // Login by username
    public Optional<User> login(String username) {
        return userRepository.findByUsername(username);
    }
}
