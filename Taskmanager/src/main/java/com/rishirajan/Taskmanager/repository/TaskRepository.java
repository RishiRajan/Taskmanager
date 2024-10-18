package com.rishirajan.Taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rishirajan.Taskmanager.Entity.Task;

public interface TaskRepository extends JpaRepository<Task , Long> {

}

