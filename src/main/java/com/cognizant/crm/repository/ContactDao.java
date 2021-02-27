package com.cognizant.crm.repository;

import java.util.List;

import com.cognizant.crm.model.Case;
import com.cognizant.crm.model.Contact;
import com.cognizant.crm.model.User;

public interface ContactDao {

	public boolean addContact(Contact contact, User user);

	public boolean isContactRegistered(Contact contact);
	
	public List<Contact> getContactsByUserId(String userId);
	
}
