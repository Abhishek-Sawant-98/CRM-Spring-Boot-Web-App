package com.crm.repository;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.crm.model.Account;
import com.crm.model.User;
import com.crm.queryconstants.AccountConstants;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // Generates constructor for final fields at compile time
@Repository
public class AccountDaoImpl implements AccountDao {

	// Using final fields and a constructor is generally considered best practise
	// for autowiring dependencies
	private final JdbcTemplate jdbcTemplate;

	@Override
	public boolean addAccount(Account account, User user) {
		String insertQuery = AccountConstants.INSERT_ACCOUNT;
		String insertBillingAddressQuery = AccountConstants.INSERT_ACCOUNT_BILLING_ADDRESS;
		String insertShippingAddressQuery = AccountConstants.INSERT_ACCOUNT_SHIPPING_ADDRESS;

		account.setAccountOwner(user.getUserId());

		// If the account isn't registered, insert the account details in database and
		// return true, else return false
		if (!isAccountRegistered(account.getAccountName(), account.getAccountOwner())) {

			// GeneratedKeyHolder is used to capture the specified auto-generated primary
			// key from MySQL upon successful insertion into the specified table
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(con -> {
				PreparedStatement ps = con.prepareStatement(insertQuery, new String[] { "idAccount" });
				ps.setString(1, account.getAccountOwner());
				ps.setString(2, account.getAccountName());
				ps.setString(3, account.getPhone());
				ps.setString(4, account.getFax());
				ps.setString(5, account.getWebsite());
				ps.setString(6, account.getType());
				ps.setString(7, account.getEmployeeCount());
				ps.setString(8, account.getAnnualRevenue());
				ps.setString(9, account.getIndustry());
				ps.setString(10, account.getDescription());
				return ps;
			}, keyHolder);

			// This primary key of the inserted account will be used for inserting its
			// address.
			account.setIdAccount(Objects.requireNonNull(keyHolder.getKey()).intValue());

			// Inserts the Billing address
			jdbcTemplate.update(insertBillingAddressQuery, account.getBillingStreet(), account.getBillingCity(),
					account.getBillingStateProvince(), account.getBillingZipPostal(), account.getBillingCountry(),
					account.getIdAccount());

			// Inserts the Shipping address
			jdbcTemplate.update(insertShippingAddressQuery, account.getShippingStreet(), account.getShippingCity(),
					account.getShippingStateProvince(), account.getShippingZipPostal(), account.getShippingCountry(),
					account.getIdAccount());

			return true;
		}
		return false;
	}

	@Override
	public boolean isAccountRegistered(String accountName, String accountOwner) {
		String accountOccurrencesQuery = AccountConstants.FIND_ACCOUNT_OCCURRENCES;

		return jdbcTemplate.queryForObject(accountOccurrencesQuery,
				new Object[] { accountName, accountOwner }, Integer.class) == 1;
	}

	@Override
	public List<Account> getAccountsByUserId(String userId) {
		String fetchAccountsQuery = AccountConstants.FETCH_ACCOUNTS;

		return jdbcTemplate.query(fetchAccountsQuery, new Object[] { userId }, rs -> {
			List<Account> accountList = new ArrayList<Account>();
			while (rs.next()) {
				Account account = new Account();
				account.setAccountName(rs.getString("Account_Name"));
				account.setPhone(rs.getString("phone"));
				account.setFax(rs.getString("fax"));
				account.setWebsite(rs.getString("website"));
				account.setType(rs.getString("type"));
				account.setBillingAddress(rs.getString("Billing_Address"));
				account.setShippingAddress(rs.getString("Shipping_Address"));
				account.setEmployeeCount(rs.getString("employee_count"));
				account.setAnnualRevenue(rs.getString("annual_revenue"));
				account.setIndustry(rs.getString("industry"));

				accountList.add(account);
			}
			return accountList;
		});
	}

}
