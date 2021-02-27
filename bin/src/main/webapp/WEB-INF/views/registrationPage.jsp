<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<!-- Required meta tags -->
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1" />

	<!-- Bootstrap CSS -->
	<link
		href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
		rel="stylesheet"
		integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
		crossorigin="anonymous" />

	<link rel="stylesheet" href="/custom_css/registrationPageStyles.css" />
	<title>Customer Relationship Management - Registration Page</title>
</head>
<body>
	<div class="container vh-100" id="signUpContainer">
		<div class="row vh-100 align-content-center">
			<div class="col-8 offset-2">
				<div class="card-header text-center"><h1>Sign Up</h1></div>
				<div class="card-body">
					<h5 class="card-title">Fill in your details to Sign up.</h5>
					<form:form action="/CRM/registration" method="post" modelAttribute="user" class="row g-3">
						<div class="col-md-4">
							<label for="firstName" class="form-label font-white">
								<spring:message	code="label.firstName" />
							</label>
							<form:input class="form-control" id="firstName" path="firstName"  />
							<form:errors path="firstName" cssClass="red-font" />
							
						</div>

						<div class="col-md-4">
							<label for="lastName" class="form-label font-white">
								<spring:message code="label.lastName" />
							</label>
							<form:input class="form-control" id="lastName" path="lastName" />
							<form:errors path="lastName" cssClass="red-font" />
						</div>

						<div class="col-md-4">
							<label for="dob" class="form-label font-white">
								<spring:message	code="label.dob" />
							</label>
							<form:input type="date" class="form-control" id="dob" path="dob" />
							<form:errors path="dob" cssClass="red-font" />
						</div>

						<div class="col-md-4">
							<label for="gender" class="form-label font-white">
								<spring:message	code="label.gender" />
							</label>
							<form:select class="form-select" id="gender" path="gender" items="${genderOptions}"/>
							<form:errors path="gender" cssClass="red-font" />
						</div>

						<div class="col-md-4">
							<label for="contactNumber" class="form-label font-white">
								<spring:message	code="label.contactNumber" />
							</label>
							<form:input class="form-control" id="contactNumber" path="contactNumber" />
							<form:errors path="contactNumber" cssClass="red-font" />
							
						</div>

						<div class="col-md-4">
							<label for="emailId" class="form-label font-white">
								<spring:message	code="label.email" />
							</label>
							<form:input class="form-control" id="emailId" path="emailId" />
							<form:errors path="emailId" cssClass="red-font" />
							
						</div>

						<div class="col-md-4">
							<label for="jobTitle" class="form-label font-white">
								<spring:message	code="label.jobTitle" />
							</label>
							<form:input  class="form-control" id="jobTitle" path="jobTitle" />
							<form:errors path="jobTitle" cssClass="red-font" />
							
						</div>

						<div class="col-md-4">
							<label for="company" class="form-label font-white">
								<spring:message	code="label.company" />
							</label>
							<form:input class="form-control" id="company" path="company" />
							<form:errors path="company" cssClass="red-font" />
							
						</div>

						<div class="col-md-4">
							<label for="country" class="form-label font-white">
								<spring:message	code="label.country" />
							</label>
							<form:input  class="form-control" id="country" path="country" />
							<form:errors path="country" cssClass="red-font" />
							
						</div>

						<div class="col-md-3">
							<label for="employeeCount" class="form-label font-white">
								<spring:message	code="label.employeeCount" />
							</label>
							<form:select class="form-select" id="employeeCount" path="employeeCount" items="${employeeCountOptions}" />
							<form:errors path="employeeCount" cssClass="red-font" />
						</div>

						<div class="col-md-3">
							<label for="userId" class="form-label font-white">
								<spring:message	code="label.id" />
							</label>
							<form:input class="form-control" id="userId" path="userId" />
							<form:errors path="userId" cssClass="red-font" />
						</div>

						<div class="col-md-3">
							<label for="password" class="form-label font-white">
								<spring:message	code="label.password" />
							</label>
							<form:password class="form-control"	id="password" path="password" />
							<form:errors path="password" cssClass="red-font" />
						</div>

						<div class="col-md-3">
							<label for="confirmPassword" class="form-label font-white">
								<spring:message	code="label.confirmPassword" />
							</label>
							<form:password class="form-control" id="confirmPassword" path="confirmPassword" />
							<form:errors path="confirmPassword" cssClass="red-font" />
						</div>

						<div class="col-md-12 center">
							<button class="btn btn-primary" type="submit">Sign Up</button>
							<form:errors path="isAlreadyRegistered" cssClass="red-font" />
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
		crossorigin="anonymous"></script>
	<script src="/custom_js/script.js"></script>
</body>
</html>
