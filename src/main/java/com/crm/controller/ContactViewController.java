package com.crm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crm.model.User;
import com.crm.service.ContactService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // Generates constructor for final fields at compile time
@Controller
@RequestMapping("/CRM") // Base url of crm website
public class ContactViewController {

	// Using final fields and a constructor is generally considered best practise
	// for autowiring dependencies
	private final ContactService contactService;

	// Accessing the Contact view page
	@GetMapping("/all-contacts")
	private String getContactViewPage(HttpSession session, ModelMap map) {

		// To prevent unauthorised access to contact-view page without logging in
		if (session.getAttribute("user") == null) {
			return "redirect:/CRM/login";
		}
		
		// Adding 'contactList' attribute to be accessible in contactViewPage
		map.addAttribute("contactList", contactService.getContactsByUserId((User) session.getAttribute("userObj")));	

		return "view-all-pages/contactViewPage";
	}

	// Method to handle all exceptions
	@ExceptionHandler(Exception.class)
	private String getErrorPageOnFailure() {
		return "errorPage";
	}

}