package com.example.KurGiangremake.repository;

import com.example.KurGiangremake.domain.Notification;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class NotificationRepository {
    private final Map<Long, Notification> notifications = new HashMap<>();
    private long idCounter = 1;

    public Notification save(Notification notification) {
        if (notification.getId() == null) {
            notification.setId(idCounter++);
        }
        notifications.put(notification.getId(), notification);
        return notification;
    }

    public List<Notification> findAllByUserId(Long userId) {
        return notifications.values().stream()
                .filter(n -> n.getUserId().equals(userId))
                .toList();
    }

    public List<Notification> findUnreadByUserId(Long userId) {
        return notifications.values().stream()
                .filter(n -> n.getUserId().equals(userId) && !n.isRead())
                .toList();
    }
}
