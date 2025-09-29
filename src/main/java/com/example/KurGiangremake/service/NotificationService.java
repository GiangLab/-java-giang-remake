package com.example.KurGiangremake.service;

import com.example.KurGiangremake.domain.Notification;
import com.example.KurGiangremake.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    // Lấy tất cả notification của user
    public List<Notification> getAllUserNotifications(Long userId) {
        return notificationRepository.findByUserId(userId);
    }

    // Lấy các notification còn pending của user
    public List<Notification> getPendingUserNotifications(Long userId) {
        return notificationRepository.findPendingByUserId(userId);
    }

    // Thêm notification mới
    public Notification addNotification(Notification notification) {
        return notificationRepository.save(notification);
    }
}
