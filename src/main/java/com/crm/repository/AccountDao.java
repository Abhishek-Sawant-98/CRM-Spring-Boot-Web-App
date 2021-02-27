package com.crm.repository;

import java.util.List;

import com.crm.model.Account;
import com.crm.model.User;

public interface AccountDao {

	public boolean addAccount(Account account, User user);

	public boolean isAccountRegistered(String accountName, String accountOwner);
	
	public List<Account> getAccountsByUserId(String userId);
	
}
