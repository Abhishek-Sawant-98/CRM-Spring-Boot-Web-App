package com.cognizant.crm.repository;

import java.util.List;

import com.cognizant.crm.model.Account;
import com.cognizant.crm.model.Case;
import com.cognizant.crm.model.User;

public interface CaseDao {

	public boolean addCase(Case caseObj, User user);

	public boolean isCaseRegistered(Case caseObj);

	public List<Case> getCasesByUserId(String userId);

}
