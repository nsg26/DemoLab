package com.mongodb.service;

import com.mongodb.entity.Task;
import com.mongodb.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.StringOperators;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {
	@Autowired
	private TaskRepository taskrepo;

	public Task addTask(Task task)
	{
		task.setTaskId(UUID.randomUUID().toString().split("-")[0]);
		return taskrepo.save(task);
	}

	public List<Task> findAllTasks()
	{
		return taskrepo.findAll();
	}

	public Task getTaskById(String taskId){
		return taskrepo.findById(taskId).get();
	}

	public List<Task> getTaskBySeverity(int severity)
	{
		return taskrepo.findBySeverity(severity);
	}
	public List<Task> getTaskByAssignee(String assignee)
	{
		return taskrepo.getTaskByAssignee(assignee);
	}
	public Task updateTask(Task taskRequest){
		Task existingTask=taskrepo.findById(taskRequest.getTaskId()).get();
		existingTask.setDescription(taskRequest.getDescription());
		existingTask.setAssignee(taskRequest.getAssignee());
		existingTask.setStoryPoint(taskRequest.getStoryPoint());
		existingTask.setSeverity(taskRequest.getSeverity());
		return taskrepo.save(existingTask);
	}

	public String deleteTask(String taskId){
		taskrepo.deleteById(taskId);
		return taskId+ " task deleted from dashboard ";
	}
}
