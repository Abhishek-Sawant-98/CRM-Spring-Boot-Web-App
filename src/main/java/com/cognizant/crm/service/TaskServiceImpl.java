package com.cognizant.crm.service;

import com.cognizant.crm.model.User;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.cognizant.crm.model.Task;
import com.cognizant.crm.repository.TaskDao;
import com.cognizant.crm.validation.TaskValidator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // Generates constructor for final fields at compile time
@Service
public class TaskServiceImpl implements TaskService {

	// Using final fields and a constructor is generally considered best practise
	// for autowiring dependencies
	private final TaskDao taskDaoImpl;

	@Override
	public boolean addTask(Task task, User user) {
		return taskDaoImpl.addTask(task, user);
	}

	@Override
	public List<Task> getTasksByUserId(User user) {
		return taskDaoImpl.getTaskByUserId(user.getUserId());
	}

	@Override
	public void validate(Task task, BindingResult results) {
		new TaskValidator().validate(task, results);
	}
}
