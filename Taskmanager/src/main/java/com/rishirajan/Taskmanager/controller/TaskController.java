package com.rishirajan.Taskmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rishirajan.Taskmanager.Entity.Task;
import com.rishirajan.Taskmanager.service.taskService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Tag(
		name= "Task REST API CRUD operations",
		description ="CREATE, READ, UPDATE, DELETE operations for Task REST API"
)
@RestController
@CrossOrigin
public class TaskController {
	 private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
	 
	@Autowired
	private taskService service;
	
	@Operation(
			summary = "Create a Task",
			description ="Endpoint to Create a new task"
	)
	@PostMapping("/tasks")
	public Task addTask(@RequestBody Task task) {
		 logger.info("Incoming Task: {}", task);		
		return service.saveTask(task);
	}
	
	
	@Operation(
			summary = "Update a Task",
			description ="Endpoint to Update a task"
	)
	@PutMapping("/tasks")
	public Task updateTask(@RequestBody Task task) {
		return service.updateTask(task);
	}
	
	@Operation(
			summary = "Fetch all Tasks",
			description ="Endpoint to fetch all Tasks"
	)
	@GetMapping("/tasks")
	public List<Task> getallTasks(){
		return service.getTasks();
	}
	
	@Operation(
			summary = "Fetch Task by ID",
			description ="Endpoint to fetch a single task by ID"
	)
	@GetMapping("/tasks/{id}")
	public Task getTaskByID(@PathVariable Long id) {
		return service.getTaskById(id);
	}
	
	
	@Operation(
			summary = "Delete a Task by ID",
			description ="Endpoint to Delete a single task by ID"
	)
	@DeleteMapping("/tasks/{id}")
	public String deleteTask(@PathVariable Long id) {
		return service.deleteTask(id);
	}
	
	
	

}
