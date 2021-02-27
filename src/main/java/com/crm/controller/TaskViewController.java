package com.crm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crm.model.User;
import com.crm.service.TaskService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // Generates constructor for final fields at compile time
@Controller
@RequestMapping("/CRM") // Base url of crm website
public class TaskViewController {

	// Using final fields and a constructor is generally considered best practise
	// for autowiring dependencies
	private final TaskService taskService;

	// Accessing the Task view page
	@GetMapping("/all-tasks")
	private String getTaskViewPage(HttpSession session, ModelMap map) {

		// To prevent unauthorised access to task-view page without logging in
		if (session.getAttribute("user") == null) {
			return "redirect:/CRM/login";
		}
		
		// Adding 'taskList' attribute to be accessible in taskViewPage
		map.addAttribute("taskList", taskService.getTasksByUserId((User) session.getAttribute("userObj")));	

		return "view-all-pages/taskViewPage";
	}

	// Method to handle all exceptions
	@ExceptionHandler(Exception.class)
	private String getErrorPageOnFailure() {
		return "errorPage";
	}

}