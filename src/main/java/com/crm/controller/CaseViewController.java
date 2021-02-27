package com.crm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crm.model.User;
import com.crm.service.CaseService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // Generates constructor for final fields at compile time
@Controller
@RequestMapping("/CRM") // Base url of crm website
public class CaseViewController {

	// Using final fields and a constructor is generally considered best practise
	// for autowiring dependencies
	private final CaseService caseService;

	// Accessing the case view page
	@GetMapping("/all-cases")
	private String getCaseViewPage(HttpSession session, ModelMap map) {

		// To prevent unauthorised access to case-view page without logging in
		if (session.getAttribute("user") == null) {
			return "redirect:/CRM/login";
		}
		
		// Adding 'caseList' attribute to be accessible in caseViewPage
		map.addAttribute("caseList", caseService.getCasesByUserId((User) session.getAttribute("userObj")));	

		return "view-all-pages/caseViewPage";
	}

	// Method to handle all exceptions
	@ExceptionHandler(Exception.class)
	private String getErrorPageOnFailure() {
		return "errorPage";
	}

}