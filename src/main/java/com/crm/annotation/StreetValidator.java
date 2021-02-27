package com.crm.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StreetValidator implements ConstraintValidator<StreetPattern, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value.matches("^(.+)?$");
	}
}
