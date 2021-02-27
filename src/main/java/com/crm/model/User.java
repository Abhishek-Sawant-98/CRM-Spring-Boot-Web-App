package com.crm.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Component
@Getter // To generate getters at compile time
@Setter // To generate setters at compile time
public class User {

	private static final String USER_ID_PATTERN = "^(\\w+)?$";
	private static final String PASSWORD_PATTERN = "^((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&\\+=]).{8,30})?$";

	@NotBlank(message = "{error.id.blank}")
	@Pattern(regexp = USER_ID_PATTERN, message = "{error.id.invalidFormat}")
	private String userId;

	@NotBlank(message = "{error.password.blank}")
	@Pattern(regexp = PASSWORD_PATTERN, message = "{error.password.invalidFormat}")
	private String password;

	private String confirmPassword;
	private String firstName;
	private String lastName;
	private String dob;
	private String gender;
	private String contactNumber;
	private String emailId;
	private String jobTitle;
	private String company;
	private String country;
	private String employeeCount;

	private Boolean isAuthentic;
	private Boolean isAlreadyRegistered;

}
