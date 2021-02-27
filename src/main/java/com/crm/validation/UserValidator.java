package com.crm.validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.crm.model.User;

public class UserValidator implements Validator {

	// Pattern constants
	private static final String NAME_PATTERN = "^([a-zA-Z ]+)?$";
	private static final String EMAIL_PATTERN = "^([\\w\\._%\\+-]+@[\\w]+\\.[a-z]{2,6})?$";
	private static final String DESCRIPTION_PATTERN = "^([\\w ]+)?$";
	private static final String PHONE_PATTERN = "^(\\d{10})?$";
	private static final String DATE_PATTERN = "yyyy-MM-dd";
	private static final String DEFAULT_SELECT_OPTION = "Select...";

	@Override
	public boolean supports(Class<?> clazz) {
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;

		// Rejecting the blank or empty inputs/fields
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error.firstName.blank");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "company", "error.company.blank");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "country", "error.country.blank");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "jobTitle", "error.jobTitle.blank");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailId", "error.email.blank");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contactNumber", "error.phone.blank");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "error.confirmPassword.blank");

		// Rejecting the non-blank fields based on failing to match a specific
		// pattern/condition
		if (!user.getFirstName().matches(NAME_PATTERN)) {
			errors.rejectValue("firstName", "error.firstName.invalidFormat");
		}
		if (!user.getLastName().matches(NAME_PATTERN)) {
			errors.rejectValue("lastName", "error.lastName.invalidFormat");
		}
		if (!user.getEmailId().matches(EMAIL_PATTERN)) {
			errors.rejectValue("emailId", "error.email.invalidFormat");
		}
		if (!user.getJobTitle().matches(DESCRIPTION_PATTERN)) {
			errors.rejectValue("jobTitle", "error.jobTitle.invalidFormat");
		}
		if (user.getGender().equals(DEFAULT_SELECT_OPTION)) {
			errors.rejectValue("gender", "error.gender.blank");
		}
		if (user.getEmployeeCount().equals(DEFAULT_SELECT_OPTION)) {
			errors.rejectValue("employeeCount", "error.employeeCount.blank");
		}
		if (!user.getContactNumber().matches(PHONE_PATTERN)) {
			errors.rejectValue("contactNumber", "error.phone.invalidFormat");
		}
		if (!user.getCompany().matches(DESCRIPTION_PATTERN)) {
			errors.rejectValue("company", "error.company.invalidFormat");
		}
		if (!user.getCountry().matches(DESCRIPTION_PATTERN)) {
			errors.rejectValue("country", "error.country.invalidFormat");
		}

		LocalDate today = LocalDate.now();
		LocalDate validDob = today.minusYears(18); // User should be 18 or above

		if (!user.getDob().equals("")) {
			LocalDate userDob = LocalDate.parse(user.getDob(), DateTimeFormatter.ofPattern(DATE_PATTERN));

			// User should be 18 or above, else reject dob
			if (userDob.isAfter(validDob)) {
				errors.rejectValue("dob", "error.dob.underAge");
			}
		} else {
			errors.rejectValue("dob", "error.dob.invalidFormat");
		}

		// Password and confirm password should be same
		if (!user.getConfirmPassword().equals("") && !user.getConfirmPassword().equals(user.getPassword())) {
			errors.rejectValue("confirmPassword", "error.confirmPassword.mismatch");
		}

	}

}
