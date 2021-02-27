package com.cognizant.crm.repository;

import java.util.List;

import com.cognizant.crm.model.Event;
import com.cognizant.crm.model.Lead;
import com.cognizant.crm.model.User;

public interface LeadDao {

	public boolean addLead(Lead lead, User user);

	public boolean isLeadRegistered(Lead lead);
	
	public List<Lead> getLeadsByUserId(String userId);
	
}
