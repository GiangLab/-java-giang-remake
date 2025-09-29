package com.example.KurGiangremake.repository;

import com.example.KurGiangremake.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    // Lấy tất cả notification của user
    List<Notification> findByUserId(Long userId);

    // Lấy các notification còn pending của user
    @Query("SELECT n FROM Notification n WHERE n.user.id = :userId AND n.status = 'PENDING'")
    List<Notification> findPendingByUserId(Long userId);

}
