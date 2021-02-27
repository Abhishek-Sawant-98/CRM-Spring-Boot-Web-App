package com.crm.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.crm.model.Task;
import com.crm.model.User;
import com.crm.queryconstants.AccountConstants;
import com.crm.queryconstants.ContactConstants;
import com.crm.queryconstants.TaskConstants;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // Generates constructor for final fields at compile time
@Repository
public class TaskDaoImpl implements TaskDao {

	// Using final fields and a constructor is generally considered best practise
	// for autowiring dependencies
	private final JdbcTemplate jdbcTemplate;

	@Override
	public boolean addTask(Task task, User user) {
		String insertQuery = TaskConstants.INSERT_TASK;
		String accountKeyQuery = AccountConstants.ACCOUNT_KEY;
		String contactKeyQuery = ContactConstants.CONTACT_KEY;

		task.setIdContact(jdbcTemplate.queryForObject(contactKeyQuery,
				new Object[] { task.getAssignedTo(), user.getUserId() }, Integer.class));
		task.setIdAccount(jdbcTemplate.queryForObject(accountKeyQuery,
				new Object[] { task.getRelatedTo(), user.getUserId() }, Integer.class));

		if (!isTaskRegistered(task, user.getUserId())) {
			jdbcTemplate.update(insertQuery, task.getIdContact(), task.getIdAccount(), task.getName(),
					task.getSubject(), task.getComments(), task.getDueDate(), task.getReminderDate(),
					task.getReminderTime(), task.getStatus(), task.getPriority());

			return true;
		}
		return false;
	}

	@Override
	public List<Task> getTaskByUserId(String userId) {
		String taskListQuery = TaskConstants.FETCH_TASKS;

		return jdbcTemplate.query(taskListQuery, new Object[] { userId }, rs -> {
			List<Task> taskList = new ArrayList<>();
			while (rs.next()) {
				Task task = new Task();
				task.setAssignedTo(rs.getString("AssignedTo"));
				task.setRelatedTo(rs.getString("RelatedTo"));
				task.setName(rs.getString("name"));
				task.setSubject(rs.getString("subject"));
				task.setComments(rs.getString("comments"));
				task.setDueDate(rs.getString("due_date"));
				task.setReminderDate(rs.getString("reminder_date"));
				task.setReminderTime(rs.getString("reminder_time"));
				task.setStatus(rs.getString("status"));
				task.setPriority(rs.getString("priority"));
				taskList.add(task);
			}
			return taskList;
		});
	}

	@Override
	public boolean isTaskRegistered(Task task, String userId) {
		String taskOccurrencesQuery = TaskConstants.TASK_OCCURRENCES;

		return jdbcTemplate.queryForObject(taskOccurrencesQuery,
				new Object[] { task.getName(), task.getSubject(), userId, userId }, Integer.class) == 1;
	}

}
