package com.cognizant.crm.model;

import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Component;

import com.cognizant.crm.annotation.CountOrRevenuePattern;
import com.cognizant.crm.annotation.AlnumPattern;
import com.cognizant.crm.annotation.ProbabilityPattern;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter // To generate getters at compile time
@Setter // To generate setters at compile time
public class Opportunity {
	
	private int idOpportunity; // Primary key
	private int idAccount; // Account foreign key

	private String opportunityOwner; // user name
	private String stage; // dropdown
	private String type; // dropdown
	private String leadSource; // dropdown
	private String closeDate;
	private Boolean isAlreadyRegistered;

	@NotBlank(message = "{error.opportunity.name.blank}")
	@AlnumPattern(message = "{error.opportunity.name.invalidFormat}")
	private String opportunityName;

	@NotBlank(message = "{error.account.name.blank}")
	@AlnumPattern(message = "{error.account.name.invalidFormat}")
	private String accountName;

	@NotBlank(message = "{error.probability.blank}")
	@ProbabilityPattern
	private String probability;

	@NotBlank(message = "{error.amount.blank}")
	@CountOrRevenuePattern(message = "{error.amount.invalidFormat}")
	private String amount;

	@NotBlank(message = "{error.description.blank}")
	@AlnumPattern
	private String description;

	@NotBlank(message = "{error.nextStep.blank}")
	@AlnumPattern(message = "{error.nextStep.invalidFormat}")
	private String nextStep;
}
