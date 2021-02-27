package com.crm.queryconstants;

public class LeadConstants {
	
	// Inserting a lead
	public static final String INSERT_LEAD = "INSERT INTO `Lead` (`lead_owner`, `lead_status`, `phone`, `company`, "
			+ "`email_id`, `website`, `lead_source`, `employee_count`, `annual_revenue`, `industry`, `description`) "
			+ "values(?,?,?,?,?,?,?,?,?,?,?);";

	// Inserting the lead name
	public static final String INSERT_LEAD_NAME = "INSERT INTO `name` (`salutation`, `first_name`, "
			+ "`last_name`, `idLead`) values(?,?,?,?)";

	// Inserting the lead address
	public static final String INSERT_LEAD_ADDRESS = "INSERT INTO address (`street`, `city`, `state_province`, "
			+ "`zip_postal`, `country`, `idLead`) values(?,?,?,?,?,?)";

	// To fetch leads based on user_id
	public static final String FETCH_LEADS = "SELECT CONCAT(n.first_name, ' ', n.last_name) AS Name, "
			+ "CONCAT(`street`,', ', `city`,', ', `state_province`,', ', `zip_postal`,', ', ad.country,', ') AS Address, "
			+ "l.lead_status, l.phone, l.company, l.email_id, l.website, l.lead_source, l.employee_count, l.annual_revenue, l.industry "
			+ "FROM name n, address ad, `lead` l WHERE l.idLead = n.idLead AND l.idLead = ad.idLead AND l.idLead IN "
			+ "(SELECT `idLead` FROM `lead` WHERE `lead_owner` = ?);";

	// Finding the lead occurrences
	public static final String LEAD_OCCURRENCES = "SELECT COUNT(*) FROM `Lead` WHERE `lead_owner` = ? AND `phone` = ?;";
}
