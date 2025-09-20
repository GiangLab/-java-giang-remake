package com.example.KurGiangremake.service;

import com.example.KurGiangremake.domain.Notification;
import com.example.KurGiangremake.domain.NotificationStatus;
import com.example.KurGiangremake.domain.User;
import com.example.KurGiangremake.repository.NotificationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class NotificationServiceTest {

    private NotificationRepository notificationRepository;
    private NotificationService notificationService;

    private User user;

    @BeforeEach
    void setUp() {
        // Mock repository
        notificationRepository = mock(NotificationRepository.class);
        notificationService = new NotificationService(notificationRepository);

        // Tạo user mẫu
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

        List<Notification> all = Arrays.asList(n1, n2);

        // Khi gọi repo thì trả về danh sách trên
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

        List<Notification> pending = Arrays.asList(n1);

        // Khi gọi repo thì trả về danh sách pending
        when(notificationRepository.findPendingByUserId(1L)).thenReturn(pending);

        List<Notification> result = notificationService.getPendingUserNotifications(1L);

        assertEquals(1, result.size());
        verify(notificationRepository, times(1)).findPendingByUserId(1L);
    }

    @Test
    void testAddNotification() {
        Notification n = new Notification();
        n.setUser(user);
        n.setMessage("New Notification");
        n.setStatus(NotificationStatus.PENDING);

        when(notificationRepository.save(n)).thenReturn(n);

        Notification saved = notificationService.addNotification(n);

        assertEquals("New Notification", saved.getMessage());
        assertEquals(user.getId(), saved.getUser().getId());
        verify(notificationRepository, times(1)).save(n);
    }
}
