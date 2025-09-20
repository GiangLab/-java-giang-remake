package com.example.KurGiangremake.service;

import com.example.KurGiangremake.domain.*;
import com.example.KurGiangremake.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    public TaskService(TaskRepository taskRepository) { this.taskRepository = taskRepository; }

    public Task createTask(Task task) {
        task.setCreatedAt(LocalDateTime.now());
        task.setStatus(TaskStatus.PENDING);
        task.setDeleted(false);
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll().stream()
                .filter(t -> !t.isDeleted())
                .toList();
    }

    public List<Task> getPendingTasks() {
        return taskRepository.findPending();
    }

    public boolean deleteTask(Long id) {
        return taskRepository.findById(id).map(task -> {
            task.setDeleted(true);
            task.setStatus(TaskStatus.DELETED);
            taskRepository.save(task);
            return true;
        }).orElse(false);
    }
}
