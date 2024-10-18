package com.rishirajan.Taskmanager;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.rishirajan.Taskmanager.Entity.Task;
import com.rishirajan.Taskmanager.repository.TaskRepository;
import com.rishirajan.Taskmanager.service.*;

class TaskServiceTest {

    @Mock
    private TaskRepository repo;

    @InjectMocks
    private taskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveTask() {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Sample Task");
        task.setDescription("This is a sample task.");

        when(repo.save(any(Task.class))).thenReturn(task);

        Task result = taskService.saveTask(task);

        assertEquals(1L, result.getId());
        assertEquals("Sample Task", result.getTitle());
        assertEquals("This is a sample task.", result.getDescription());
        verify(repo, times(1)).save(any(Task.class));
    }

    @Test
    void testGetTasks() {
        Task task1 = new Task();
        task1.setId(1L);
        task1.setTitle("Task 1");

        Task task2 = new Task();
        task2.setId(2L);
        task2.setTitle("Task 2");

        List<Task> tasks = Arrays.asList(task1, task2);

        when(repo.findAll()).thenReturn(tasks);

        List<Task> result = taskService.getTasks();

        assertEquals(2, result.size());
        verify(repo, times(1)).findAll();
    }

    @Test
    void testGetTaskById_Found() {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Task 1");

        when(repo.findById(1L)).thenReturn(Optional.of(task));

        Task result = taskService.getTaskById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Task 1", result.getTitle());
        verify(repo, times(1)).findById(1L);
    }

    @Test
    void testGetTaskById_NotFound() {
        when(repo.findById(1L)).thenReturn(Optional.empty());

        Task result = taskService.getTaskById(1L);

        assertNull(result);
        verify(repo, times(1)).findById(1L);
    }

    @Test
    void testDeleteTask() {
        Long taskId = 1L;
        doNothing().when(repo).deleteById(taskId);

        String result = taskService.deleteTask(taskId);

        assertEquals("Task Deleted Successfully", result);
        verify(repo, times(1)).deleteById(taskId);
    }

    @Test
    void testUpdateTask() {
        Task existingTask = new Task();
        existingTask.setId(1L);
        existingTask.setTitle("Old Title");
        existingTask.setDescription("Old Description");
        existingTask.setState("Open");
        existingTask.setAssignedTo("John");

        Task updatedTask = new Task();
        updatedTask.setId(1L);
        updatedTask.setTitle("New Title");
        updatedTask.setDescription("New Description");
        updatedTask.setState("In Progress");
        updatedTask.setAssignedTo("Jane");

        when(repo.findById(1L)).thenReturn(Optional.of(existingTask));
        when(repo.save(any(Task.class))).thenReturn(updatedTask);

        Task result = taskService.updateTask(updatedTask);

        assertEquals(1L, result.getId());
        assertEquals("New Title", result.getTitle());
        assertEquals("New Description", result.getDescription());
        assertEquals("In Progress", result.getState());
        assertEquals("Jane", result.getAssignedTo());
        verify(repo, times(1)).findById(1L);
        verify(repo, times(1)).save(any(Task.class));
    }
}
