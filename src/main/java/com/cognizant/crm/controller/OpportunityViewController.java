package com.cognizant.crm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cognizant.crm.model.User;
import com.cognizant.crm.service.OpportunityService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // Generates constructor for final fields at compile time
@Controller
@RequestMapping("/CRM") // Base url of crm website
public class OpportunityViewController {

	// Using final fields and a constructor is generally considered best practise
	// for autowiring dependencies
	private final OpportunityService opportunityService;

	// Accessing the opportunity view page
	@GetMapping("/all-opportunities")
	private String getOpportunityViewPage(HttpSession session, ModelMap map) {

		// To prevent unauthorised access to opportunity-view page without logging in
		if (session.getAttribute("user") == null) {
			return "redirect:/CRM/login";
		}
		
		// Adding 'opportunityList' attribute to be accessible in opportunityViewPage
		map.addAttribute("opportunityList", opportunityService.getOpportunitiesByUserId((User) session.getAttribute("userObj")));	

		return "view-all-pages/opportunityViewPage";
	}

	// Method to handle all exceptions
	@ExceptionHandler(Exception.class)
	private String getErrorPageOnFailure() {
		return "errorPage";
	}

}