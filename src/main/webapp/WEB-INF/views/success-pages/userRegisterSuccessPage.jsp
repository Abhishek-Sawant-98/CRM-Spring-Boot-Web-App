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
	<link rel="stylesheet" href="/custom_css/successPageStyles.css" />
	
	<!-- Bootstrap CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet">
	
	<title>CRM - Registration Successful</title>
</head>
<body>
	
	<header>
		<h2>Registration Successful :)</h2>
	</header>
	
	<div>
		<br>
		<img alt="Account Entry Successful" src="https://i.pinimg.com/564x/ca/93/68/ca9368ef147aff0510e7d666a6d526a3.jpg" /><br>
		<h1>THANK YOU :)</h1><br><br>
		<h3>Click on the below button to Login and access our dashboard.</h3>
	</div>
	
	<nav>
		<a href="/CRM/login" class="btn btn-primary btn-lg active">Login</a>
	</nav>
	
	<!-- Bootstrap Bundle with Popper -->
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js"></script>
</body>
</html>
