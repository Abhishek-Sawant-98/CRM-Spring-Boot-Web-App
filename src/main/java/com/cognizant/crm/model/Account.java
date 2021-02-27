package com.cognizant.crm.model;

import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Component;

import com.cognizant.crm.annotation.CountOrRevenuePattern;
import com.cognizant.crm.annotation.AlnumPattern;
import com.cognizant.crm.annotation.FaxPattern;
import com.cognizant.crm.annotation.PhonePattern;
import com.cognizant.crm.annotation.StreetPattern;
import com.cognizant.crm.annotation.UrlPattern;
import com.cognizant.crm.annotation.ZipcodePattern;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter // To generate getters at compile time
@Setter // To generate setters at compile time
public class Account {
	
	private int idAccount; // Primary key

	private String accountOwner; // user name
	private String type; // dropdown
	private String industry; // dropdown
	private Boolean isAlreadyRegistered;
	private String billingAddress;
	private String shippingAddress;

	// Billing Address
	@NotBlank(message = "{error.street.blank}")
	@StreetPattern
	private String billingStreet;

	@NotBlank(message = "{error.city.blank}")
	@AlnumPattern(message = "{error.city.invalidFormat}")
	private String billingCity;

	@NotBlank(message = "{error.stateOrProvince.blank}")
	@AlnumPattern(message = "{error.stateOrProvince.invalidFormat}")
	private String billingStateProvince;

	@NotBlank(message = "{error.zipOrPostalCode.blank}")
	@ZipcodePattern
	private String billingZipPostal;

	@NotBlank(message = "{error.country.blank}")
	@AlnumPattern(message = "{error.country.invalidFormat}")
	private String billingCountry;

	// Shipping Address
	@NotBlank(message = "{error.street.blank}")
	@StreetPattern
	private String shippingStreet;

	@NotBlank(message = "{error.city.blank}")
	@AlnumPattern(message = "{error.city.invalidFormat}")
	private String shippingCity;

	@NotBlank(message = "{error.stateOrProvince.blank}")
	@AlnumPattern(message = "{error.stateOrProvince.invalidFormat}")
	private String shippingStateProvince;

	@NotBlank(message = "{error.zipOrPostalCode.blank}")
	@ZipcodePattern
	private String shippingZipPostal;

	@NotBlank(message = "{error.country.blank}")
	@AlnumPattern(message = "{error.country.invalidFormat}")
	private String shippingCountry;

	// Other fields
	@NotBlank(message = "{error.account.name.blank}")
	@AlnumPattern(message = "{error.account.name.invalidFormat}")
	private String accountName; // account name

	@NotBlank(message = "{error.phone.blank}")
	@PhonePattern
	private String phone;

	@NotBlank(message = "{error.fax.blank}")
	@FaxPattern
	private String fax;

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
