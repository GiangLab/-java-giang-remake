package com.example.KurGiangremake.domain;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Notification {
    private Long id;
    private Long userId;
    private String message;
    private NotificationType type;
    private LocalDateTime createdAt;
    private boolean read;
}
