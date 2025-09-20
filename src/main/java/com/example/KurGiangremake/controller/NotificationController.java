package com.example.KurGiangremake.controller;

import com.example.KurGiangremake.domain.Notification;
import com.example.KurGiangremake.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    private final NotificationService notificationService;
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Notification>> getAllUserNotifications(@PathVariable Long userId) {
        return ResponseEntity.ok(notificationService.getAllUserNotifications(userId));
    }

    @GetMapping("/{userId}/pending")
    public ResponseEntity<List<Notification>> getPendingUserNotifications(@PathVariable Long userId) {
        return ResponseEntity.ok(notificationService.getPendingUserNotifications(userId));
    }
}
