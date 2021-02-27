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
	<link rel="stylesheet" href="/custom_css/userRegisterSuccessPageStyles.css" />
	<link rel="stylesheet" href="/css/bootstrap.min.css" />
	<title>Customer Relationship Management - New Case Entry Successful</title>
</head>
<body>
	
	<div class="center">
		<img alt="Case Entry Successful" src="https://i.pinimg.com/564x/ca/93/68/ca9368ef147aff0510e7d666a6d526a3.jpg" />
		<h1 class="text-center font-white">THANK YOU!</h1><br><br><br>
		<h3 class="text-center font-white"> Your new case details have been saved successfully :)</h3>
		<h3 class="text-center font-white">Click on the below button to return to our dashboard.</h3>
		<a href="/CRM/dashboard" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">Back to Dashboard</a>
	</div>
	
	<script src="/js/bootstrap.min.js"></script>
</body>
</html>
