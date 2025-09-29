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

    // Thêm user mới
    public User addUser(User user) {
        return userRepository.save(user);
    }

    // Lấy user theo ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Lấy tất cả user
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Đăng ký user (tương đương addUser)
    public User register(User user) {
        return userRepository.save(user);
    }

    // Login theo username (repository phải có findByUsername)
    public Optional<User> login(String username) {
        return userRepository.findByUsername(username);
    }
}
