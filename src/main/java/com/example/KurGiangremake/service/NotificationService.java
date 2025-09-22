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

    // Get all notifications for a user
    public List<Notification> getAllUserNotifications(Long userId) {
        return notificationRepository.findByUserId(userId);
    }

    // Get all pending notifications for a user
    public List<Notification> getPendingUserNotifications(Long userId) {
        return notificationRepository.findPendingByUserId(userId);
    }

    // Add new notification
    public Notification addNotification(Notification notification) {
        return notificationRepository.save(notification);
    }
}
