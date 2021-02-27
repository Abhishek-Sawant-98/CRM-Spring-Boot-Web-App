package com.cognizant.crm.repository;

import java.util.List;

import com.cognizant.crm.model.Lead;
import com.cognizant.crm.model.Opportunity;
import com.cognizant.crm.model.User;

public interface OpportunityDao {

	public boolean addOpportunity(Opportunity opportunity, User user);

	public boolean isOpportunityRegistered(Opportunity opportunity);
	
	public List<Opportunity> getOpportunitiesByUserId(String userId);

}
