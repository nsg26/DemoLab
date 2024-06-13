package com.mongodb.controller;

import com.mongodb.entity.Task;
import com.mongodb.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {
@Autowired
	private TaskService serv;
@PostMapping
@ResponseStatus(HttpStatus.CREATED)
	public Task createtask(@RequestBody Task task)
	{

		return serv.addTask(task);
	}
	@GetMapping
	public List<Task> getTasks()
	{
		return serv.findAllTasks();
	}
	@GetMapping("/{taskId}")
	public Task getTask(@PathVariable String taskId){
		return serv.getTaskById(taskId);
	}
@GetMapping("severity/{severity}")
	public List<Task> findTaskUsingSeverity(@PathVariable int severity)
	{
		return serv.getTaskBySeverity(severity);
	}
	@GetMapping("assignee/{assignee}")
	public List<Task> getTaskByAssignee(@PathVariable String assignee)
	{
		return serv.getTaskByAssignee(assignee);
	}
	@PutMapping
	public Task modifyTask(@RequestBody Task task){

		return serv.updateTask(task);
	}
	@DeleteMapping("/{taskId}")
	public String deleteTask(@PathVariable String taskId){
		return serv.deleteTask(taskId);
	}

}
