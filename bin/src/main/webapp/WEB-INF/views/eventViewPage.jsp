<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Customer Relationship Management - All Events</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
	crossorigin="anonymous" />
<style>
@import url('https://fonts.googleapis.com/css?family=Raleway');

@import	url('https://fonts.googleapis.com/css2?family=Montserrat&display=swap');

* {
	margin: 0;
	padding: 0;
	border-collapse: collapse;
	font-family: 'Montserrat', sans-serif;
}

html, body {
	height: 100%;
	width: 100%;
	background-image: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
	background-size: cover;
	background-repeat: no-repeat;
	vertical-align: top;
	text-align: center;
	background-attachment: fixed;
	display: flex;
	flex-direction: column;
}

.content {
	flex: 2;
	display: flex;
	flex-direction: column;
	justify-content: space-evenly;
	align-items: center;
	color: darkslategrey;
}

.card {
	background-color: rgba(255, 255, 255, 0.7);
	padding: 2rem;
	margin: 1rem 4rem 0.5rem 4rem;
	border-radius: 10px;
	box-shadow: 0 15px 30px -10px grey;
	width: min-content;
}

.events-table {
	width: 90vw;
}

.events-table table {
	width: 100%;
}

.events-table table caption {
	font-size: x-large;
	font-weight: bold;
	text-align: center;
	padding-bottom: 1rem;
}

.events-table td:nth-child(even) {
	padding: 1rem;
	background-color: #eeeeee;
}

.container {
	width: 25vw;
}

</style>
</head>
<body>

		<div class="content">
			<div class="card">
				<div class="events-table">
					<table>
						<c:choose>
							<c:when test="${ not empty eventList }">
								<caption>Events</caption>
								<tr>
									<th>Event Name</th>
									<th>Assigned To</th>
									<th>Related To</th>
									<th>Subject</th>
									<th>Event Date</th>
									<th>Start Time</th>
									<th>End Time</th>
									<th>Location</th>
									<th>Description</th>
								</tr>
								<c:forEach var="event" items="${ eventList }">
									<tr>
										<td>${ event.name }</td>
										<td>${ event.assignedTo }</td>
										<td>${ event.relatedTo }</td>
										<td>${ event.subject }</td>
										<td>${ event.date }</td>
										<td>${ event.startTime }</td>
										<td>${ event.endTime }</td>
										<td>${ event.location }</td>
										<td>${ event.description }</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<caption>No Events available</caption>
							</c:otherwise>
						</c:choose>
					</table>
				</div>
			</div>

		<a href="/CRM/dashboard" class="btn btn-primary btn-lg active" role="button" aria-pressed="true"> Back to DashBoard </a>
		<a href="/CRM/calendar-page" class="btn btn-primary btn-lg active" role="button" aria-pressed="true"> Back to Calendar Page </a>
  </div>
  </body>
</html>

