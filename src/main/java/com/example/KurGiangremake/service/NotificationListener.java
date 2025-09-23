package com.example.KurGiangremake.service;

import com.example.KurGiangremake.domain.Notification;
import com.example.KurGiangremake.domain.NotificationStatus;
import com.example.KurGiangremake.domain.User;
import com.example.KurGiangremake.repository.NotificationRepository;
import com.example.KurGiangremake.repository.UserRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationListener {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public NotificationListener(NotificationRepository notificationRepository, UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    @KafkaListener(topics = "tasks", groupId = "task-group")
    public void listen(String message) {
        // Giả định message chứa userId và title
        // Ví dụ: "New task created for userId=1 with title=Task 1"
        String[] parts = message.split(" ");
        Long userId = Long.parseLong(parts[5]);
        String title = message.substring(message.indexOf("title=") + 6);

        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            Notification notification = new Notification();
            notification.setUser(user);
            notification.setMessage("New task created: " + title);
            notification.setStatus(NotificationStatus.PENDING);
            notificationRepository.save(notification);
        }
    }
}
