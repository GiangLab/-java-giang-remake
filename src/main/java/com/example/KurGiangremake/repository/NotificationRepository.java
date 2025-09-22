package com.example.KurGiangremake.repository;

import com.example.KurGiangremake.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    // Get all notifications for a user
    List<Notification> findByUserId(Long userId);

    // Get all pending notifications for a user
    @Query("SELECT n FROM Notification n WHERE n.user.id = :userId AND n.status = 'PENDING'")
    List<Notification> findPendingByUserId(Long userId);
}
