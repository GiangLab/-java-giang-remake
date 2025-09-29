package com.example.KurGiangremake.repository;

import com.example.KurGiangremake.domain.Task;
import com.example.KurGiangremake.domain.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    // Lấy tất cả task của user chưa bị xóa
    List<Task> findByUserIdAndDeletedFalse(Long userId);

    // Lấy tất cả task pending của user chưa bị xóa
    List<Task> findByUserIdAndStatusAndDeletedFalse(Long userId, TaskStatus status);
}
