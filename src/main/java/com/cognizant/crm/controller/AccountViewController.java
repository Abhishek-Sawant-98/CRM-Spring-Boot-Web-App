package com.cognizant.crm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cognizant.crm.model.User;
import com.cognizant.crm.service.AccountService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // Generates constructor for final fields at compile time
@Controller
@RequestMapping("/CRM") // Base url of crm website
public class AccountViewController {

	// Using final fields and a constructor is generally considered best practise
	// for autowiring dependencies
	private final AccountService accountService;

	// Accessing the account view page
	@GetMapping("/all-accounts")
	private String getAccountViewPage(HttpSession session, ModelMap map) {

		// To prevent unauthorised access to account-view page without logging in
		if (session.getAttribute("user") == null) {
			return "redirect:/CRM/login";
		}

		// Adding 'accountList' attribute to be accessible in accountViewPage
		map.addAttribute("accountList", accountService.getAccountsByUserId((User) session.getAttribute("userObj")));

		return "view-all-pages/accountViewPage";
	}

	// Method to handle all exceptions
	@ExceptionHandler(Exception.class)
	private String getErrorPageOnFailure() {
		return "errorPage";
	}

}