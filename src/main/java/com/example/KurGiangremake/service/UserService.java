package com.example.KurGiangremake.service;

import com.example.KurGiangremake.domain.User;
import com.example.KurGiangremake.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) { this.userRepository = userRepository; }

    public User register(User user) {
        user.setCreatedAt(LocalDateTime.now());
        user.setActive(true);
        return userRepository.save(user);
    }

    public Optional<User> login(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }
}
