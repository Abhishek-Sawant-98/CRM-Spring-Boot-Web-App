package com.crm.queryconstants;

public class AccountConstants {

	// Inserting an account in 'account' table
	public static final String INSERT_ACCOUNT = "INSERT INTO `account` (`account_owner`, `account_name`, `phone`, `fax`, `website`, "
			+ "`type`, `employee_count`, `annual_revenue`, `industry`, `description`) values(?,?,?,?,?,?,?,?,?,?);";

	// Inserting the account billing address in 'address' table
	public static final String INSERT_ACCOUNT_BILLING_ADDRESS = "INSERT INTO `address` (`street`, `city`, `state_province`, "
			+ "`zip_postal`, `country`, `idAccount`, `type`) values(?,?,?,?,?,?,'Billing')";

	// Inserting the account shipping address in 'address' table
	public static final String INSERT_ACCOUNT_SHIPPING_ADDRESS = "INSERT INTO `address` (`street`, `city`, `state_province`, "
			+ "`zip_postal`, `country`, `idAccount`, `type`) values(?,?,?,?,?,?,'Shipping')";

	// Fetching accounts for a particular user_id
	public static final String FETCH_ACCOUNTS = "SELECT a.account_name AS Account_Name, a.phone, a.fax, a.website, a.type, "
			+ "CONCAT(`street`,', ', `city`,', ', `state_province`,', ', `zip_postal`,', ', ad.country) AS Billing_Address, "
			+ "CONCAT(`street`,', ', `city`,', ', `state_province`,', ', `zip_postal`,', ', ad.country) AS Shipping_Address, "
			+ "a.employee_count, a.annual_revenue, a.industry FROM `account` a, `address` ad WHERE a.idAccount = ad.idAccount AND "
			+ "a.idAccount IN (SELECT `idAccount` from `account` WHERE `account_owner` = ?);";

	// Retrieving the account id
	public static final String ACCOUNT_KEY = "SELECT `idAccount` FROM `account` WHERE `account_name` = ? and `account_owner` = ?;";

	// Finding the no. of occurrences of an account in 'account' table
	public static final String FIND_ACCOUNT_OCCURRENCES = "SELECT COUNT(*) FROM `account` WHERE "
			+ "`account_name` = ? AND `account_owner` = ?;";
}
