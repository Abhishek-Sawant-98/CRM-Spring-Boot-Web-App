package com.crm.repository;

import java.util.List;

import com.crm.model.Account;
import com.crm.model.Case;
import com.crm.model.User;

public interface CaseDao {

	public boolean addCase(Case caseObj, User user);

	public boolean isCaseRegistered(Case caseObj);

	public List<Case> getCasesByUserId(String userId);

}
