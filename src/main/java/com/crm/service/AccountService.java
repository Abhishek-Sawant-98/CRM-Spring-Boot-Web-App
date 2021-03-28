package com.crm.service;

import java.util.List;

import com.crm.model.Account;
import com.crm.model.User;

public interface AccountService {

	public boolean addAccount(Account account, User user);
	
	public boolean isAccountRegistered(String accountName, User user);
	
	public List<Account> getAccountsByUserId(User user);
	
}
