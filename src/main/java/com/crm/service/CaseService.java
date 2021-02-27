package com.crm.service;

import java.util.List;

import com.crm.model.Case;
import com.crm.model.User;

public interface CaseService {

	public boolean addCase(Case caseObj, User user);
	
	public List<Case> getCasesByUserId(User user);

}
