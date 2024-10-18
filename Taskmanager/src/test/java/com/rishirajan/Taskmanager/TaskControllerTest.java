package com.rishirajan.Taskmanager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import com.rishirajan.Taskmanager.Entity.Task;
import com.rishirajan.Taskmanager.controller.TaskController;
import com.rishirajan.Taskmanager.service.taskService;

class TaskControllerTest {

    @Mock
    private taskService service;

    @InjectMocks
    private TaskController taskController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddTask() {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Test Task");

        when(service.saveTask(any(Task.class))).thenReturn(task);

        Task result = taskController.addTask(task);

        assertEquals(1L, result.getId());
        assertEquals("Test Task", result.getTitle());
        verify(service, times(1)).saveTask(any(Task.class));
    }

    @Test
    void testUpdateTask() {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Updated Task");

        when(service.updateTask(any(Task.class))).thenReturn(task);

        Task result = taskController.updateTask(task);

        assertEquals(1L, result.getId());
        assertEquals("Updated Task", result.getTitle());
        verify(service, times(1)).updateTask(any(Task.class));
    }

    @Test
    void testGetAllTasks() {
        Task task1 = new Task();
        task1.setId(1L);
        task1.setTitle("Task 1");

        Task task2 = new Task();
        task2.setId(2L);
        task2.setTitle("Task 2");

        List<Task> tasks = Arrays.asList(task1, task2);

        when(service.getTasks()).thenReturn(tasks);

        List<Task> result = taskController.getallTasks();

        assertEquals(2, result.size());
        verify(service, times(1)).getTasks();
    }

    @Test
    void testGetTaskById() {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Task 1");

        when(service.getTaskById(1L)).thenReturn(task);

        Task result = taskController.getTaskByID(1L);

        assertEquals(1L, result.getId());
        assertEquals("Task 1", result.getTitle());
        verify(service, times(1)).getTaskById(1L);
    }

    @Test
    void testDeleteTask() {
        Long taskId = 1L;
        String expectedMessage = "Task deleted successfully";

        when(service.deleteTask(taskId)).thenReturn(expectedMessage);

        String result = taskController.deleteTask(taskId);

        assertEquals(expectedMessage, result);
        verify(service, times(1)).deleteTask(taskId);
    }
}
