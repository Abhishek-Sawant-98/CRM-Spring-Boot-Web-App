package com.cognizant.crm.model;

import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Component;

import com.cognizant.crm.annotation.CountOrRevenuePattern;
import com.cognizant.crm.annotation.AlnumPattern;
import com.cognizant.crm.annotation.EmailPattern;
import com.cognizant.crm.annotation.PhonePattern;
import com.cognizant.crm.annotation.StreetPattern;
import com.cognizant.crm.annotation.UrlPattern;
import com.cognizant.crm.annotation.ZipcodePattern;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter // To generate getters at compile time
@Setter // To generate setters at compile time
public class Lead {
	
	private int idLead; // Primary key

	private String leadOwner; // user name
	private String leadStatus; // dropdown
	private String leadSource; // dropdown
	private String industry; // dropdown
	private Boolean isAlreadyRegistered;
	private String name;
	private String address;

	// Name
	private String salutation; // dropdown

	@NotBlank(message = "{error.firstName.blank}")
	@AlnumPattern(message = "{error.firstName.invalidFormat}")
	private String firstName;

	@NotBlank(message = "{error.lastName.blank}")
	@AlnumPattern(message = "{error.lastName.invalidFormat}")
	private String lastName;

	// Address
	@NotBlank(message = "{error.street.blank}")
	@StreetPattern
	private String street;

	@NotBlank(message = "{error.city.blank}")
	@AlnumPattern(message = "{error.city.invalidFormat}")
	private String city;

	@NotBlank(message = "{error.stateOrProvince.blank}")
	@AlnumPattern(message = "{error.stateOrProvince.invalidFormat}")
	private String stateProvince;

	@NotBlank(message = "{error.zipOrPostalCode.blank}")
	@ZipcodePattern
	private String zipPostal;

	@NotBlank(message = "{error.country.blank}")
	@AlnumPattern(message = "{error.country.invalidFormat}")
	private String country;

	// Other fields
	@NotBlank(message = "{error.phone.blank}")
	@PhonePattern
	private String phone;

	@NotBlank(message = "{error.company.blank}")
	@AlnumPattern(message = "{error.company.invalidFormat}")
	private String company;

	@NotBlank(message = "{error.email.blank}")
	@EmailPattern
	private String emailId;

	@NotBlank(message = "{error.website.blank}")
	@UrlPattern
	private String website;

	@NotBlank(message = "{error.employeeCount.blank}")
	@CountOrRevenuePattern(message = "{error.employeeCount.invalidFormat}")
	private String employeeCount;

	@NotBlank(message = "{error.revenue.blank}")
	@CountOrRevenuePattern
	private String annualRevenue;

	@NotBlank(message = "{error.description.blank}")
	@AlnumPattern
	private String description;
}
