package com.cognizant.crm.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ZipcodeValidator implements ConstraintValidator<ZipcodePattern, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value.matches("^(^\\d{5}$|^\\d{5}-\\d{4}$|^\\d{6}$)?$");
	}
}