package com.cognizant.crm.repository;

import java.util.List;

import com.cognizant.crm.model.Event;
import com.cognizant.crm.model.User;

public interface EventDao {

	public boolean save(Event event, User user);
	
	public boolean isEventRegistered(Event event, String userId);
	
	public List<Event> getEventsByUserId(String userId);

}
