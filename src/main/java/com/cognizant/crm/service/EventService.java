package com.cognizant.crm.service;

import java.util.List;

import org.springframework.validation.BindingResult;

import com.cognizant.crm.model.Event;
import com.cognizant.crm.model.User;

public interface EventService {

	public boolean saveEvent(Event event, User user);
	
	public List<Event> getEventsByUserId(User user);

	public void validate(Event event, BindingResult results);
}
