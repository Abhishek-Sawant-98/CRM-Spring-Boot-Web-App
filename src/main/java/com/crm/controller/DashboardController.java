package com.crm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crm.model.Account;
import com.crm.model.Case;
import com.crm.model.Contact;
import com.crm.model.Lead;
import com.crm.model.Opportunity;
import com.crm.model.Task;
import com.crm.model.User;
import com.crm.service.AccountService;
import com.crm.service.CaseService;
import com.crm.service.ContactService;
import com.crm.service.LeadService;
import com.crm.service.OpportunityService;
import com.crm.service.TaskService;
import com.crm.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // Generates constructor for final fields at compile time
@Controller
@RequestMapping("/CRM") // Base url of crm website
@PropertySource("classpath:messages.properties") // To scan the option labels from the 'messages.properties' file
public class DashboardController {

	// Using final fields and a constructor is generally considered best practise
	// for autowiring dependencies
	private final UserService userService;
	private final LeadService leadService;
	private final AccountService accountService;
	private final ContactService contactService;
	private final OpportunityService opportunityService;
	private final CaseService caseService;
	private final TaskService taskService;

	// Accessing the dashboard page
	@GetMapping("/dashboard")
	private String getDashboardPage(@ModelAttribute("user") User user, @ModelAttribute("lead") Lead lead,
			@ModelAttribute("account") Account account, @ModelAttribute("contact") Contact contact,
			@ModelAttribute("opportunity") Opportunity opportunity, @ModelAttribute("caseObj") Case caseObj,
			@ModelAttribute("task") Task task, ModelMap map, HttpSession session, HttpServletResponse response) {

		// To prevent returning back to dashboard page after logout (by clicking back
		// button)
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setHeader("Expires", "0"); // Proxies

		// To prevent unauthorised access to dashboard page without logging in
		if (session.getAttribute("user") == null) {
			return "redirect:/CRM/login";
		}

		// Fetching all values of user from db and storing it in session
		User userSessionObj = (User) session.getAttribute("user");
		User fetchedUser = userService.getUserById(userSessionObj.getUserId());
		session.setAttribute("userObj", fetchedUser);

		map.addAttribute("userObj", fetchedUser);

		return "dashboardPage";
	}

	// Accessing the logout page
	@GetMapping("/logout")
	private String getLogoutPage(@ModelAttribute("user") User user, HttpSession session) {

		// Removing both attributes from session to prevent unauthorised access after
		// logout
		session.removeAttribute("user");
		session.removeAttribute("userObj");
		session.invalidate(); // Invalidates this session and then unbinds any objects bound to it.

		return "logoutPage";
	}

	/*****************
	 * Methods for validating and adding new account,lead etc.
	 *******************/

	// New Account
	@RequestMapping(value = "/dashboard/new-account", method = { RequestMethod.GET, RequestMethod.POST })
	private String addNewAccount(@Valid @ModelAttribute("account") Account account, BindingResult results,
			HttpSession session, HttpServletRequest request,
			@Value("${error.alert.account.invalid}") String invalidAccountMsg,
			@Value("${error.alert.account.alreadyExists}") String accountAlreadyExistsMsg) {

		// Returns to dashboard page in case of validation errors
		if (results.hasErrors()) {

			session.setAttribute("errorMessage", invalidAccountMsg);

			return "dashboardPage";
		}

		// Displays error message and returns to dashboard page if the account is
		// already registered. If not, then the account details are inserted
		// into database
		if (!accountService.addAccount(account, (User) session.getAttribute("userObj"))) {
			results.rejectValue("isAlreadyRegistered", "error.account.cantRegisterAgain");

			session.setAttribute("errorMessage", accountAlreadyExistsMsg);

			return "dashboardPage";
		}

		// Returns to the success page upon successful validation and insertion of the
		// above account in the database
		return "success-pages/addAccountSuccessPage";
	}

	// New Contact (custom-validation)
	@RequestMapping(value = "/dashboard/new-contact", method = { RequestMethod.GET, RequestMethod.POST })
	private String addNewContact(@Valid @ModelAttribute("contact") Contact contact, BindingResult results,
			HttpSession session, @Value("${error.alert.contact.invalid}") String invalidContactMsg,
			@Value("${error.alert.contact.alreadyExists}") String contactAlreadyExistsMsg) {

		User userSessionObj = (User) session.getAttribute("userObj");

		// Custom validation of contact through 'ContactValidator.java'
		contactService.validate(contact, results);

		if (results.hasErrors()) {
			session.setAttribute("errorMessage", invalidContactMsg);
			return "dashboardPage";
		}

		// Checking if account exists in DB or not
		if (!accountService.isAccountRegistered(contact.getAccountName(), userSessionObj)) {
			results.rejectValue("accountName", "error.account.notExists");
			session.setAttribute("errorMessage", invalidContactMsg);
			return "dashboardPage";
		}

		if (!contactService.addContact(contact, userSessionObj)) {
			results.rejectValue("isAlreadyRegistered", "error.contact.cantRegisterAgain");
			session.setAttribute("errorMessage", contactAlreadyExistsMsg);
			return "dashboardPage";
		}

		return "success-pages/addContactSuccessPage";
	}

	// New Lead
	@RequestMapping(value = "/dashboard/new-lead", method = { RequestMethod.GET, RequestMethod.POST })
	private String addNewLead(@Valid @ModelAttribute("lead") Lead lead, BindingResult results, HttpSession session,
			@Value("${error.alert.lead.invalid}") String invalidLeadMsg,
			@Value("${error.alert.lead.alreadyExists}") String leadAlreadyExistsMsg) {

		if (results.hasErrors()) {
			session.setAttribute("errorMessage", invalidLeadMsg);
			return "dashboardPage";
		}

		if (!leadService.addLead(lead, (User) session.getAttribute("userObj"))) {
			results.rejectValue("isAlreadyRegistered", "error.lead.cantRegisterAgain");
			session.setAttribute("errorMessage", leadAlreadyExistsMsg);
			return "dashboardPage";
		}

		return "success-pages/addLeadSuccessPage";
	}

	// New Opportunity (custom-validation)
	@RequestMapping(value = "/dashboard/new-opportunity", method = { RequestMethod.GET, RequestMethod.POST })
	private String addNewOpportunity(@Valid @ModelAttribute("opportunity") Opportunity opportunity,
			BindingResult results, HttpSession session,
			@Value("${error.alert.opportunity.invalid}") String invalidOpportunityMsg,
			@Value("${error.alert.opportunity.alreadyExists}") String opportunityAlreadyExistsMsg) {

		User userSessionObj = (User) session.getAttribute("userObj");

		// Custom validation of opportunity through 'OpportunityValidator.java'
		opportunityService.validate(opportunity, results);

		if (results.hasErrors()) {
			session.setAttribute("errorMessage", invalidOpportunityMsg);
			return "dashboardPage";
		}

		// Checking if account exists in DB or not
		if (!accountService.isAccountRegistered(opportunity.getAccountName(), userSessionObj)) {
			results.rejectValue("accountName", "error.account.notExists");
			session.setAttribute("errorMessage", invalidOpportunityMsg);
			return "dashboardPage";
		}

		if (!opportunityService.addOpportunity(opportunity, userSessionObj)) {
			results.rejectValue("isAlreadyRegistered", "error.opportunity.cantRegisterAgain");
			session.setAttribute("errorMessage", opportunityAlreadyExistsMsg);
			return "dashboardPage";
		}

		return "success-pages/addOpportunitySuccessPage";
	}

	// New Case
	@RequestMapping(value = "/dashboard/new-case", method = { RequestMethod.GET, RequestMethod.POST })
	private String addNewCase(@Valid @ModelAttribute("caseObj") Case caseObj, BindingResult results,
			HttpSession session, @Value("${error.alert.case.invalid}") String invalidCaseMsg,
			@Value("${error.alert.case.alreadyExists}") String caseAlreadyExistsMsg) {

		User userSessionObj = (User) session.getAttribute("userObj");

		if (results.hasErrors()) {
			session.setAttribute("errorMessage", invalidCaseMsg);
			return "dashboardPage";
		}

		boolean notExistsFlag = false;

		// Checking if account exists in DB or not
		if (!accountService.isAccountRegistered(caseObj.getAccountName(), userSessionObj)) {
			results.rejectValue("accountName", "error.account.notExists");
			notExistsFlag = true;
		}

		// Checking if contact exists in DB or not
		if (!contactService.isContactRegistered(caseObj.getContactMobile(), userSessionObj)) {
			results.rejectValue("contactMobile", "error.contact.notExists");
			notExistsFlag = true;
		}

		// If either or both account and contact do not exists in DB, display alert
		if (notExistsFlag) {
			session.setAttribute("errorMessage", invalidCaseMsg);
			return "dashboardPage";
		}

		if (!caseService.addCase(caseObj, userSessionObj)) {
			results.rejectValue("isAlreadyRegistered", "error.case.cantRegisterAgain");
			session.setAttribute("errorMessage", caseAlreadyExistsMsg);
			return "dashboardPage";
		}

		return "success-pages/addCaseSuccessPage";
	}

	// New Task (custom-validation)
	@RequestMapping(value = "/dashboard/new-task", method = { RequestMethod.GET, RequestMethod.POST })
	private String addNewTask(@Valid @ModelAttribute("task") Task task, BindingResult results, HttpSession session,
			@Value("${error.alert.task.invalid}") String invalidTaskMsg,
			@Value("${error.alert.task.alreadyExists}") String taskAlreadyExistsMsg) {
		
		User userSessionObj = (User) session.getAttribute("userObj");

		// Custom validation of task through 'TaskValidator.java'
		taskService.validate(task, results);

		if (results.hasErrors()) {
			session.setAttribute("errorMessage", invalidTaskMsg);
			return "dashboardPage";
		}
		
		boolean notExistsFlag = false;

		// Checking if account exists in DB or not
		if (!accountService.isAccountRegistered(task.getRelatedTo(), userSessionObj)) {
			results.rejectValue("relatedTo", "error.account.notExists");
			notExistsFlag = true;
		}

		// Checking if contact exists in DB or not
		if (!contactService.isContactRegistered(task.getAssignedTo(), userSessionObj)) {
			results.rejectValue("assignedTo", "error.contact.notExists");
			notExistsFlag = true;
		}

		// If either or both account and contact do not exists in DB, display alert
		if (notExistsFlag) {
			session.setAttribute("errorMessage", invalidTaskMsg);
			return "dashboardPage";
		}

		if (!taskService.addTask(task, userSessionObj)) {
			results.rejectValue("isAlreadyRegistered", "error.task.cantRegisterAgain");
			session.setAttribute("errorMessage", taskAlreadyExistsMsg);
			return "dashboardPage";
		}

		return "success-pages/addTaskSuccessPage";
	}

	/****************
	 * Methods for populating the select tag options in dashboard page
	 *******************/

	@ModelAttribute("typeOptions")
	private List<String> populateAccountTypeOptions(
			@Value("#{'${label.type.options}'.split(',')}") List<String> typeOptions) {
		return typeOptions;
	}

	@ModelAttribute("industryOptions")
	private List<String> populateAccountIndustryOptions(
			@Value("#{'${label.industry.options}'.split(',')}") List<String> industryOptions) {
		return industryOptions;
	}

	@ModelAttribute("nameSalutationOptions")
	private List<String> populateNameSalutationOptions(
			@Value("#{'${label.name.salutation.options}'.split(',')}") List<String> nameSalutationOptions) {
		return nameSalutationOptions;
	}

	@ModelAttribute("leadSourceOptions")
	private List<String> populateLeadSourceOptions(
			@Value("#{'${label.leadSource.options}'.split(',')}") List<String> leadSourceOptions) {
		return leadSourceOptions;
	}

	@ModelAttribute("leadStatusOptions")
	private List<String> populateleadStatusOptions(
			@Value("#{'${label.leadStatus.options}'.split(',')}") List<String> leadStatusOptions) {
		return leadStatusOptions;
	}

	@ModelAttribute("stageOptions")
	private List<String> populateStageOptions(
			@Value("#{'${label.stage.options}'.split(',')}") List<String> stageOptions) {
		return stageOptions;
	}

	@ModelAttribute("caseStatusOptions")
	private List<String> populateCaseStatusOptions(
			@Value("#{'${label.caseStatus.options}'.split(',')}") List<String> caseStatusOptions) {
		return caseStatusOptions;
	}

	@ModelAttribute("caseTypeOptions")
	private List<String> populateCaseTypeOptions(
			@Value("#{'${label.caseType.options}'.split(',')}") List<String> caseTypeOptions) {
		return caseTypeOptions;
	}

	@ModelAttribute("caseOriginOptions")
	private List<String> populateCaseOriginOptions(
			@Value("#{'${label.caseOrigin.options}'.split(',')}") List<String> caseOriginOptions) {
		return caseOriginOptions;
	}

	@ModelAttribute("priorityOptions")
	private List<String> populatePriorityOptions(
			@Value("#{'${label.priority.options}'.split(',')}") List<String> priorityOptions) {
		return priorityOptions;
	}

	@ModelAttribute("taskStatusOptions")
	private List<String> populateTaskStatusOptions(
			@Value("#{'${label.taskStatus.options}'.split(',')}") List<String> taskStatusOptions) {
		return taskStatusOptions;
	}

	// Model attributes required for modal popup forms
	@ModelAttribute("lead")
	private Lead getLeadModelAttribute() {
		return new Lead();
	}

	@ModelAttribute("account")
	private Account getAccountModelAttribute() {
		return new Account();
	}

	@ModelAttribute("contact")
	private Contact getContactModelAttribute() {
		return new Contact();
	}

	@ModelAttribute("opportunity")
	private Opportunity getOpportunityModelAttribute() {
		return new Opportunity();
	}

	@ModelAttribute("caseObj")
	private Case getCaseModelAttribute() {
		return new Case();
	}

	@ModelAttribute("task")
	private Task getTaskModelAttribute() {
		return new Task();
	}

	// Method for handling all kinds of exceptions
	@ExceptionHandler(Exception.class)
	private String getErrorPageOnFailure() {
		return "errorPage";
	}

}
