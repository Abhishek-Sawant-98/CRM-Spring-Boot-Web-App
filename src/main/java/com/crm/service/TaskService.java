package com.crm.service;

import java.util.List;

import org.springframework.validation.BindingResult;

import com.crm.model.Task;
import com.crm.model.User;

public interface TaskService {

	public boolean addTask(Task task, User user);

	public List<Task> getTasksByUserId(User user);

	public void validate(Task task, BindingResult results);
}
