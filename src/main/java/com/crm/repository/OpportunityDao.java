package com.crm.repository;

import java.util.List;

import com.crm.model.Lead;
import com.crm.model.Opportunity;
import com.crm.model.User;

public interface OpportunityDao {

	public boolean addOpportunity(Opportunity opportunity, User user);

	public boolean isOpportunityRegistered(Opportunity opportunity);
	
	public List<Opportunity> getOpportunitiesByUserId(String userId);

}
