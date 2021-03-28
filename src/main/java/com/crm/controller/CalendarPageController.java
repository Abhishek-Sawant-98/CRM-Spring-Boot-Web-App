package com.crm.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crm.model.Event;
import com.crm.model.User;
import com.crm.service.AccountService;
import com.crm.service.ContactService;
import com.crm.service.EventService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // Generates constructor for final fields at compile time
@Controller
@RequestMapping("/CRM") // Base url of crm website
@PropertySource("classpath:messages.properties") // To scan the option labels from the 'messages.properties' file
public class CalendarPageController {

	// Using final fields and a constructor is generally considered best practise
	// for autowiring dependencies
	private final EventService eventService;
	private final ContactService contactService;
	private final AccountService accountService;

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
	@RequestMapping(value = "/add-event", method = { RequestMethod.GET, RequestMethod.POST })
	private String addNewEvent(@Valid @ModelAttribute("event") Event event, BindingResult results, HttpSession session,
			@Value("${error.alert.event.invalid}") String invalidEventMsg,
			@Value("${error.alert.event.alreadyExists}") String eventAlreadyExistsMsg) {

		User userSessionObj = (User) session.getAttribute("userObj");

		// Custom validation of event through 'EventValidator.java'
		eventService.validate(event, results);

		if (results.hasErrors()) {
			session.setAttribute("errorMessage", invalidEventMsg);
			return "calendarPage";
		}

		boolean notExistsFlag = false;

		// Checking if account exists in DB or not
		if (!accountService.isAccountRegistered(event.getRelatedTo(), userSessionObj)) {
			results.rejectValue("relatedTo", "error.account.notExists");
			notExistsFlag = true;
		}

		// Checking if contact exists in DB or not
		if (!contactService.isContactRegistered(event.getAssignedTo(), userSessionObj)) {
			results.rejectValue("assignedTo", "error.contact.notExists");
			notExistsFlag = true;
		}

		// If either or both account and contact do not exists in DB, display alert
		if (notExistsFlag) {
			session.setAttribute("errorMessage", invalidEventMsg);
			return "calendarPage";
		}

		if (!eventService.saveEvent(event, userSessionObj)) {
			results.rejectValue("isAlreadyRegistered", "error.event.cantRegisterAgain");
			session.setAttribute("errorMessage", eventAlreadyExistsMsg);
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