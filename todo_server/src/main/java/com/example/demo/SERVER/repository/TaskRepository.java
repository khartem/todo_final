package com.example.demo.SERVER.repository;

import com.example.demo.SERVER.tables.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    public Task findTaskById(Long id);
}
