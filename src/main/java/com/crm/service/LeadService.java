package com.crm.service;

import java.util.List;

import com.crm.model.Lead;
import com.crm.model.User;

public interface LeadService {

	public boolean addLead(Lead lead, User user);

	public List<Lead> getLeadsByUserId(User user);

}
