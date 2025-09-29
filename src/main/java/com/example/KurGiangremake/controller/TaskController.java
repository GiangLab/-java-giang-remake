package com.example.KurGiangremake.controller;

import com.example.KurGiangremake.domain.Task;
import com.example.KurGiangremake.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) { this.taskService = taskService; }

    @GetMapping("/all")
    public List<Task> getAllTasks(@RequestParam Long userId) {
        return taskService.getAllTasks(userId);
    }

    @GetMapping("/pending")
    public List<Task> getPendingTasks(@RequestParam Long userId) {
        return taskService.getPendingTasks(userId);
    }

    @PostMapping
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        taskService.addTask(task);
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        boolean deleted = taskService.markTaskDeleted(id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
