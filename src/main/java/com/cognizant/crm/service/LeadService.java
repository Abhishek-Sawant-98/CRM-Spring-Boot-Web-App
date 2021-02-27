package com.cognizant.crm.service;

import java.util.List;

import com.cognizant.crm.model.Lead;
import com.cognizant.crm.model.User;

public interface LeadService {

	public boolean addLead(Lead lead, User user);

	public List<Lead> getLeadsByUserId(User user);

}
