package com.example.KurGiangremake.controller;

import com.example.KurGiangremake.domain.Notification;
import com.example.KurGiangremake.service.NotificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/all/{userId}")
    public List<Notification> getAllNotifications(@PathVariable Long userId) {
        // đổi tên phương thức khớp với service
        return notificationService.getAllUserNotifications(userId);
    }

    @GetMapping("/pending/{userId}")
    public List<Notification> getPendingNotifications(@PathVariable Long userId) {
        // đổi tên phương thức khớp với service
        return notificationService.getPendingUserNotifications(userId);
    }
}
