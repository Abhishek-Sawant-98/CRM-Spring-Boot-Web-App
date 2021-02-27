package com.crm.validation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.crm.model.Event;

public class EventValidator implements Validator {

	// Pattern constant
	private static final String DATE_PATTERN = "yyyy-MM-dd";

	@Override
	public boolean supports(Class<?> clazz) {
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		Event event = (Event) target;

		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);

		if (!event.getDate().equals("")) {
			LocalDate userEventDate = LocalDate.parse(event.getDate(), formatter);

			// Event date should be on or after 'today'
			if (userEventDate.isBefore(today)) {
				errors.rejectValue("date", "error.eventDate.isBeforeToday");
			}

		} else {
			errors.rejectValue("date", "error.eventDate.invalidFormat");
		}

		// Start time and end time are mandatory fields
		if (event.getStartTime().equals("")) {
			errors.rejectValue("startTime", "error.startTime.blank");
		}

		if (event.getEndTime().equals("")) {
			errors.rejectValue("endTime", "error.endTime.blank");
		}

		if (!event.getEndTime().equals("") && !event.getStartTime().equals("")) {

			LocalTime start = LocalTime.parse(event.getStartTime());
			LocalTime end = LocalTime.parse(event.getEndTime());

			// Start time should be before end time
			if (start.isAfter(end)) {
				errors.rejectValue("startTime", "error.startAfterEndTime");
			}
		}

	}

}
