package com.crm.repository;

import java.util.List;

import com.crm.model.Task;
import com.crm.model.User;

public interface TaskDao {

	public boolean addTask(Task task, User user);

	public boolean isTaskRegistered(Task task, String userId);

	public List<Task> getTaskByUserId(String userId);

}
