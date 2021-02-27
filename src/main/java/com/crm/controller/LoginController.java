package com.crm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crm.model.User;
import com.crm.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // Generates constructor for final fields at compile time
@Controller
@RequestMapping("/CRM") // Base url of crm website
public class LoginController {

	// Using final fields and a constructor is generally considered best practise
	// for autowiring dependencies
	private final UserService userService;

	// Accessing the login page
	@GetMapping("/login")
	private String getLoginPage(@ModelAttribute("user") User user) {
		return "loginPage";
	}

	// Authenticating and logging in
	@PostMapping("/login")
	private String validateUser(@ModelAttribute("user") User user, BindingResult results, HttpSession session) {

		// Returning back to login page if user credentials aren't present in the database
		if (!userService.isUserAuthentic(user)) {
			results.rejectValue("isAuthentic", "error.user.invalidCredentials");
			return "loginPage";
		}
		
		// Adding 'user' object into the session
		session.setAttribute("user", user);

		return "redirect:/CRM/dashboard";
	}

	// Method to handle all exceptions
	@ExceptionHandler(Exception.class)
	private String getErrorPageOnFailure() {
		return "errorPage";
	}

}
