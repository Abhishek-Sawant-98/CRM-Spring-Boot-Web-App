<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<!-- Required meta tags -->
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	
	<!-- Bootstrap CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet">
	
	<link rel="stylesheet" href="/custom_css/dashboardPageStyles.css" />
	
	<title>CRM - Dashboard</title>
</head>
<body>
	<c:if test="${not empty errorMessage}">
    	<script>
	        alert("${errorMessage}");
	        <% 
	        	session.removeAttribute("errorMessage");
	        %>
   		</script>
	</c:if>
	<header>
		<h2>Welcome ${userObj.firstName} ${userObj.lastName} :)</h2>
	</header>
	<div class="container-fluid p-0" id="navContainer">
		<nav class="navbar navbar-expand-lg navbar-light bg-light w-auto">
			<div class="container-fluid justify-content-evenly w-auto">
				<a class="navbar-brand align-content-center" href="#">CRM</a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown"
					aria-controls="navbarNavDropdown" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>

				<div class="collapse navbar-collapse" id="navbarNavDropdown">
					<ul class="navbar-nav">

						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#"
							id="leadsDropdownMenuLink" 
							data-bs-toggle="dropdown" aria-expanded="false"> Leads </a>
							<ul class="dropdown-menu" aria-labelledby="leadsDropdownMenuLink">
								<li><a class="dropdown-item" data-bs-toggle="modal"
									data-bs-target="#leadsModal">New Lead</a></li>
								<li><a class="dropdown-item" href="/CRM/all-leads">View All Leads</a></li>
							</ul></li>

						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#"
							id="accountsDropdownMenuLink" 
							data-bs-toggle="dropdown" aria-expanded="false"> Accounts </a>
							<ul class="dropdown-menu"
								aria-labelledby="accountsDropdownMenuLink">
								<li><a class="dropdown-item" data-bs-toggle="modal"
									data-bs-target="#accountsModal">New Account</a></li>
								<li><a class="dropdown-item" href="/CRM/all-accounts">View All Accounts</a></li>
							</ul></li>

						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#"
							id="contactsDropdownMenuLink" 
							data-bs-toggle="dropdown" aria-expanded="false"> Contacts </a>
							<ul class="dropdown-menu"
								aria-labelledby="contactsDropdownMenuLink">
								<li><a class="dropdown-item" data-bs-toggle="modal"
									data-bs-target="#contactsModal">New Contact</a></li>
								<li><a class="dropdown-item" href="/CRM/all-contacts">View All Contacts</a></li>
							</ul></li>

						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#"
							id="opportunitiesDropdownMenuLink" 
							data-bs-toggle="dropdown" aria-expanded="false">
								Opportunities </a>
							<ul class="dropdown-menu"
								aria-labelledby="opportunitiesDropdownMenuLink">
								<li><a class="dropdown-item" data-bs-toggle="modal"
									data-bs-target="#opportunitiesModal">New Opportunity</a></li>
								<li><a class="dropdown-item" href="/CRM/all-opportunities">View All Opportunites</a></li>
							</ul></li>
							
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#"
							id="accountsDropdownMenuLink" 
							data-bs-toggle="dropdown" aria-expanded="false"> Cases </a>
							<ul class="dropdown-menu" aria-labelledby="accountsDropdownMenuLink">
								<li><a class="dropdown-item" data-bs-toggle="modal"
									data-bs-target="#casesModal">New Case</a></li>
								<li><a class="dropdown-item" href="/CRM/all-cases">View All Cases</a></li>
							</ul></li>
							
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#"
							id="accountsDropdownMenuLink" 
							data-bs-toggle="dropdown" aria-expanded="false"> Tasks </a>
							<ul class="dropdown-menu" aria-labelledby="accountsDropdownMenuLink">
								<li><a class="dropdown-item" data-bs-toggle="modal"
									data-bs-target="#tasksModal">New Task</a></li>
								<li><a class="dropdown-item" href="/CRM/all-tasks">View All Tasks</a></li>
							</ul></li>

						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="/CRM/calendar-page">Calendar</a></li>

						<li class="nav-item">
							<a href="/CRM/logout"  >
								<img src="https://findicons.com/files/icons/1620/crystal_project/128/exit.png" alt="Logout icon" />Logout</a>
						</li>

					</ul>
				</div>
			</div>
		</nav>
		<!--Write next modal from here----------------------------------------------------------------------------------------------------->
		<!-- New Leads Modal---------------------------------------------------------------------------------------->
		<div class="modal fade" id="leadsModal" tabindex="-1"
			aria-labelledby="leadsleModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<!-- Leads Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<h3 class="modal-title">New Lead</h3>
						<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
					</div>
					<div class="modal-body">
						<form:form action="/CRM/dashboard/new-lead" method="post" modelAttribute="lead" class="row g-3">
							<div class="col-md-4">
								<label class="form-label" for="leadOwner"><spring:message code="label.leadOwner" /></label> 
								<input type="text" value='${userObj.userId} - ${userObj.firstName} ${userObj.lastName}'  class="form-control" readonly="readonly" id="leadOwner"  />
							</div>

							<div class="col-md-4">
								<label class="form-label" for="leadStatus"><spring:message code="label.leadStatus" /></label> 
								<form:select path="leadStatus" class="form-control" id="leadStatus" items="${leadStatusOptions}"/>
							</div>

							<div class="col-md-4">
								<label class="form-label" for="salutation"><spring:message code="label.salutation" /></label> 
								<form:select path="salutation" class="form-control" id="salutation" items="${nameSalutationOptions}"/>
							</div>

							<div class="col-md-4">
								<label class="form-label" for="firstName"><spring:message code="label.firstName" /></label> 
								<form:input path="firstName" class="form-control" id="firstName" />
								<form:errors path="firstName" cssClass="red-font" />
							</div>

							<div class="col-md-4">
								<label class="form-label" for="lastName"><spring:message code="label.lastName" /></label> 
								<form:input path="lastName" class="form-control" id="lastName" />
								<form:errors path="lastName" cssClass="red-font" />
							</div>

							<div class="col-md-4">
								<label class="form-label" for="phone"><spring:message code="label.phone" /></label> 
								<form:input path="phone" class="form-control" id="phone" />
								<form:errors path="phone" cssClass="red-font" />
							</div>

							<div class="col-md-4">
								<label class="form-label" for="company"><spring:message code="label.company" /></label> 
								<form:input path="company" class="form-control" id="company" />
								<form:errors path="company" cssClass="red-font" />
							</div>

							<div class="col-md-8">
								<label class="form-label" for="emailId"><spring:message code="label.email" /></label> 
								<form:input path="emailId" class="form-control" id="emailId" />
								<form:errors path="emailId" cssClass="red-font" />
							</div>

							<h3>Address Information</h3>
							<div class="col-md-4">
								<label class="form-label" for="street"><spring:message code="label.street" /></label> 
								<form:input path="street" class="form-control" id="street" />
								<form:errors path="street" cssClass="red-font" />
							</div>

							<div class="col-md-4">
								<label class="form-label" for="city"><spring:message code="label.city" /></label> 
								<form:input path="city" class="form-control" id="city" />
								<form:errors path="city" cssClass="red-font" />
							</div>

							<div class="col-md-4">
								<label class="form-label" for="stateOrProvince"><spring:message code="label.stateOrProvince" /></label> 
								<form:input path="stateProvince" class="form-control" id="stateOrProvince" />
								<form:errors path="stateProvince" cssClass="red-font" />
							</div>

							<div class="col-md-4">
								<label class="form-label" for="zipPostal"><spring:message code="label.zipOrPostalCode" /></label> 
								<form:input path="zipPostal" class="form-control" id="zipPostal" />
								<form:errors path="zipPostal" cssClass="red-font" />
							</div>

							<div class="col-md-4">
								<label class="form-label" for="country"><spring:message code="label.country" /></label> 
								<form:input path="country" class="form-control" id="country" />
								<form:errors path="country" cssClass="red-font" />
							</div>

							<div class="col-md-4">
								<label class="form-label" for="website"><spring:message code="label.website" /></label> 
								<form:input path="website" class="form-control" id="website" />
								<form:errors path="website" cssClass="red-font" />
							</div>

							<h3>Additional Information</h3>
							<div class="col-md-4">
								<label class="form-label" for="employeeCount"><spring:message code="label.employeeCount" /></label> 
								<form:input path="employeeCount" class="form-control" id="employeeCount" />
								<form:errors path="employeeCount" cssClass="red-font" />
							</div>

							<div class="col-md-4">
								<label class="form-label" for="leadSource"><spring:message code="label.leadSource" /></label> 
								<form:select path="leadSource" class="form-control" id="leadSource" items="${leadSourceOptions}"/>
							</div>

							<div class="col-md-4">
								<label class="form-label" for="annualRevenue"><spring:message code="label.annualRevenue" /></label> 
								<form:input path="annualRevenue" class="form-control" id="annualRevenue" />
								<form:errors path="annualRevenue" cssClass="red-font" />
							</div>

							<div class="col-md-4">
								<label class="form-label" for="industry"><spring:message code="label.industry" /></label> 
								<form:select path="industry" class="form-control" id="industry" items="${industryOptions}"/>
							</div>

							<div class="col-md-8">
								<label class="form-label" for="description"><spring:message code="label.description" /></label> 
								<form:input path="description" class="form-control" id="description" />
								<form:errors path="description" cssClass="red-font" />
							</div>

							<div class="col-md-12 center">
								<input type="submit" class="btn btn-primary" value="Save"/>
								<form:errors path="isAlreadyRegistered" cssClass="red-font" />
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>

		<!-- New Accounts Modal---------------------------------------------------------------------------------------------->
		<div class="modal fade" id="accountsModal" tabindex="-1"
			aria-labelledby="accountsModal" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<h3 class="modal-title">New Account</h3>
						<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
					</div>
					<div class="modal-body">
						<form:form action="/CRM/dashboard/new-account" method="post" modelAttribute="account" class="row g-3">
							<h3>Account Information:</h3>
							 <div class="col-md-4">
								<label class="form-label" for="accountOwner"><spring:message code="label.accountOwner" /></label> 
								<input type="text" value='${userObj.userId} - ${userObj.firstName} ${userObj.lastName}'  class="form-control" readonly="readonly" id="accountOwner"  />
							</div>

							<div class="col-md-4">
								<label class="form-label" for="accountName"><spring:message code="label.accountName" /></label> 
								<form:input path="accountName" class="form-control" id="accountName" />
								<form:errors path="accountName" cssClass="red-font" />
							</div>

							<div class="col-md-4">
								<label class="form-label" for="phone"><spring:message code="label.phone" /></label> 
								<form:input path="phone" class="form-control" id="phone" />
								<form:errors path="phone" cssClass="red-font" />
							</div>

							<div class="col-md-4">
								<label class="form-label" for="fax"><spring:message code="label.fax" /></label> 
								<form:input path="fax" class="form-control" id="fax" />
								<form:errors path="fax" cssClass="red-font" />
							</div>

							<div class="col-md-8">
								<label class="form-label" for="website"><spring:message code="label.website" /></label> 
								<form:input path="website" class="form-control" id="website"  />
								<form:errors path="website" cssClass="red-font" />
							</div>

							<h3>Additional Information</h3>
							<div class="col-md-4">
								<label class="form-label" for="type"><spring:message code="label.type" /></label> 
								<form:select path="type" class="form-control" id="type" items="${typeOptions}"/>
							</div>

							<div class="col-md-4">
								<label class="form-label" for="employeeCount"><spring:message code="label.employeeCount" /></label> 
								<form:input path="employeeCount" class="form-control" id="employeeCount" />
								<form:errors path="employeeCount" cssClass="red-font" />
							</div>

							<div class="col-md-4">
								<label class="form-label" for="industry"><spring:message code="label.industry" /></label> 
								<form:select path="industry" class="form-control" id="industry" items="${industryOptions}"/>
							</div>

							<div class="col-md-4">
								<label class="form-label" for="annualRevenue"><spring:message code="label.annualRevenue" /></label> 
								<form:input path="annualRevenue" class="form-control" id="annualRevenue" />
								<form:errors path="annualRevenue" cssClass="red-font" />
							</div>
							<div class="col-md-8">
								<label class="form-label" for="description"><spring:message code="label.description" /></label> 
								<form:input path="description" class="form-control" id="description" />
								<form:errors path="description" cssClass="red-font" />
							</div>

							<h3>Billing Address</h3>
							<div class="col-md-4">
								<label class="form-label" for="billingStreet"><spring:message code="label.street" /></label> 
								<form:input path="billingStreet" class="form-control"  id="billingStreet" />
								<form:errors path="billingStreet" cssClass="red-font" />
							</div>

							<div class="col-md-4">
								<label class="form-label" for="billingCity"><spring:message code="label.city" /></label> 
								<form:input path="billingCity" class="form-control" id="billingCity" />
								<form:errors path="billingCity" cssClass="red-font" />
							</div>

							<div class="col-md-4">
								<label class="form-label" for="billingStateProvince"><spring:message code="label.stateOrProvince" /></label> 
								<form:input path="billingStateProvince" class="form-control" id="billingState"/>
								<form:errors path="billingStateProvince" cssClass="red-font" />
							</div>

							<div class="col-md-4">
								<label class="form-label" for="billingZipPostal"><spring:message code="label.zipOrPostalCode" /></label> 
								<form:input path="billingZipPostal" class="form-control" id="billingPost"  />
								<form:errors path="billingZipPostal" cssClass="red-font" />
							</div>

							<div class="col-md-8">
								<label class="form-label" for="billingCountry"><spring:message code="label.country" /></label> 
								<form:input path="billingCountry" class="form-control" id="billingCountry" />
								<form:errors path="billingCountry" cssClass="red-font" />
							</div>

							<!-- Checkbox to set the shipping address the same as billing address -->
							<div class="col-md-12">
								<input type="checkbox" onclick="populateShippingAddress()" id="sameAddress" /> 
								<label for="sameAddress" id="address-label"> Shipping address same as Billing address</label>
							</div>

							<h3>Shipping Address</h3>
							<div class="col-md-4">
								<label class="form-label" for="shippingStreet"><spring:message code="label.street" /></label> 
								<form:input path="shippingStreet" class="form-control" id="shippingStreet" />
								<form:errors path="shippingStreet" cssClass="red-font" />
							</div>

							<div class="col-md-4">
								<label class="form-label" for="shippingCity"><spring:message code="label.city" /></label> 
								<form:input path="shippingCity" class="form-control" id="shippingCity"  />
								<form:errors path="shippingCity" cssClass="red-font" />
							</div>

							<div class="col-md-4">
								<label class="form-label" for="shippingStateProvince"><spring:message code="label.stateOrProvince" /></label> 
								<form:input path="shippingStateProvince" class="form-control" id="shippingState"  />
								<form:errors path="shippingStateProvince" cssClass="red-font" />
							</div>

							<div class="col-md-4">
								<label class="form-label" for="shippingZipPostal"><spring:message code="label.zipOrPostalCode" /></label> 
								<form:input path="shippingZipPostal" class="form-control" id="shippingPost" />
								<form:errors path="shippingZipPostal" cssClass="red-font" />
							</div>

							<div class="col-md-8">
								<label class="form-label" for="shippingCountry"><spring:message code="label.country" /></label> 
								<form:input path="shippingCountry" class="form-control" id="shippingCountry"/>
								<form:errors path="shippingCountry" cssClass="red-font" />
							</div>

							<div class="col-md-12 center">
								<input type="submit" class="btn btn-primary" value="Save"/>
								<form:errors path="isAlreadyRegistered" cssClass="red-font" />
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>

		<!-- New Contacts Modal---------------------------------------------------------------------------------------------------->
		<div class="modal fade" id="contactsModal" tabindex="-1"
			aria-labelledby="contactsLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<!-- Contacts Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<h3 class="modal-title">New Contact</h3>
						<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
					</div>
					
					<div class="modal-body">
						<form:form action="/CRM/dashboard/new-contact" method="post" modelAttribute="contact" class="row g-3">
							<h3>Contact Information</h3>
							<div class="col-md-4">
								<label class="form-label" for="contactOwner"><spring:message code="label.contactOwner" /></label> 
								<input type="text" value='${userObj.userId} - ${userObj.firstName} ${userObj.lastName}'  class="form-control" readonly="readonly" id="contactOwner"  />
							</div>
							
							<div class="col-md-4">
								<label class="form-label" for="mobile"><spring:message code="label.mobile" /></label> 
								<form:input path="mobile" class="form-control" id="mobile" />
								<form:errors path="mobile" cssClass="red-font" />
							</div>

							<div class="col-md-4">
								<label class="form-label" for="salutation"><spring:message code="label.salutation" /></label> 
								<form:select path="salutation" class="form-control" id="salutation" items="${nameSalutationOptions}"/>
							</div>

							<div class="col-md-6">
								<label class="form-label" for="firstName"><spring:message code="label.firstName" /></label> 
								<form:input path="firstName" class="form-control" id="firstName" />
								<form:errors path="firstName" cssClass="red-font" />
							</div>

							<div class="col-md-6">
								<label class="form-label" for="lastName"><spring:message code="label.lastName" /></label> 
								<form:input path="lastName" class="form-control" id="lastName" />
								<form:errors path="lastName" cssClass="red-font" />
							</div>

							<div class="col-md-4">
								<label class="form-label" for="accountName"><spring:message code="label.accountName" /></label> 
								<form:input path="accountName" class="form-control" id="accountName" />
								<form:errors path="accountName" cssClass="red-font" /> 
							</div>

							<div class="col-md-4">
								<label class="form-label" for="emailId"><spring:message code="label.email" /></label> 
								<form:input path="emailId" class="form-control" id="emailId" />
								<form:errors path="emailId" cssClass="red-font" />
							</div>

							<div class="col-md-4">
								<label class="form-label" for="title"><spring:message code="label.title" /></label> 
								<form:input path="title" class="form-control" id="title" />
								<form:errors path="title" cssClass="red-font" />
							</div>

							<h3>Mailing Address</h3>
							<div class="col-md-4">
								<label class="form-label" for="mailingStreet"><spring:message code="label.street" /></label> 
								<form:input path="mailingStreet" class="form-control" id="mailingStreet" />
								<form:errors path="mailingStreet" cssClass="red-font" />
							</div>

							<div class="col-md-4">
								<label class="form-label" for="mailingCity"><spring:message code="label.city" /></label> 
								<form:input path="mailingCity" class="form-control" id="mailingCity"  />
								<form:errors path="mailingCity" cssClass="red-font" />
							</div>

							<div class="col-md-4">
								<label class="form-label" for="mailingStateProvince"><spring:message code="label.stateOrProvince" /></label> 
								<form:input path="mailingStateProvince" class="form-control" id="mailingStateProvince"  />
								<form:errors path="mailingStateProvince" cssClass="red-font" />
							</div>

							<div class="col-md-4">
								<label class="form-label" for="mailingZipPostal"><spring:message code="label.zipOrPostalCode" /></label> 
								<form:input path="mailingZipPostal" class="form-control" id="mailingZipPostal" />
								<form:errors path="mailingZipPostal" cssClass="red-font" />
							</div>

							<div class="col-md-8">
								<label class="form-label" for="mailingCountry"><spring:message code="label.country" /></label> 
								<form:input path="mailingCountry" class="form-control" id="mailingCountry"/>
								<form:errors path="mailingCountry" cssClass="red-font" />
							</div>

							<h3>Other Address</h3>
							<div class="col-md-4">
								<label class="form-label" for="otherStreet"><spring:message code="label.street" /></label> 
								<form:input path="otherStreet" class="form-control" id="otherStreet" />
								<form:errors path="otherStreet" cssClass="red-font" />
							</div>

							<div class="col-md-4">
								<label class="form-label" for="otherCity"><spring:message code="label.city" /></label> 
								<form:input path="otherCity" class="form-control" id="otherCity"  />
								<form:errors path="otherCity" cssClass="red-font" />
							</div>

							<div class="col-md-4">
								<label class="form-label" for="otherStateProvince"><spring:message code="label.stateOrProvince" /></label> 
								<form:input path="otherStateProvince" class="form-control" id="otherStateProvince"  />
								<form:errors path="otherStateProvince" cssClass="red-font" />
							</div>

							<div class="col-md-4">
								<label class="form-label" for="otherZipPostal"><spring:message code="label.zipOrPostalCode" /></label> 
								<form:input path="otherZipPostal" class="form-control" id="otherZipPostal" />
								<form:errors path="otherZipPostal" cssClass="red-font" />
							</div>

							<div class="col-md-8">
								<label class="form-label" for="otherCountry"><spring:message code="label.country" /></label> 
								<form:input path="otherCountry" class="form-control" id="otherCountry"/>
								<form:errors path="otherCountry" cssClass="red-font" />
							</div>

							<h3>Additional Information</h3>
							<div class="col-md-4">
								<label class="form-label" for="fax"><spring:message code="label.fax" /></label> 
								<form:input path="fax" class="form-control" id="fax" />
								<form:errors path="fax" cssClass="red-font" />
							</div>

							<div class="col-md-4">
								<label class="form-label" for="department"><spring:message code="label.department" /></label> 
								<form:input path="department" class="form-control" id="department" />
								<form:errors path="department" cssClass="red-font" />
							</div>

							<div class="col-md-4">
								<label class="form-label" for="homePhone"><spring:message code="label.homePhone" /></label> 
								<form:input path="homePhone" class="form-control" id="homePhone" />
								<form:errors path="homePhone" cssClass="red-font" />
							</div>

							<div class="col-md-4">
								<label class="form-label" for="leadSource"><spring:message code="label.leadSource" /></label> 
								<form:select path="leadSource" class="form-control" id="leadSource" items="${leadSourceOptions}"/>
							</div>

							<div class="col-md-4">
								<label class="form-label" for="advertisement"><spring:message code="label.advertisement" /></label> 
								<form:input path="advertisement" class="form-control" id="advertisement" />
								<form:errors path="advertisement" cssClass="red-font" />
							</div>

							<div class="col-md-4">
								<label class="form-label" for="otherPhone"><spring:message code="label.otherPhone" /></label> 
								<form:input path="otherPhone" class="form-control" id="otherPhone" />
								<form:errors path="otherPhone" cssClass="red-font" />
							</div>

							<div class="col-md-6">
								<label class="form-label" for="dob"><spring:message code="label.dob" /></label> 
								<form:input type="date" path="dob" class="form-control" id="dob" />
								<form:errors path="dob" cssClass="red-font" />
							</div>

							<div class="col-md-6">
								<label class="form-label" for="assistantPhone"><spring:message code="label.assistantPhone" /></label> 
								<form:input path="assistantPhone" class="form-control" id="assistantPhone" />
								<form:errors path="assistantPhone" cssClass="red-font" />
							</div>

							<div class="col-md-6">
								<label class="form-label" for="assistant"><spring:message code="label.assistant" /></label> 
								<form:input path="assistant" class="form-control" id="assistant" />
								<form:errors path="assistant" cssClass="red-font" />
							</div>

							<div class="col-md-6">
								<label class="form-label" for="description"><spring:message code="label.description" /></label> 
								<form:input path="description" class="form-control" id="description" />
								<form:errors path="description" cssClass="red-font" />
							</div>

							<div class="col-md-12 center">
								<input type="submit" class="btn btn-primary" value="Save"/>
								<form:errors path="isAlreadyRegistered" cssClass="red-font" />
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>

		<!-- New Opportunities Modal-------------------------------------------------------------------------------------------->
		<div class="modal fade" id="opportunitiesModal" tabindex="-1"
			aria-labelledby="opportunitiesModal" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<!-- Leads Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<h3 class="modal-title">New Opportunity</h3>
						<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
					</div>
					<div class="modal-body">
						<form:form action="/CRM/dashboard/new-opportunity" method="post" modelAttribute="opportunity" class="row g-3">
							<div class="col-md-4">
								<label class="form-label" for="opportunityOwner"><spring:message code="label.opportunityOwner" /></label> 
								<input type="text" value='${userObj.userId} - ${userObj.firstName} ${userObj.lastName}'  class="form-control" readonly="readonly" id="opportunityOwner"  />
							</div>

							<div class="col-md-4">
								<label class="form-label" for="closeDate"><spring:message code="label.closeDate" /></label> 
								<form:input type="date" path="closeDate" class="form-control" id="closeDate" />
								<form:errors path="closeDate" cssClass="red-font" /> 
							</div>

							<div class="col-md-4">
								<label class="form-label" for="opportunityName"><spring:message code="label.opportunityName" /></label> 
								<form:input path="opportunityName" class="form-control" id="opportunityName" />
								<form:errors path="opportunityName" cssClass="red-font" /> 
							</div>

							<div class="col-md-4">
								<label class="form-label" for="stage"><spring:message code="label.stage" /></label> 
								<form:select path="stage" class="form-control" id="stage" items="${stageOptions}"/>
							</div>

							<div class="col-md-4">
								<label class="form-label" for="accountName"><spring:message code="label.accountName" /></label> 
								<form:input path="accountName" class="form-control" id="accountName" />
								<form:errors path="accountName" cssClass="red-font" /> 
							</div>

							<div class="col-md-4">
								<label class="form-label" for="probability"><spring:message code="label.probability" /></label> 
								<form:input path="probability" class="form-control" id="probability" />
								<form:errors path="probability" cssClass="red-font" />
							</div>

							<div class="col-md-4">
								<label class="form-label" for="type"><spring:message code="label.type" /></label> 
								<form:select path="type" class="form-control" id="type" items="${typeOptions}"/>
							</div>
							<div class="col-md-8"> <!-- Amount -->
								<label class="form-label" for="amount"><spring:message code="label.amount" /></label> 
								<form:input path="amount" class="form-control" id="amount" />
								<form:errors path="amount" cssClass="red-font" />
							</div>

							<h3>Additional Information</h3>
							<div class="col-md-4">
								<label class="form-label" for="leadSource"><spring:message code="label.leadSource" /></label> 
								<form:select path="leadSource" class="form-control" id="leadSource" items="${leadSourceOptions}"/>
							</div>

							<div class="col-md-4">
								<label class="form-label" for="description"><spring:message code="label.description" /></label> 
								<form:input path="description" class="form-control" id="description" />
								<form:errors path="description" cssClass="red-font" />
							</div>
							<div class="col-md-4"> <!-- Next step -->
								<label class="form-label" for="nextStep"><spring:message code="label.nextStep" /></label> 
								<form:input path="nextStep" class="form-control" id="nextStep" />
								<form:errors path="nextStep" cssClass="red-font" />
							</div>

							<div class="col-md-12 center">
								<input type="submit" class="btn btn-primary" value="Save"/>
								<form:errors path="isAlreadyRegistered" cssClass="red-font" />
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
		
		
		<!-- New Case Modal---------------------------------------------------------------------------------------->
		<div id="casesModal" class="modal fade" tabindex="-1" aria-labelledby="casesModal" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<h3 class="modal-title">New Case</h3>
						<button class="btn-close" type="button" data-bs-dismiss="modal"></button>
					</div>
					<div class="modal-body"> 
						<form:form action="/CRM/dashboard/new-case" method="post" modelAttribute="caseObj" class="row g-3">
							<h3>Case Information:</h3>
							<div class="col-md-6">
								<label class="form-label" for="caseOwner"><spring:message code="label.caseOwner" /></label> 
								<input type="text" value='${userObj.userId} - ${userObj.firstName} ${userObj.lastName}'  class="form-control" readonly="readonly" id="caseOwner"  />
							</div>
							
							<div class="col-md-6">
								<label class="form-label" for="contactMobile"><spring:message code="label.contactMobile" /></label> 
								<form:input path="contactMobile" class="form-control" id="contactMobile" />
								<form:errors path="contactMobile" cssClass="red-font" />
							</div>
							
							<div class="col-md-6">
								<label class="form-label" for="accountName"><spring:message code="label.accountName" /></label> 
								<form:input path="accountName" class="form-control" id="accountName" />
								<form:errors path="accountName" cssClass="red-font" />
							</div>
							
							<div class="col-md-6">
								<label class="form-label" for="email"><spring:message code="label.email" /></label> 
								<form:input path="emailId" class="form-control" id="email" />
								<form:errors path="emailId" cssClass="red-font" />
							</div>
							
							<h3>Additional Information:</h3>
							<div class="col-md-4">
								<label class="form-label" for="status"><spring:message code="label.caseStatus" /></label> 
								<form:select path="status" class="form-control" id="status" items="${caseStatusOptions}"/>
							</div>
							
							<div class="col-md-4">
								<label class="form-label" for="type"><spring:message code="label.type" /></label> 
								<form:select path="type" class="form-control" id="type" items="${caseTypeOptions}"/>
							</div>
							
							<div class="col-md-4">
								<label class="form-label" for="origin"><spring:message code="label.caseOrigin" /></label> 
								<form:select path="caseOrigin" class="form-control" id="origin" items="${caseOriginOptions}"/>
							</div>
							
							<div class="col-md-6">
								<label class="form-label" for="reason"><spring:message code="label.caseReason" /></label> 
								<form:select path="caseReason" class="form-control" id="reason">
									<form:option value="User didn't attend training" label="User didn't attend training" />
									<form:option value="Complex functionality" label="Complex functionality" />
									<form:option value="Existing problem" label="Existing problem" />
									<form:option value="Instructions not clear" label="Instructions not clear" />
									<form:option value="New problem" label="New problem" />
								</form:select>
							</div>
							
							<div class="col-md-6">
								<label class="form-label" for="priority"><spring:message code="label.priority" /></label> 
								<form:select path="priority" class="form-control" id="priority" items="${priorityOptions}"/>
							</div>
							
							<h3>Description Information:</h3>
							<div class="col-md-4">
								<label class="form-label" for="subject"><spring:message code="label.subject" /></label> 
								<form:input path="subject" class="form-control" id="subject" />
								<form:errors path="subject" cssClass="red-font" />
							</div>
							
							<div class="col-md-4">
								<label class="form-label" for="description"><spring:message code="label.description" /></label> 
								<form:input path="description" class="form-control" id="description" />
								<form:errors path="description" cssClass="red-font" />
							</div>
							
							<div class="col-md-4">
								<label class="form-label" for="internalComments"><spring:message code="label.internalComments" /></label> 
								<form:input path="internalComments" class="form-control" id="internalComments" />
								<form:errors path="internalComments" cssClass="red-font" />
							</div>
							
							<div class="col-md-12 center">
								<input type="submit" class="btn btn-primary" value="Save"/>
							</div>
							
						</form:form>
					</div>
				</div>
			</div>
		</div>
		
		
		<!-- New Task Modal---------------------------------------------------------------------------------------->
		<div id="tasksModal" class="modal fade" tabindex="-1" aria-labelledby="tasksModal" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<h3 class="modal-title">New Task</h3>
						<button class="btn-close" type="button" data-bs-dismiss="modal"></button>
					</div>
					<div class="modal-body">
						<form:form action="/CRM/dashboard/new-task" method="post" modelAttribute="task" class="row g-3">
							<h3>Task information</h3>
							<div class="col-md-6">
								<label class="form-label" for="assignedTo"><spring:message code="label.assignedTo" /></label> 
								<form:input path="assignedTo" class="form-control" id="assignedTo" />
								<form:errors path="assignedTo" cssClass="red-font" />
							</div>
							<div class="col-md-6">
								<label class="form-label" for="relatedTo"><spring:message code="label.relatedTo" /></label> 
								<form:input path="relatedTo" class="form-control" id="relatedTo" />
								<form:errors path="relatedTo" cssClass="red-font" />
							</div>
							<div class="col-md-6">
								<label class="form-label" for="name"><spring:message code="label.taskName" /></label> 
								<form:input path="name" class="form-control" id="name" />
								<form:errors path="name" cssClass="red-font" />
							</div>
							<div class="col-md-6">
								<label class="form-label" for="subject"><spring:message code="label.subject" /></label> 
								<form:input path="subject" class="form-control" id="subject" />
								<form:errors path="subject" cssClass="red-font" />
							</div>
							<div class="col-md-6">
								<label class="form-label" for="comments"><spring:message code="label.comments" /></label> 
								<form:input path="comments" class="form-control" id="comments" />
								<form:errors path="comments" cssClass="red-font" />
							</div>
							
							<div class="col-md-6">
								<label class="form-label" for="dueDate"><spring:message code="label.dueDate" /></label> 
								<form:input type="date" path="dueDate" class="form-control" id="dueDate" />
								<form:errors path="dueDate" cssClass="red-font" />
							</div>
							
							<h3>Set Task Reminder</h3>
							<div class="col-md-6">
								<label class="form-label" for="reminderDate"><spring:message code="label.reminderDate" /></label> 
								<form:input type="date" path="reminderDate" class="form-control" id="reminderDate" />
								<form:errors path="reminderDate" cssClass="red-font" />
							</div>
							
							<div class="col-md-6">
								<spring:bind path="task.reminderTime">
									<label class="form-label" for="reminderDate"><spring:message code="label.reminderTime" /></label> 
									<input type="time" class="form-control" name="${status.expression}" value="${status.value}" />
									<c:if test="${status.error}">
                						<c:forEach items="${status.errorMessages}" var="error">
                							<span style="color:red">${error}</span>
                						</c:forEach>
            						</c:if>
								</spring:bind>
							</div>
							
								<h3>Additional Information</h3>
							
							<div class="col-md-6">
								<label class="form-label" for="status"><spring:message code="label.taskStatus" /></label> 
								<form:select path="status" class="form-control" id="status" items="${taskStatusOptions}"/>
							</div>
							
							<div class="col-md-6">
								<label class="form-label" for="priority"><spring:message code="label.priority" /></label> 
								<form:select path="priority" class="form-control" id="priority" items="${priorityOptions}"/>
							</div>
							
							<div class="col-md-12 center">
								<input type="submit" class="btn btn-primary" value="Save"/>
							</div>
							
						</form:form>
					</div>
				</div>
			</div>
		</div>	
	</div>
	
	<div class="crm-background"></div>

	<!-- Bootstrap Bundle with Popper -->
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js"></script>
	
	<script type="text/javascript">
	
		function populateShippingAddress(){
			
			const sameCheckBox = document.getElementById('sameAddress');
			
			var billingStreet = document.getElementById('billingStreet').value;
			var billingCity = document.getElementById('billingCity').value;
			var billingState = document.getElementById('billingState').value;
			var billingPost = document.getElementById('billingPost').value;
			var billingCountry = document.getElementById('billingCountry').value;
			
			if(sameCheckBox.checked){
				document.getElementById('shippingStreet').value = billingStreet;
				document.getElementById('shippingCity').value = billingCity;
				document.getElementById('shippingState').value = billingState;
				document.getElementById('shippingPost').value = billingPost;
				document.getElementById('shippingCountry').value = billingCountry;		
			}
			else{
				document.getElementById('shippingStreet').value = '';
				document.getElementById('shippingCity').value = '';
				document.getElementById('shippingState').value = '';
				document.getElementById('shippingPost').value = '';
				document.getElementById('shippingCountry').value = '';	
			}
		}
	
		
	</script>
</body>
</html>
