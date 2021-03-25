package com.crm.model;

import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Component;

import com.crm.annotation.AlnumPattern;
import com.crm.annotation.EmailPattern;
import com.crm.annotation.FaxPattern;
import com.crm.annotation.PhonePattern;
import com.crm.annotation.StreetPattern;
import com.crm.annotation.ZipcodePattern;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter // To generate getters at compile time
@Setter // To generate setters at compile time
public class Contact {

	private int idContact; // Primary key
	private int idAccount; // Account foreign key

	private String contactOwner; // user name
	private String salutation; // dropdown
	private String leadSource; // dropdown
	private String dob;
	private Boolean isAlreadyRegistered;
	private String contactName;
	private String mailingAddress;
	private String otherAddress;

	@NotBlank(message = "{error.firstName.blank}")
	@AlnumPattern(message = "{error.firstName.invalidFormat}")
	private String firstName;

	@NotBlank(message = "{error.lastName.blank}")
	@AlnumPattern(message = "{error.lastName.invalidFormat}")
	private String lastName;

	@NotBlank(message = "{error.mobile.blank}")
	@PhonePattern
	private String mobile;

	@NotBlank(message = "{error.account.name.blank}")
	@AlnumPattern(message = "{error.account.name.invalidFormat}")
	private String accountName; // account name

	@NotBlank(message = "{error.email.blank}")
	@EmailPattern
	private String emailId;

	@NotBlank(message = "{error.title.blank}")
	@AlnumPattern(message = "{error.title.blank}")
	private String title;

	// Mailing Address
	@NotBlank(message = "{error.street.blank}")
	@StreetPattern
	private String mailingStreet;

	@NotBlank(message = "{error.city.blank}")
	@AlnumPattern(message = "{error.city.invalidFormat}")
	private String mailingCity;

	@NotBlank(message = "{error.stateOrProvince.blank}")
	@AlnumPattern(message = "{error.stateOrProvince.invalidFormat}")
	private String mailingStateProvince;

	@NotBlank(message = "{error.zipOrPostalCode.blank}")
	@ZipcodePattern
	private String mailingZipPostal;

	@NotBlank(message = "{error.country.blank}")
	@AlnumPattern(message = "{error.country.invalidFormat}")
	private String mailingCountry;

	// Other Address
	@NotBlank(message = "{error.street.blank}")
	@StreetPattern
	private String otherStreet;

	@NotBlank(message = "{error.city.blank}")
	@AlnumPattern(message = "{error.city.invalidFormat}")
	private String otherCity;

	@NotBlank(message = "{error.stateOrProvince.blank}")
	@AlnumPattern(message = "{error.stateOrProvince.invalidFormat}")
	private String otherStateProvince;

	@NotBlank(message = "{error.zipOrPostalCode.blank}")
	@ZipcodePattern
	private String otherZipPostal;

	@NotBlank(message = "{error.country.blank}")
	@AlnumPattern(message = "{error.country.invalidFormat}")
	private String otherCountry;

	@NotBlank(message = "{error.assistant.blank}")
	@AlnumPattern(message = "{error.assistant.invalidFormat}")
	private String assistant;

	// Additional info
	@NotBlank(message = "{error.country.blank}")
	@PhonePattern
	private String assistantPhone;

	@NotBlank(message = "{error.phone.blank}")
	@PhonePattern
	private String otherPhone;

	@NotBlank(message = "{error.advertisement.blank}")
	@AlnumPattern(message = "{error.advertisement.invalidFormat}")
	private String advertisement;

	@NotBlank(message = "{error.phone.blank}")
	@PhonePattern
	private String homePhone;

	@NotBlank(message = "{error.department.blank}")
	@AlnumPattern(message = "{error.department.invalidFormat}")
	private String department;

	@NotBlank(message = "{error.fax.blank}")
	@FaxPattern
	private String fax;

	@NotBlank(message = "{error.description.blank}")
	@AlnumPattern
	private String description;

}
