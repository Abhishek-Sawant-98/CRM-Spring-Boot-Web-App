package com.crm.service;

import java.util.List;

import org.springframework.validation.BindingResult;

import com.crm.model.Opportunity;
import com.crm.model.User;

public interface OpportunityService {

	public boolean addOpportunity(Opportunity opportunity, User user);

	public void validate(Opportunity opportunity, BindingResult results);

	public List<Opportunity> getOpportunitiesByUserId(User user);

}
