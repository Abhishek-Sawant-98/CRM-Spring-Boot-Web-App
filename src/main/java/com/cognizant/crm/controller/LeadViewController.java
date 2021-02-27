package com.cognizant.crm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cognizant.crm.model.User;
import com.cognizant.crm.service.LeadService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // Generates constructor for final fields at compile time
@Controller
@RequestMapping("/CRM") // Base url of crm website
public class LeadViewController {

	// Using final fields and a constructor is generally considered best practise
	// for autowiring dependencies
	private final LeadService leadService;

	// Accessing the lead view page
	@GetMapping("all-leads")
	private String getLeadViewPage(HttpSession session, ModelMap map) {

		// To prevent unauthorised access to lead-view page without logging in
		if (session.getAttribute("user") == null) {
			return "redirect:/CRM/login";
		}
		
		// Adding 'leadList' attribute to be accessible in leadViewPage
		map.addAttribute("leadList", leadService.getLeadsByUserId((User) session.getAttribute("userObj")));	

		return "view-all-pages/leadViewPage";
	}

	// Method to handle all exceptions
	@ExceptionHandler(Exception.class)
	private String getErrorPageOnFailure() {
		return "errorPage";
	}

}