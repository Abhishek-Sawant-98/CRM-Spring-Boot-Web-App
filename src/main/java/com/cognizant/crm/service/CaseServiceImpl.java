package com.cognizant.crm.service;

import com.cognizant.crm.model.User;

import java.util.List;

import org.springframework.stereotype.Service;
import com.cognizant.crm.model.Case;
import com.cognizant.crm.repository.CaseDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // Generates constructor for final fields at compile time
@Service
public class CaseServiceImpl implements CaseService {

	// Using final fields and a constructor is generally considered best practise
	// for autowiring dependencies
	private final CaseDao caseDaoImpl;

	@Override
	public boolean addCase(Case caseObj, User user) {
		return caseDaoImpl.addCase(caseObj, user);
	}

	@Override
	public List<Case> getCasesByUserId(User user) {
		return caseDaoImpl.getCasesByUserId(user.getUserId());
	}

}
