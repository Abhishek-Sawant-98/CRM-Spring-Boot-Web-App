package com.cognizant.crm.service;

import com.cognizant.crm.model.User;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.cognizant.crm.model.Contact;
import com.cognizant.crm.repository.ContactDao;
import com.cognizant.crm.validation.ContactValidator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // Generates constructor for final fields at compile time
@Service
public class ContactServiceImpl implements ContactService {

	// Using final fields and a constructor is generally considered best practise
	// for autowiring dependencies
	private final ContactDao contactDaoImpl;

	@Override
	public boolean addContact(Contact contact, User user) {
		return contactDaoImpl.addContact(contact, user);
	}

	@Override
	public List<Contact> getContactsByUserId(User user) {
		return contactDaoImpl.getContactsByUserId(user.getUserId());
	}

	@Override
	public void validate(Contact contact, BindingResult results) {
		new ContactValidator().validate(contact, results);
	}
}
