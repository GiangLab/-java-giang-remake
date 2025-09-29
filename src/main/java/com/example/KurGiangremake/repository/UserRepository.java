package com.example.KurGiangremake.repository;

import com.example.KurGiangremake.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Tìm user theo username
    Optional<User> findByUsername(String username);

    // Tìm user theo email nếu cần
    Optional<User> findByEmail(String email);

    // Kiểm tra user tồn tại theo username
    boolean existsByUsername(String username);

    // Kiểm tra user tồn tại theo email
    boolean existsByEmail(String email);
}
