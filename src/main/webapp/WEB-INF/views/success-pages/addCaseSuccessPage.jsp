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
	
	<title>CRM - New Case Entry Successful</title>
</head>
<body>
	<header>
		<h2>Your 'New Case' details successfully saved :)</h2>
	</header>
	
	<nav>
		<a href="/CRM/dashboard"> 
			<img alt="back-icon" id="back-button" src="https://cdn5.vectorstock.com/i/thumb-large/22/24/back-icon-vector-10472224.jpg"> 
			DashBoard 
		</a>
		<a href="/CRM/logout">
			<img src="https://findicons.com/files/icons/1620/crystal_project/128/exit.png" class="icon" alt="Logout icon" />Logout
		</a>
	</nav>
	
	<div>
		<img alt="Case Entry Successful" src="https://i.pinimg.com/564x/ca/93/68/ca9368ef147aff0510e7d666a6d526a3.jpg" /><br>
		<h1>THANK YOU ${userObj.firstName} ${userObj.lastName} :)</h1>
	</div>
	
	<!-- Bootstrap Bundle with Popper -->
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js"></script>
</body>
</html>
