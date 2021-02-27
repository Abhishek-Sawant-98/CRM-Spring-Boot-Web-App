package com.cognizant.crm.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CountOrRevenueValidator implements ConstraintValidator<CountOrRevenuePattern, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value.matches("([1-9]\\d*)?$");
	}
}
