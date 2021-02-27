package com.crm.repository;

import java.util.List;

import com.crm.model.Event;
import com.crm.model.User;

public interface EventDao {

	public boolean save(Event event, User user);
	
	public boolean isEventRegistered(Event event, String userId);
	
	public List<Event> getEventsByUserId(String userId);

}
