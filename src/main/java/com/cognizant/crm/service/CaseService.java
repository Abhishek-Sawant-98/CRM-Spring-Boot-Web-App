package com.cognizant.crm.service;

import java.util.List;

import com.cognizant.crm.model.Case;
import com.cognizant.crm.model.User;

public interface CaseService {

	public boolean addCase(Case caseObj, User user);
	
	public List<Case> getCasesByUserId(User user);

}
