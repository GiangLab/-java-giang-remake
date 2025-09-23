package com.example.KurGiangremake.service;

import com.example.KurGiangremake.domain.Notification;
import com.example.KurGiangremake.domain.NotificationStatus;
import com.example.KurGiangremake.domain.User;
import com.example.KurGiangremake.repository.NotificationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NotificationServiceTest {

    private NotificationRepository notificationRepository;
    private NotificationService notificationService;

    private User user;

    @BeforeEach
    void setUp() {
        notificationRepository = Mockito.mock(NotificationRepository.class);
        notificationService = new NotificationService(notificationRepository);

        user = new User();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");
    }

    @Test
    void testGetAllUserNotifications() {
        Notification n1 = new Notification();
        n1.setId(1L);
        n1.setUser(user);
        n1.setMessage("Message 1");
        n1.setStatus(NotificationStatus.PENDING);

        Notification n2 = new Notification();
        n2.setId(2L);
        n2.setUser(user);
        n2.setMessage("Message 2");
        n2.setStatus(NotificationStatus.SENT);

        List<Notification> all = List.of(n1, n2);

        when(notificationRepository.findByUserId(1L)).thenReturn(all);

        List<Notification> result = notificationService.getAllUserNotifications(1L);

        assertEquals(2, result.size());
        verify(notificationRepository, times(1)).findByUserId(1L);
    }

    @Test
    void testGetPendingUserNotifications() {
        Notification n1 = new Notification();
        n1.setId(3L);
        n1.setUser(user);
        n1.setMessage("Pending Message 1");
        n1.setStatus(NotificationStatus.PENDING);

        List<Notification> pending = List.of(n1);

        when(notificationRepository.findPendingByUserId(1L)).thenReturn(pending);

        List<Notification> result = notificationService.getPendingUserNotifications(1L);

        assertEquals(1, result.size());
        verify(notificationRepository, times(1)).findPendingByUserId(1L);
    }
}
