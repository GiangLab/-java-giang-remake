package com.example.KurGiangremake.repository;

import com.example.KurGiangremake.domain.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskRepository {
    private final List<Task> tasks = new ArrayList<>();

    public List<Task> findAll() { return tasks; }

    public Optional<Task> findById(Long id) {
        return tasks.stream().filter(t -> t.getId().equals(id)).findFirst();
    }

    public void add(Task task) { tasks.add(task); }
}

