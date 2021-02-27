<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
	crossorigin="anonymous" />
<title>Customer Relationship Management - All Tasks</title>
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
	vertical-align: middle;
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

.tasks-table {
	width: 90vw;
}

.tasks-table table {
	width: 100%;
}

.tasks-table table caption {
	font-size: x-large;
	font-weight: bold;
	text-align: center;
	padding-bottom: 1rem;
}

.tasks-table td:nth-child(even) {
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
				<div class="tasks-table">
					<table>
						<c:choose>
							<c:when test="${ not empty taskList }">
								<caption>Tasks</caption>
								<tr>
									<th>Task Name</th>
									<th>Assigned To</th>
									<th>Related To</th>
									<th>Subject</th>
									<th>Due Date</th>
									<th>Reminder Date</th>
									<th>Reminder Time</th>
									<th>Status</th>
									<th>Priority</th>
								</tr>
								<c:forEach var="task" items="${ taskList }">
									<tr>
										<td>${ task.name }</td>
										<td>${ task.assignedTo }</td>
										<td>${ task.relatedTo }</td>
										<td>${ task.subject }</td>
										<td>${ task.dueDate }</td>
										<td>${ task.reminderDate }</td>
										<td>${ task.reminderTime }</td>
										<td>${ task.status }</td>
										<td>${ task.priority }</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<caption>No tasks available</caption>
							</c:otherwise>
						</c:choose>
					</table>
				</div>
			</div>
			
			<a href="/CRM/dashboard" class="btn btn-primary btn-lg active" role="button" aria-pressed="true"> Back to DashBoard </a>
			
		</div>

		
  </body>
</html>

