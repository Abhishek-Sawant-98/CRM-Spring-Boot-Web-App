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
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
	crossorigin="anonymous" />

<link rel="stylesheet" href="/custom_css/logoutPageStyles.css" />


<title>Customer Relationship Management - Log Out</title>
</head>
<body>

	<img alt="Logout Icon"
		src="https://thumbs.dreamstime.com/b/logout-isolated-special-red-round-button-abstract-illustration-logout-special-red-round-button-103921368.jpg" />

	<div class="mb-3 text-center">
		<h1>You have successfully Logged Out!</h1>
		<br>
		<br>
		<h4>Please click on the below button to Login Again!</h4>
		<a href="/CRM/login" class="btn btn-primary btn-lg active"
			role="button" aria-pressed="true">Login Again</a><br>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
		crossorigin="anonymous"></script>
</body>
</html>
