package com.example.KurGiangremake.controller;

import com.example.KurGiangremake.domain.Notification;
import com.example.KurGiangremake.service.NotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class NotificationControllerTest {

    private NotificationService notificationService;
    private NotificationController notificationController;

    @BeforeEach
    void setUp() {
        notificationService = mock(NotificationService.class);
        notificationController = new NotificationController(notificationService);
    }

    @Test
    void testGetAllNotifications() {
        Long userId = 1L;
        Notification n = new Notification();
        n.setId(1L);
        n.setMessage("Test message");
        List<Notification> notifications = List.of(n);

        when(notificationService.getAllUserNotifications(userId)).thenReturn(notifications);

        List<Notification> result = notificationController.getAllNotifications(userId);

        assertEquals(1, result.size());
        assertEquals("Test message", result.get(0).getMessage());
        verify(notificationService, times(1)).getAllUserNotifications(userId);
    }

    @Test
    void testGetPendingNotifications() {
        Long userId = 1L;
        Notification n = new Notification();
        n.setId(2L);
        n.setMessage("Pending message");
        List<Notification> pending = List.of(n);

        when(notificationService.getPendingUserNotifications(userId)).thenReturn(pending);

        List<Notification> result = notificationController.getPendingNotifications(userId);

        assertEquals(1, result.size());
        assertEquals("Pending message", result.get(0).getMessage());
        verify(notificationService, times(1)).getPendingUserNotifications(userId);
    }
}
