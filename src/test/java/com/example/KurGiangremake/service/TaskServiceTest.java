package com.example.KurGiangremake.service;

import com.example.KurGiangremake.domain.Task;
import com.example.KurGiangremake.domain.TaskStatus;
import com.example.KurGiangremake.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {

    private TaskRepository taskRepository;
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskRepository = new TaskRepository();
        taskService = new TaskService(taskRepository);
    }

    @Test
    void testAddAndGetTasks() {
        Task task = new Task(1L, 1L, "Title", "Description", TaskStatus.PENDING, LocalDate.now(), LocalDate.now().plusDays(1), false);
        taskService.addTask(task);

        List<Task> tasks = taskService.getAllTasks(1L);
        assertEquals(1, tasks.size());
        assertEquals("Title", tasks.get(0).getTitle());
    }

    @Test
    void testGetPendingTasks() {
        Task task1 = new Task(1L, 1L, "T1", "Desc", TaskStatus.PENDING, LocalDate.now(), LocalDate.now().plusDays(1), false);
        Task task2 = new Task(2L, 1L, "T2", "Desc", TaskStatus.COMPLETED, LocalDate.now(), LocalDate.now().plusDays(1), false);
        taskService.addTask(task1);
        taskService.addTask(task2);

        List<Task> pending = taskService.getPendingTasks(1L);
        assertEquals(1, pending.size());
        assertEquals(TaskStatus.PENDING, pending.get(0).getStatus());
    }

    @Test
    void testMarkTaskDeleted() {
        Task task = new Task(1L, 1L, "Title", "Desc", TaskStatus.PENDING, LocalDate.now(), LocalDate.now().plusDays(1), false);
        taskService.addTask(task);

        boolean deleted = taskService.markTaskDeleted(1L);
        assertTrue(deleted);
        assertTrue(taskService.getAllTasks(1L).get(0).isDeleted());
    }
}
