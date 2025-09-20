package com.example.KurGiangremake.domain;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Task {
    private Long id;
    private Long userId;
    private String title;
    private String description;
    private TaskStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime targetDate;
    private boolean deleted;
}
