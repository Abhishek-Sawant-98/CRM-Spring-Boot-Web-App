package com.crm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.crm.model.Lead;
import com.crm.model.User;
import com.crm.repository.LeadDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // Generates constructor for final fields at compile time
@Service
public class LeadServiceImpl implements LeadService {

	// Using final fields and a constructor is generally considered best practise
	// for autowiring dependencies
	private final LeadDao leadDaoImpl;

	@Override
	public boolean addLead(Lead lead, User user) {
		return leadDaoImpl.addLead(lead, user);
	}

	@Override
	public List<Lead> getLeadsByUserId(User user) {
		return leadDaoImpl.getLeadsByUserId(user.getUserId());
	}
}
