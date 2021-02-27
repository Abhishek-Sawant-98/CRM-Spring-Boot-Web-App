package com.cognizant.crm.validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.cognizant.crm.model.Opportunity;

public class OpportunityValidator implements Validator {

	// Pattern constant
	private static final String DATE_PATTERN = "yyyy-MM-dd";

	@Override
	public boolean supports(Class<?> clazz) {
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		Opportunity opportunity = (Opportunity) target;

		LocalDate today = LocalDate.now();

		if (!opportunity.getCloseDate().equals("")) {
			LocalDate userCloseDate = LocalDate.parse(opportunity.getCloseDate(),
					DateTimeFormatter.ofPattern(DATE_PATTERN));

			// Close date should be on or after 'today'
			if (userCloseDate.isBefore(today)) {
				errors.rejectValue("closeDate", "error.closeDate.isBeforeToday");
			}

		} else {
			errors.rejectValue("closeDate", "error.closeDate.invalidFormat");
		}

	}

}
