package com.cognizant.crm.queryconstants;

public class CaseConstants {

	// Inserting a case
	public static final String INSERT_CASE = "INSERT INTO `crm`.`case` (`case_owner`, `idContact`, "
			+ "`idAccount`, `email_id`, `status`, `type`, `case_origin`, `case_reason`, `case_priority`, `subject`, "
			+ "`description`, `internal_comments`) values(?,?,?,?,?,?,?,?,?,?,?,?);";

	// To fetch cases based on user_id
	public static final String FETCH_CASES = "SELECT CONCAT(`first_name`, ' ', `last_name`) as Contact_Name, "
			+ "c.mobile, a.account_name as Account_Name, cs.email_id, cs.subject, cs.status, cs.type, cs.case_origin, "
			+ "cs.case_reason, cs.case_priority, cs.description FROM `name` n, `contact` c, `account` a, `case` cs "
			+ "WHERE cs.idContact = n.idContact AND cs.idAccount = a.idAccount AND cs.idContact IN "
			+ "(SELECT `idContact` FROM `contact` WHERE `contact_owner` = ?);";

	// Finding case occurrences
	public static final String CASE_OCCURRENCES = "SELECT COUNT(*) FROM `case` WHERE `case_owner` = ? AND `subject` = ?";
}
