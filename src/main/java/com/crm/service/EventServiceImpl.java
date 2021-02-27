package com.crm.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.crm.model.Event;
import com.crm.model.User;
import com.crm.repository.EventDao;
import com.crm.validation.EventValidator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // Generates constructor for final fields at compile time
@Service
public class EventServiceImpl implements EventService {

	// Using final fields and a constructor is generally considered best practise
	// for autowiring dependencies
	private final EventDao eventDaoImpl;

	@Override
	public boolean saveEvent(Event event, User user) {
		return eventDaoImpl.save(event, user);
	}

	@Override
	public List<Event> getEventsByUserId(User user) {
		return eventDaoImpl.getEventsByUserId(user.getUserId());
	}

	@Override
	public void validate(Event event, BindingResult results) {
		new EventValidator().validate(event, results);
	}
}
