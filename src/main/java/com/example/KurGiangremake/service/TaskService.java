package com.example.KurGiangremake.service;

import com.example.KurGiangremake.domain.Task;
import com.example.KurGiangremake.domain.TaskStatus;
import com.example.KurGiangremake.repository.TaskRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Get all tasks of a user (cache by userId)
    @Cacheable(value = "tasks", key = "#userId")
    public List<Task> getAllTasks(Long userId) {
        return taskRepository.findByUserIdAndDeletedFalse(userId);
    }

    // Get pending tasks of a user (cache by userId + status)
    @Cacheable(value = "tasksPending", key = "#userId")
    public List<Task> getPendingTasks(Long userId) {
        return taskRepository.findByUserIdAndStatusAndDeletedFalse(userId, TaskStatus.PENDING);
    }

    // Mark a task as deleted and evict cache for the user
    @CacheEvict(value = {"tasks", "tasksPending"}, key = "#taskId")
    public boolean markTaskDeleted(Long taskId) {
        Optional<Task> taskOpt = taskRepository.findById(taskId);
        taskOpt.ifPresent(task -> {
            task.setDeleted(true);
            taskRepository.save(task);
        });
        return taskOpt.isPresent();
    }

    // Add new task and evict cache for the user
    @CacheEvict(value = {"tasks", "tasksPending"}, key = "#task.userId")
    public Task addTask(Task task) {
        return taskRepository.save(task);
    }
}
