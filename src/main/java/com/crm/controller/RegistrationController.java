package com.crm.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
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
public class RegistrationController {

	// Using final fields and a constructor is generally considered best practise
	// for autowiring dependencies
	private final UserService userService;

	// Accessing the registration page
	@GetMapping("/registration")
	private String getRegistrationPage(@ModelAttribute("user") User user) {
		return "registrationPage";
	}

	// Validating and registering the user
	@PostMapping("/registration")
	private String registerUser(@Valid @ModelAttribute("user") User user, BindingResult results) {

		userService.validate(user, results);

		if (results.hasErrors()) {
			return "registrationPage";
		}

		if (!userService.signUp(user)) {
			results.rejectValue("isAlreadyRegistered", "error.user.cantRegisterAgain");
			return "registrationPage";
		}

		return "redirect:/CRM/registration/success";
	}

	// Accessing the register success page
	@GetMapping("/registration/success")
	private String getRegistrationSuccessfulPage(@ModelAttribute("user") User user) {
		return "success-pages/userRegisterSuccessPage";
	}

	// Methods for populating the select tag options in registration page
	@ModelAttribute("genderOptions")
	private List<String> populateGenderOptions(
			@Value("#{'${label.user.gender.options}'.split(',')}") List<String> genderOptions) {
		return genderOptions;
	}

	@ModelAttribute("employeeCountOptions")
	private List<String> populateEmployeeCountOptions(
			@Value("#{'${label.user.employeeCount.options}'.split(',')}") List<String> employeeCountOptions) {
		return employeeCountOptions;
	}

	// Method for handling all kinds of exceptions
	@ExceptionHandler(Exception.class)
	private String getErrorPageOnFailure() {
		return "errorPage";
	}

}
