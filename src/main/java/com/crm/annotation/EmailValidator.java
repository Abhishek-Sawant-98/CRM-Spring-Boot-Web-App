package com.crm.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<EmailPattern, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value.matches("(^[a-z0-9\\._%\\+-]+@[a-z0-9\\.-]+\\.[a-z]{2,6})?$");
	}
}
