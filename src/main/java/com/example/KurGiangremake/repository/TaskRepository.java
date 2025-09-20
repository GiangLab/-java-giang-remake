package com.example.KurGiangremake.repository;

import com.example.KurGiangremake.domain.Task;
import com.example.KurGiangremake.domain.TaskStatus;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TaskRepository {
    private final Map<Long, Task> tasks = new HashMap<>();
    private long idCounter = 1;

    public Task save(Task task) {
        if (task.getId() == null) {
            task.setId(idCounter++);
        }
        tasks.put(task.getId(), task);
        return task;
    }

    public List<Task> findAll() {
        return new ArrayList<>(tasks.values());
    }

    public List<Task> findPending() {
        return tasks.values().stream()
                .filter(t -> !t.isDeleted() && t.getStatus() == TaskStatus.PENDING)
                .toList();
    }

    public Optional<Task> findById(Long id) {
        return Optional.ofNullable(tasks.get(id));
    }
}
