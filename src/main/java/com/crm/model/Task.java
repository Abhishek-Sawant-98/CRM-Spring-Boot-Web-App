package com.crm.model;

import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Component;

import com.crm.annotation.AlnumPattern;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter // To generate getters at compile time
@Setter // To generate setters at compile time
public class Task {

	private int idTask; // Primary key
	private int idContact; // Contact foreign key
	private int idAccount; // Account foreign key

	private String status; // dropdown
	private String priority; // dropdown
	private String dueDate;
	private String reminderDate;
	private String reminderTime;
	private Boolean isAlreadyRegistered;

	@NotBlank(message = "{error.phone.blank}")
	@AlnumPattern(message = "{error.phone.invalidFormat}")
	private String assignedTo; // contact mobile

	@NotBlank(message = "{error.account.name.blank}")
	@AlnumPattern(message = "{error.account.name.invalidFormat}")
	private String relatedTo; // account name

	@NotBlank(message = "{error.task.name.blank}")
	@AlnumPattern(message = "{error.task.name.invalidFormat}")
	private String name; // task name

	@NotBlank(message = "{error.subject.blank}")
	@AlnumPattern(message = "{error.subject.invalidFormat}")
	private String subject;

	@NotBlank(message = "{error.comments.blank}")
	@AlnumPattern(message = "{error.comments.invalidFormat}")
	private String comments;

}
