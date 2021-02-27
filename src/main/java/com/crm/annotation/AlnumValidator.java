package com.crm.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AlnumValidator implements ConstraintValidator<AlnumPattern, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value.matches("^([\\w ]+)?$");
	}
}
