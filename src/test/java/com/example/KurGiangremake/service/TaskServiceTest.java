package com.example.KurGiangremake.service;

import com.example.KurGiangremake.domain.Task;
import com.example.KurGiangremake.domain.TaskStatus;
import com.example.KurGiangremake.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceTest {

    private TaskRepository taskRepository;
    private KafkaTemplate<String, String> kafkaTemplate; // Thêm
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskRepository = Mockito.mock(TaskRepository.class);
        kafkaTemplate = Mockito.mock(KafkaTemplate.class); // Mock Kafka
        taskService = new TaskService(taskRepository, kafkaTemplate); // Truyền đủ args
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

        when(taskRepository.findByUserIdAndDeletedFalse(1L)).thenReturn(List.of(task));

        List<Task> tasks = taskService.getAllTasks(1L);
        assertEquals(1, tasks.size());
        assertEquals("Title", tasks.get(0).getTitle());
    }

    @Test
    void testGetPendingTasks() {
        Task task1 = new Task();
        task1.setId(1L);
        task1.setUserId(1L);
        task1.setTitle("T1");
        task1.setStatus(TaskStatus.PENDING);
        task1.setDeleted(false);

        Task task2 = new Task();
        task2.setId(2L);
        task2.setUserId(1L);
        task2.setTitle("T2");
        task2.setStatus(TaskStatus.COMPLETED);
        task2.setDeleted(false);

        when(taskRepository.findByUserIdAndStatusAndDeletedFalse(1L, TaskStatus.PENDING))
                .thenReturn(List.of(task1));

        List<Task> pending = taskService.getPendingTasks(1L);
        assertEquals(1, pending.size());
        assertEquals(TaskStatus.PENDING, pending.get(0).getStatus());
    }

    @Test
    void testMarkTaskDeleted() {
        Task task = new Task();
        task.setId(1L);
        task.setUserId(1L);
        task.setDeleted(false);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        boolean deleted = taskService.markTaskDeleted(1L);
        assertTrue(deleted);
        assertTrue(task.isDeleted());
    }

    @Test
    void testAddTaskPublishesKafkaMessage() {
        Task task = new Task();
        task.setId(1L);
        task.setUserId(1L);
        task.setTitle("New Task");

        when(taskRepository.save(task)).thenReturn(task);

        Task saved = taskService.addTask(task);

        assertEquals("New Task", saved.getTitle());
        verify(kafkaTemplate, times(1)).send(eq("tasks"), anyString());
        verify(taskRepository, times(1)).save(task);
    }
}
