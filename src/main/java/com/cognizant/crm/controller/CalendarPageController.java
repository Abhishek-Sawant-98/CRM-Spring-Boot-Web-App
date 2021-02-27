package com.cognizant.crm.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cognizant.crm.model.Event;
import com.cognizant.crm.model.User;
import com.cognizant.crm.service.EventService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // Generates constructor for final fields at compile time
@Controller
@RequestMapping("/CRM") // Base url of crm website
public class CalendarPageController {

	// Using final fields and a constructor is generally considered best practise
	// for autowiring dependencies
	private final EventService eventService;

	// Accessing the calendar page
	@GetMapping("/calendar-page")
	private String getCalendarPage(@ModelAttribute("event") Event event, HttpSession session) {

		// To prevent unauthorised access to calendar page without logging in
		if (session.getAttribute("user") == null) {
			return "redirect:/CRM/login";
		}

		return "calendarPage";
	}

	// New Event (custom-validation)
	@PostMapping("/add-event")
	private String addNewEvent(@Valid @ModelAttribute("event") Event event, BindingResult results, HttpSession session) {

		// Custom validation of event through 'EventValidator.java'
		eventService.validate(event, results);

		if (results.hasErrors()) {
			return "calendarPage";
		}

		if (!eventService.saveEvent(event, (User) session.getAttribute("userObj"))) {
			results.rejectValue("isAlreadyRegistered", "error.event.cantRegisterAgain");
			return "calendarPage";
		}

		return "success-page/addEventSuccessPage";
	}

	// ModelAttribute required for 'add event' form
	@ModelAttribute("event")
	private Event getEventModelAttribute() {
		return new Event();
	}

	// Method to handle all exceptions
	@ExceptionHandler(Exception.class)
	private String getErrorPageOnFailure() {
		return "errorPage";
	}

}