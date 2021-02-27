package com.cognizant.crm.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FaxValidator implements ConstraintValidator<FaxPattern, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value.matches("^(\\+\\d{1,3}-\\d{3}-\\d{7})?$");
	}
}
