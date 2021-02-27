<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isErrorPage="true"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>CRM - Error Page</title>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<link rel="stylesheet" href="/custom_css/errorPageStyles.css" />
	
	<!-- Bootstrap CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<header>
		<h1>OOPS! Something went wrong :( </h1>
	</header>
	
	<div>
		<br>
		<img alt="error-page-image" src="/images/sad-dog.jpg">
		<h3><br>The following error occurred:-</h3><br>
		<%= exception.getMessage() %>
	</div>
	
</body>
</html>