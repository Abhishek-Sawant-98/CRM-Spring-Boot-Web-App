package com.cognizant.crm.service;

import com.cognizant.crm.model.User;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cognizant.crm.model.Account;
import com.cognizant.crm.repository.AccountDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // Generates constructor for final fields at compile time
@Service
public class AccountServiceImpl implements AccountService {

	// Using final fields and a constructor is generally considered best practise
	// for autowiring dependencies
	private final AccountDao accountDaoImpl;

	@Override
	public boolean addAccount(Account account, User user) {
		return accountDaoImpl.addAccount(account, user);
	}

	@Override
	public List<Account> getAccountsByUserId(User user) {
		return accountDaoImpl.getAccountsByUserId(user.getUserId());
	}

	@Override
	public boolean isAccountRegistered(String accountName, User user) {
		return accountDaoImpl.isAccountRegistered(accountName, user.getUserId());
	}

}
