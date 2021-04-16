package com.crm.queryconstants;

public class EventConstants {

	// To insert an event
	public static final String INSERT_EVENT = "INSERT INTO `event` (`idContact`,`idAccount`,`subject`, `name`, "
			+ "`date`, `start`, `end`, `location`, `description`) " + "values(?,?,?,?,?,?,?,?,?);";

	// To fetch events based on user_id
	public static final String FETCH_EVENTS = "SELECT DISTINCT CONCAT(`first_name`, ' ', `last_name`) as AssignedTo, "
			+ "a.account_name as RelatedTo, e.subject, e.name, e.date, e.start, e.end, "
			+ "e.location, e.description FROM name n, contact c, account a, event e "
			+ "WHERE e.idContact=n.idContact AND e.idAccount=a.idAccount AND e.idContact in "
			+ "(SELECT `idContact` FROM `contact` WHERE `contact_owner` = ?);";

	// Finding event occurrences
	public static final String EVENT_OCCURRENCES = "SELECT COUNT(*) FROM `event` WHERE `name` = ? AND `subject` = ? "
			+ "AND `idContact` IN (SELECT `idContact` FROM `contact` WHERE `contact_owner` = ?) "
			+ "AND `idAccount` IN (SELECT `idAccount` FROM `account` WHERE `account_owner` = ?);";
}
