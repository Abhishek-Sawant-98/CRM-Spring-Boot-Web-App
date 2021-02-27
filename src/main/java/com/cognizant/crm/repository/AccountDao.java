package com.cognizant.crm.repository;

import java.util.List;

import com.cognizant.crm.model.Account;
import com.cognizant.crm.model.User;

public interface AccountDao {

	public boolean addAccount(Account account, User user);

	public boolean isAccountRegistered(String accountName, String accountOwner);
	
	public List<Account> getAccountsByUserId(String userId);
	
}
