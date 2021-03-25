package com.crm.model;

import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Component;

import com.crm.annotation.AlnumPattern;
import com.crm.annotation.PhonePattern;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter // To generate getters at compile time
@Setter // To generate setters at compile time
public class Event {
	
	private int idEvent; // Primary key
	private int idContact; // Contact foreign key
	private int idAccount; // Account foreign key
	
	private String date;
	private String startTime;
	private String endTime;
	private Boolean isAlreadyRegistered;

	@NotBlank(message = "{error.mobile.blank}")
	@PhonePattern
	private String assignedTo; // contact mobile

	@NotBlank(message = "{error.account.name.blank}")
	@AlnumPattern(message = "{error.account.name.invalidFormat}")
	private String relatedTo; // account name

	@NotBlank(message = "{error.event.name.blank}")
	@AlnumPattern(message = "{error.event.name.invalidFormat}")
	private String name; // event name

	@NotBlank(message = "{error.subject.blank}")
	@AlnumPattern(message = "{error.subject.invalidFormat}")
	private String subject;

	@NotBlank(message = "{error.location.blank}")
	@AlnumPattern(message = "{error.location.invalidFormat}")
	private String location;

	@NotBlank(message = "{error.description.blank}")
	@AlnumPattern
	private String description;

}
