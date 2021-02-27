package com.cognizant.crm.service;

import java.util.List;

import org.springframework.validation.BindingResult;

import com.cognizant.crm.model.Opportunity;
import com.cognizant.crm.model.User;

public interface OpportunityService {

	public boolean addOpportunity(Opportunity opportunity, User user);

	public void validate(Opportunity opportunity, BindingResult results);

	public List<Opportunity> getOpportunitiesByUserId(User user);

}
