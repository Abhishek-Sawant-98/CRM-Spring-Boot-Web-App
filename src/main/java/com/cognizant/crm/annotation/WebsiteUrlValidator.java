package com.cognizant.crm.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class WebsiteUrlValidator implements ConstraintValidator<UrlPattern, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value.matches("^((https?:\\/\\/)?(www\\.)?(\\w+)\\.([a-z]{2}\\.)?[a-z]{2,3}(\\/.*)*)?$");
	}
}