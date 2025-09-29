package com.example.KurGiangremake.service;

import com.example.KurGiangremake.domain.Task;
import com.example.KurGiangremake.domain.TaskStatus;
import com.example.KurGiangremake.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskSchedulerService {

    private static final Logger logger = LoggerFactory.getLogger(TaskSchedulerService.class);

    private final TaskRepository taskRepository;

    public TaskSchedulerService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    //Run every 1 minute to check for overdue tasks
    @Scheduled(fixedRate = 60000)  // 60s
    public void checkOverdueTasks() {
        List<Task> tasks = taskRepository.findAll();
        LocalDate now = LocalDate.now();

        tasks.stream()
                .filter(task -> !task.isDeleted()
                        && task.getStatus() == TaskStatus.PENDING
                        && task.getEndDate().isBefore(now))
                .forEach(task -> {
                    task.setStatus(TaskStatus.OVERDUE);
                    taskRepository.save(task);
                    logger.info("Task {} marked as OVERDUE", task.getId());
                });
    }
}
