package com.cognizant.crm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cognizant.crm.model.User;
import com.cognizant.crm.service.EventService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // Generates constructor for final fields at compile time
@Controller
@RequestMapping("/CRM") // Base url of crm website
public class EventViewController {

	// Using final fields and a constructor is generally considered best practise
	// for autowiring dependencies
	private final EventService eventService;

	// Accessing the Event view page
	@GetMapping("/all-events")
	private String getEventViewPage(HttpSession session, ModelMap map) {

		// To prevent unauthorised access to event-view page without logging in
		if (session.getAttribute("user") == null) {
			return "redirect:/CRM/login";
		}
		
		// Adding 'eventList' attribute to be accessible in eventViewPage
		map.addAttribute("eventList", eventService.getEventsByUserId((User) session.getAttribute("userObj")));	

		return "view-all-pages/eventViewPage";
	}

	// Method to handle all exceptions
	@ExceptionHandler(Exception.class)
	private String getErrorPageOnFailure() {
		return "errorPage";
	}

}