package com.crm.queryconstants;

public class OpportunityConstants {

	// Inserting an opportunity
	public static final String INSERT_OPPORTUNITY = "INSERT INTO `opportunity` (`opportunity_owner`, `idAccount`, "
			+ "`opportunity_name`, `type`, `stage`, `probability`, `close_date`, `lead_source`, `amount`, `description`, `next_step`) "
			+ "values(?,?,?,?,?,?,?,?,?,?,?);";

	// To fetch opportunities based on user_id
	public static final String FETCH_OPPORTUNITIES = "SELECT o.opportunity_name, a.account_name AS RelatedTo, o.type, o.stage, "
			+ "o.probability, o.close_date, o.lead_source, o.amount, o.description, o.next_step "
			+ "FROM `opportunity` o, `account` a WHERE o.idAccount = a.idAccount AND o.idAccount IN "
			+ "(SELECT `idAccount` FROM `account` WHERE `account_owner` = ?);";

	// Finding the opportunity occurrences
	public static final String OPPORTUNITY_OCCURRENCES = "SELECT COUNT(*) FROM `opportunity` WHERE "
			+ "`opportunity_owner` = ? AND `opportunity_name` = ?;";
}
