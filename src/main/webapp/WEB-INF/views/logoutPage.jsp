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
	
	<link rel="stylesheet" href="/custom_css/logoutPageStyles.css" />
	
	
	<title>CRM - Logout</title>
</head>
<body>
	
	<header>
		<h2>You have successfully Logged Out. Thank You for visiting our page :)</h2>
	</header>
	<br>
	
	<img alt="Logout Icon"
	src="https://st.depositphotos.com/1005920/2667/i/600/depositphotos_26678809-stock-photo-logout-icon.jpg" /><br><br>

	<div>
		<h4>Please click on the below button to Login Again :)</h4><br>
		<a href="/CRM/login" class="btn btn-primary btn-lg active">Login Again</a><br><br>
	</div>

	<!-- Bootstrap Bundle with Popper -->
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js"></script>
</body>
</html>
