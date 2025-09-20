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
        Task task = new Task(1L, 1L, "Title", "Desc", TaskStatus.PENDING, LocalDate.now(), LocalDate.now().plusDays(1), false);
        when(taskService.getAllTasks(1L)).thenReturn(List.of(task));

        List<Task> result = taskController.getAllTasks(1L);
        assertEquals(1, result.size());
    }

    @Test
    void testGetPendingTasks() {
        Task task = new Task(1L, 1L, "Title", "Desc", TaskStatus.PENDING, LocalDate.now(), LocalDate.now().plusDays(1), false);
        when(taskService.getPendingTasks(1L)).thenReturn(List.of(task));

        List<Task> result = taskController.getPendingTasks(1L);
        assertEquals(1, result.size());
        assertEquals(TaskStatus.PENDING, result.get(0).getStatus());
    }
}
