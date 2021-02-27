package com.cognizant.crm.service;

import java.util.List;

import org.springframework.validation.BindingResult;

import com.cognizant.crm.model.Task;
import com.cognizant.crm.model.User;

public interface TaskService {

	public boolean addTask(Task task, User user);

	public List<Task> getTasksByUserId(User user);

	public void validate(Task task, BindingResult results);
}
