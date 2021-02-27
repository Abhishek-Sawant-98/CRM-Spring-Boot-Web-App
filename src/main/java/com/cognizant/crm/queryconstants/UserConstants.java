package com.cognizant.crm.queryconstants;

public class UserConstants {

	// Inserting user in the 'user' table
	public static final String INSERT_USER = "INSERT INTO `user` (`user_id`, `password`, `first_name`, `last_name`,"
			+ "`date_of_birth`, `gender`, `contact_number`,`email_id`, `job_title`, `company`, `employee_count`, `country`)"
			+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";

	// Finding the no. of occurrences of a user of given user_id and password, in 'user' table
	public static final String FIND_USER_OCCURRENCES = "SELECT COUNT(*) FROM `user` WHERE `user_id` = ? AND `password` = ?;";

	// Finding the no. of occurrences of a user of given user_id, in 'user' table
	public static final String FIND_USER_ID_OCCURRENCES = "SELECT COUNT(*) FROM `user` WHERE `user_id` = ?;";

	// Fetching the primary key of 'user' table for a given user_id and password
	public static final String USER_KEY = "SELECT `idUser` FROM `user` WHERE `user_id`= ? and `password` = ?;";

	// Fetching all the user details of a specific user_id from 'user' table
	public static final String FETCH_USER_BY_ID = "SELECT * FROM `user` WHERE `user_id`= ?;";
}
