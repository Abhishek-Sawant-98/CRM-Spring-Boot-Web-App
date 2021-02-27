package com.crm.service;

import java.util.List;

import org.springframework.validation.BindingResult;

import com.crm.model.Event;
import com.crm.model.User;

public interface EventService {

	public boolean saveEvent(Event event, User user);
	
	public List<Event> getEventsByUserId(User user);

	public void validate(Event event, BindingResult results);
}
