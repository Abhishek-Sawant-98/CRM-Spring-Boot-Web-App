package com.crm.queryconstants;

public class ContactConstants {

	// Inserting a contact
	public static final String INSERT_CONTACT = "INSERT INTO `contact` (`contact_owner`, `idAccount`, "
			+ "`mobile`, `email_id`, `title`, `fax`, `department`, `home_phone`, `other_phone`, `lead_source`, `birth_date`, `assistant`, "
			+ "`assistant_phone`, `description`) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

	// Fetching the contact id
	public static final String CONTACT_KEY = "SELECT `idContact` FROM `contact` WHERE `mobile` = ? AND `contact_owner` = ?;";

	// Inserting the contact name
	public static final String INSERT_CONTACT_NAME = "INSERT INTO `name` (`salutation`, `first_name`, `last_name`, `idContact`) "
			+ "values(?,?,?,?)";

	// Inserting the contact mailing address
	public static final String INSERT_CONTACT_MAILING_ADDRESS = "INSERT INTO `address` "
			+ "(`street`, `city`, `state_province`, `zip_postal`, `country`, `idContact`, `type`) values(?,?,?,?,?,?,'Mailing');";

	// Inserting the contact other address
	public static final String INSERT_CONTACT_OTHER_ADDRESS = "INSERT INTO `address` "
			+ "(`street`, `city`, `state_province`, `zip_postal`, `country`, `idContact`, `type`) values(?,?,?,?,?,?,'Other');";

	// Fetching contacts for a particular user_id
	public static final String FETCH_CONTACTS = "SELECT CONCAT(n.first_name, ' ', n.last_name) AS Contact_Name, a.account_name, "
			+ "c.mobile, c.email_id, c.birth_date, c.title, c.fax, c.department, c.lead_source, c.assistant_phone, "
			+ "CONCAT(`street`,', ', `city`,', ', `state_province`,', ', `zip_postal`,', ', ad.country) AS Mailing_Address, "
			+ "CONCAT(`street`,', ', `city`,', ', `state_province`,', ', `zip_postal`,', ', ad.country) AS Other_Address "
			+ "FROM `name` n, `contact` c, `account` a, `address` ad WHERE c.idContact = ad.idContact AND c.idContact = n.idContact AND "
			+ "c.idAccount = a.idAccount AND c.idContact IN (SELECT `idContact` from `contact` WHERE `contact_owner` = ?);";

	// Inserting the contact occurrences
	public static final String CONTACT_OCCURRENCES = "SELECT COUNT(*) FROM `contact` WHERE `contact_owner` = ? "
			+ "AND `mobile` = ?";
	
}
