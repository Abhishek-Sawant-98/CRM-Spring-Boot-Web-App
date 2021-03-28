package com.crm.queryconstants;

public class TaskConstants {

	// Inserting a task
	public static final String INSERT_TASK = "INSERT INTO `task` (`idContact`,`idAccount`,`name`, `subject`, "
			+ "`comments`, `due_date`, `reminder_date`, `reminder_time`, `status`, `priority`) "
			+ "values(?,?,?,?,?,?,?,?,?,?);";

	// Fetching tasks based on user_id
	public static final String FETCH_TASKS = "SELECT CONCAT(`first_name`, ' ', `last_name`) as AssignedTo, "
			+ "a.account_name as RelatedTo, `name`, `subject`, `comments`, `due_date`, `reminder_date`, "
			+ "`reminder_time`, `status`, `priority` FROM name n, contact c, account a, task t "
			+ "WHERE t.idContact=n.idContact AND t.idAccount=a.idAccount AND t.idContact in "
			+ "(SELECT idContact FROM contact WHERE contact_owner = ?);";

	// Finding the task occurrences
	public static final String TASK_OCCURRENCES = "SELECT COUNT(*) FROM `task` WHERE `name` = ? AND `subject` = ? "
			+ "AND `idContact` IN (SELECT `idContact` FROM `contact` WHERE `contact_owner` = ?) "
			+ "AND `idAccount` IN (SELECT `idAccount` FROM `account` WHERE `account_owner` = ?);";
}
