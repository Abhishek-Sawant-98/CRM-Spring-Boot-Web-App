package com.crm.validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.crm.model.Task;

public class TaskValidator implements Validator {

	// Pattern constant
	private static final String DATE_PATTERN = "yyyy-MM-dd";

	@Override
	public boolean supports(Class<?> clazz) {
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		Task task = (Task) target;

		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);

		if (!task.getDueDate().equals("")) {
			LocalDate userDueDate = LocalDate.parse(task.getDueDate(), formatter);

			// Due date should be on or after 'today'
			if (userDueDate.isBefore(today)) {
				errors.rejectValue("dueDate", "error.dueDate.isBeforeToday");
			}

		} else {
			errors.rejectValue("dueDate", "error.dueDate.invalidFormat");
		}

		if (!task.getReminderDate().equals("")) {
			LocalDate userReminderDate = LocalDate.parse(task.getReminderDate(), formatter);

			// Reminder date should be on or after 'today'
			if (userReminderDate.isBefore(today)) {
				errors.rejectValue("reminderDate", "error.reminderDate.isBeforeToday");
			}

		} else {
			errors.rejectValue("reminderDate", "error.reminderDate.invalidFormat");
		}

		// Reminder time is a mandatory field
		if (task.getReminderTime().equals("")) {
			errors.rejectValue("reminderTime", "error.reminderTime.blank");
		}

	}

}
