package com.cognizant.crm.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cognizant.crm.model.Event;
import com.cognizant.crm.model.User;
import com.cognizant.crm.queryconstants.AccountConstants;
import com.cognizant.crm.queryconstants.ContactConstants;
import com.cognizant.crm.queryconstants.EventConstants;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // Generates constructor for final fields at compile time
@Repository
public class EventDaoImpl implements EventDao {

	// Using final fields and a constructor is generally considered best practise
	// for autowiring dependencies
	private final JdbcTemplate jdbcTemplate;

	@Override
	public boolean save(Event event, User user) {
		String insertQuery = EventConstants.INSERT_EVENT;
		String accountKeyQuery = AccountConstants.ACCOUNT_KEY;
		String contactKeyQuery = ContactConstants.CONTACT_KEY;

		event.setIdContact(jdbcTemplate.queryForObject(contactKeyQuery,
				new Object[] { event.getAssignedTo(), user.getUserId() }, Integer.class));
		event.setIdAccount(jdbcTemplate.queryForObject(accountKeyQuery,
				new Object[] { event.getRelatedTo(), user.getUserId() }, Integer.class));

		if (!isEventRegistered(event, user.getUserId())) {
			jdbcTemplate.update(insertQuery, event.getIdContact(), event.getIdAccount(), event.getSubject(),
					event.getName(), event.getDate(), event.getStartTime(), event.getEndTime(), event.getLocation(),
					event.getDescription());

			return true;
		}
		return false;
	}

	@Override
	public List<Event> getEventsByUserId(String userId) {
		String eventListQuery = EventConstants.FETCH_EVENTS;

		return jdbcTemplate.query(eventListQuery, new Object[] { userId }, rs -> {
			List<Event> eventList = new ArrayList<>();
			while (rs.next()) {
				Event event = new Event();
				event.setAssignedTo(rs.getString("AssignedTo"));
				event.setRelatedTo(rs.getString("RelatedTo"));
				event.setName(rs.getString("name"));
				event.setSubject(rs.getString("subject"));
				event.setDate(rs.getString("date"));
				event.setStartTime(rs.getString("start"));
				event.setEndTime(rs.getString("end"));
				event.setLocation(rs.getString("location"));
				event.setDescription(rs.getString("description"));
				eventList.add(event);
			}
			return eventList;
		});
	}

	@Override
	public boolean isEventRegistered(Event event, String userId) {
		String eventOccurrencesQuery = EventConstants.EVENT_OCCURRENCES;

		return jdbcTemplate.queryForObject(eventOccurrencesQuery,
				new Object[] { event.getName(), event.getSubject(), userId, userId }, Integer.class) == 1;
	}

}
