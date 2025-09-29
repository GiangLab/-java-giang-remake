package com.example.KurGiangremake.service;

import com.example.KurGiangremake.domain.Task;
import com.example.KurGiangremake.domain.TaskStatus;
import com.example.KurGiangremake.repository.TaskRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public TaskService(TaskRepository taskRepository, KafkaTemplate<String, String> kafkaTemplate) {
        this.taskRepository = taskRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    // Get all user tasks
    public List<Task> getAllTasks(Long userId) {
        return taskRepository.findByUserIdAndDeletedFalse(userId);
    }

    // Get user's pending tasks
    public List<Task> getPendingTasks(Long userId) {
        return taskRepository.findByUserIdAndStatusAndDeletedFalse(userId, TaskStatus.PENDING);
    }

    // Mark task as deleted
    public boolean markTaskDeleted(Long taskId) {
        Optional<Task> taskOpt = taskRepository.findById(taskId);
        taskOpt.ifPresent(task -> {
            task.setDeleted(true);
            taskRepository.save(task);
        });
        return taskOpt.isPresent();
    }

    // Add new task
    public Task addTask(Task task) {
        Task savedTask = taskRepository.save(task);

        // Publish Kafka messages asynchronously
        publishTaskCreatedEvent(savedTask);

        return savedTask;
    }

    @Async
    protected CompletableFuture<Void> publishTaskCreatedEvent(Task task) {
        String message = "New task created for userId=" + task.getUserId() +
                " with title=" + task.getTitle();
        kafkaTemplate.send("tasks", message);
        return CompletableFuture.completedFuture(null);
    }
}
