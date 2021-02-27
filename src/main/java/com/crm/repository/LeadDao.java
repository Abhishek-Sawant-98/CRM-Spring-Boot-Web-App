package com.crm.repository;

import java.util.List;

import com.crm.model.Lead;
import com.crm.model.User;

public interface LeadDao {

	public boolean addLead(Lead lead, User user);

	public boolean isLeadRegistered(Lead lead);
	
	public List<Lead> getLeadsByUserId(String userId);
	
}
