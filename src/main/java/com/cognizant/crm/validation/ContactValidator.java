package com.cognizant.crm.validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.cognizant.crm.model.Contact;

public class ContactValidator implements Validator {

	// Pattern constant
	private static final String DATE_PATTERN = "yyyy-MM-dd";

	@Override
	public boolean supports(Class<?> clazz) {
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		Contact contact = (Contact) target;

		LocalDate today = LocalDate.now();
		LocalDate validDob = today.minusYears(18); // Contact should be 18 or above

		if (!contact.getDob().equals("")) {
			LocalDate userDob = LocalDate.parse(contact.getDob(), DateTimeFormatter.ofPattern(DATE_PATTERN));

			// Contact should be 18 or above
			if (userDob.isAfter(validDob)) {
				errors.rejectValue("dob", "error.dob.underAge");
			}
		} else {
			errors.rejectValue("dob", "error.dob.invalidFormat");
		}

	}

}
