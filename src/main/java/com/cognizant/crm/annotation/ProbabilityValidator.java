package com.cognizant.crm.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ProbabilityValidator implements ConstraintValidator<ProbabilityPattern, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value.matches("^(^100$|^(\\d|[1-9]\\d)(\\.\\d+)?$)?$");
	}
}
