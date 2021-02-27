<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<link rel="stylesheet" href="/custom_css/loginPageStyles.css" />
	<link rel="stylesheet" href="/css/bootstrap.min.css" />
	<title>Customer Relationship Management - Login Page</title>
</head>
<body>
	<h1 class="text-center font-white">Login Page</h1>
	<div class="container">
		<div class="card col-4 offset-4">
			<div class="card-header text-center">Login</div>
			<div class="card-body">
				<h5 class="card-title">Enter your credentials to login</h5>
				<form:form action="/CRM/login" method="post" modelAttribute="user">
					<div class="mb-3">
						<form:label class="form-label" path="userId"><spring:message code="label.id" /></form:label>
						<form:input class="form-control" aria-describedby="userIdHelp" path="userId" />
						<form:errors path="userId" cssClass="red-font"/>
						<div id="userIdHelp" class="form-text">
							We'll never share your personal details with anyone else.
						</div>
					</div>
					<div class="mb-3">
						<form:label class="form-label" path="password"><spring:message code="label.password" /></form:label>
						<form:password class="form-control" path="password" />
						<form:errors path="password" cssClass="red-font"/>
					</div>
					<div class="mb-3 text-center">
						<button type="submit" class="btn-lg btn-primary">Login</button>
						<a href="/CRM/registration" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">Sign Up</a><br>
						<form:errors path="isAuthentic" cssClass="red-font" />
					</div>
				</form:form>
			</div>
		</div>
	</div>
	
	<script src="/js/bootstrap.min.js"></script>
</body>
</html>
