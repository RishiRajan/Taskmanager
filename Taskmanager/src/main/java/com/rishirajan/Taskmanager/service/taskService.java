package com.rishirajan.Taskmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rishirajan.Taskmanager.Entity.Task;
import com.rishirajan.Taskmanager.repository.TaskRepository;

@Service
public class taskService {
	
	@Autowired
	private TaskRepository repo;
	
	public Task saveTask(Task task) {
		return repo.save(task);		
	}
	
	public List<Task> getTasks(){
		return repo.findAll();
	}
	
	public Task getTaskById(Long Id) {
		return repo.findById(Id).orElse(null);
	}
	
	public String deleteTask(Long id) {
		repo.deleteById(id);
		return "Task Deleted Successfully";
	}
	
	public Task updateTask(Task task) {
		Task existingTask = repo.findById(task.getId()).orElse(null);
		existingTask.setTitle(task.getTitle());
		existingTask.setDescription(task.getDescription());
		existingTask.setState(task.getState());
		existingTask.setAssignedTo(task.getAssignedTo());
		
		return repo.save(existingTask);
	}
	

}
