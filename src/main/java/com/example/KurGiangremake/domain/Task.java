package com.example.KurGiangremake.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private Long id;
    private Long userId;
    private String title;
    private String description;
    private TaskStatus status;
    private LocalDate createdAt;
    private LocalDate targetDate;
    private boolean deleted;
}
