<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isErrorPage="true"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title></title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="" rel="stylesheet">
</head>
<body align="center">
	<h1>Error page!</h1><br><br>
	<h3>The following error occurred:-</h3>
	<%= exception.getMessage() %>
</body>
</html>