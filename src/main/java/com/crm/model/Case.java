package com.crm.model;

import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Component;

import com.crm.annotation.AlnumPattern;
import com.crm.annotation.EmailPattern;
import com.crm.annotation.PhonePattern;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter // To generate getters at compile time
@Setter // To generate setters at compile time
public class Case {

	private int idCase; // Primary key
	private int idContact; // Contact foreign key
	private int idAccount; // Account foreign key

	private String caseOwner; // user name
	private String contactName; // Contact name
	private String status; // dropdown
	private String type; // dropdown
	private String caseOrigin; // dropdown
	private String caseReason; // dropdown
	private String priority; // dropdown
	private Boolean isAlreadyRegistered;
	
	@NotBlank(message = "{error.mobile.blank}")
	@PhonePattern
	private String contactMobile; 

	@NotBlank(message = "{error.account.name.blank}")
	@AlnumPattern(message = "{error.account.name.invalidFormat}")
	private String accountName; 

	@NotBlank(message = "{error.email.blank}")
	@EmailPattern
	private String emailId;

	@NotBlank(message = "{error.subject.blank}")
	@AlnumPattern(message = "{error.subject.invalidFormat}")
	private String subject;

	@NotBlank(message = "{error.description.blank}")
	@AlnumPattern
	private String description;

	@NotBlank(message = "{error.comments.blank}")
	@AlnumPattern(message = "{error.comments.invalidFormat}")
	private String internalComments;
}
