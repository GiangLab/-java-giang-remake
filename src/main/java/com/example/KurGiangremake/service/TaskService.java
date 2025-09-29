package com.example.KurGiangremake.service;

import com.example.KurGiangremake.domain.Task;
import com.example.KurGiangremake.domain.TaskStatus;
import com.example.KurGiangremake.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) { this.taskRepository = taskRepository; }

    public List<Task> getAllTasks(Long userId) {
        return taskRepository.findAll().stream()
                .filter(t -> !t.isDeleted() && t.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    public List<Task> getPendingTasks(Long userId) {
        return taskRepository.findAll().stream()
                .filter(t -> t.getStatus() == TaskStatus.PENDING && !t.isDeleted() && t.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    public boolean markTaskDeleted(Long taskId) {
        Optional<Task> task = taskRepository.findById(taskId);
        task.ifPresent(t -> t.setDeleted(true));
        return task.isPresent();
    }

    public void addTask(Task task) { taskRepository.add(task); }
}
