package com.example.KurGiangremake.service;

import com.example.KurGiangremake.domain.Notification;
import com.example.KurGiangremake.domain.NotificationType;
import com.example.KurGiangremake.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public Notification createNotification(Long userId, String message, NotificationType type) {
        Notification n = new Notification();
        n.setUserId(userId);
        n.setMessage(message);
        n.setType(type);
        n.setCreatedAt(LocalDateTime.now());
        n.setRead(false);
        return notificationRepository.save(n);
    }

    public List<Notification> getAllUserNotifications(Long userId) {
        return notificationRepository.findAllByUserId(userId);
    }

    public List<Notification> getPendingUserNotifications(Long userId) {
        return notificationRepository.findUnreadByUserId(userId);
    }
}
