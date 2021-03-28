package com.crm.service;

import java.util.List;

import org.springframework.validation.BindingResult;

import com.crm.model.Contact;
import com.crm.model.User;

public interface ContactService {

	public boolean addContact(Contact contact, User user);

	public void validate(Contact contact, BindingResult results);

	public List<Contact> getContactsByUserId(User user);
	
	public boolean isContactRegistered(String contactMobile, User user);
	
}
