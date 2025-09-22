package com.example.KurGiangremake.controller;

import com.example.KurGiangremake.domain.Task;
import com.example.KurGiangremake.domain.TaskStatus;
import com.example.KurGiangremake.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class TaskControllerTest {

    private TaskService taskService;
    private TaskController taskController;

    @BeforeEach
    void setUp() {
        taskService = Mockito.mock(TaskService.class);
        taskController = new TaskController(taskService);
    }

    @Test
    void testGetAllTasks() {
        Task task = new Task();
        task.setId(1L);
        task.setUserId(1L);
        task.setTitle("Title");
        task.setDescription("Desc");
        task.setStatus(TaskStatus.PENDING);
        task.setStartDate(LocalDate.now());
        task.setEndDate(LocalDate.now().plusDays(1));
        task.setDeleted(false);

        when(taskService.getAllTasks(1L)).thenReturn(List.of(task));

        List<Task> result = taskController.getAllTasks(1L);
        assertEquals(1, result.size());
        assertEquals("Title", result.get(0).getTitle());
    }

    @Test
    void testGetPendingTasks() {
        Task task = new Task();
        task.setId(2L);
        task.setUserId(1L);
        task.setTitle("Task Pending");
        task.setStatus(TaskStatus.PENDING);
        task.setDeleted(false);

        when(taskService.getPendingTasks(1L)).thenReturn(List.of(task));

        List<Task> result = taskController.getPendingTasks(1L);
        assertEquals(1, result.size());
        assertEquals(TaskStatus.PENDING, result.get(0).getStatus());
        assertEquals("Task Pending", result.get(0).getTitle());
    }
}
