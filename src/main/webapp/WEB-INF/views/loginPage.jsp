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
	
	<!-- Bootstrap CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet">
	
	<title>CRM - Login Page</title>
</head>
<body>
	<header>
		<h1>Login Page</h1>
	</header>
	<br>
	
	<div class="container">
		<div class="card col-6 offset-3">
			<div class="card-header"><h2>Login</h2></div>
			<div class="card-body">
				<form:form action="/CRM/login" method="post" modelAttribute="user" class="row g-3">
					<div class="col-md-12">
						<form:label class="form-label" path="userId"><spring:message code="label.id" /></form:label>
						<form:input class="form-control" aria-describedby="userIdHelp" path="userId" />
						<form:errors path="userId" cssClass="red-font"/>
					</div><br>
					
					<div class="col-md-12">
						<form:label class="form-label" path="password"><spring:message code="label.password" /></form:label>
						<form:password class="form-control" path="password" />
						<form:errors path="password" cssClass="red-font"/>
					</div><br>
					
					<div class="col-md-12">
						<button type="submit" class="btn btn-primary">Login</button>
					</div><br>
					
					<div class="col-md-12">
						<a href="/CRM/registration" class="btn btn-primary btn-md active">Sign Up</a>
					</div><br>
					
					<div class="col-md-12">
						<form:errors path="isAuthentic" cssClass="red-font" />
					</div><br>
					
				</form:form>
			</div>
		</div>
	</div>
	
	<!-- Bootstrap Bundle with Popper -->
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js"></script>
</body>
</html>
