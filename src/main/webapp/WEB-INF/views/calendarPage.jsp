<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>CRM - Calendar Page</title>

	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	
	<!-- Bootstrap CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet">
	
	<link rel="stylesheet" href="/custom_css/calendarStyles.css" />
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" />
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Quicksand:wght@300;400;500;600;700&display=swap" />
</head>
<body>
	<c:if test="${not empty errorMessage}">
    	<script>
	        alert("${errorMessage}");
	        <% 
	        	session.removeAttribute("errorMessage");
	        %>
   		</script>
	</c:if>

	<header>
		<h1>CALENDAR PAGE</h1>
	</header>
	
	<nav>
		<a href="/CRM/dashboard"> 
			<img alt="back-icon" id="back-button" src="https://cdn5.vectorstock.com/i/thumb-large/22/24/back-icon-vector-10472224.jpg"> 
			DashBoard 
		</a>
		<a  data-bs-toggle="modal" data-bs-target="#eventsModal">Add Event</a>
		<a href="/CRM/all-events">View All Events</a>
		<a href="/CRM/logout">
			<img src="https://findicons.com/files/icons/1620/crystal_project/128/exit.png" alt="Logout icon" />Logout</a>
	</nav>
	
	<!-- Add Event Modal---------------------------------------------------------------------------------------->
	<div class="modal fade" id="eventsModal" tabindex="-1" aria-labelledby="eventsModal" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h3>New Event</h3>
					<button class="btn-close" type="button" data-bs-dismiss="modal"></button>
				</div>
				<div class="modal-body">
					<form:form action="/CRM/add-event" method="post" modelAttribute="event" class="row g-3">
						<h3>Event information</h3>
						<div class="col-md-6">
							<label class="form-label" for="assignedTo"><spring:message code="label.assignedTo" /></label> 
							<form:input path="assignedTo" class="form-control" id="assignedTo" />
							<form:errors path="assignedTo" cssClass="red-font" />
						</div>
						<div class="col-md-6">
							<label class="form-label" for="relatedTo"><spring:message code="label.relatedTo" /></label> 
							<form:input path="relatedTo" class="form-control" id="relatedTo" />
							<form:errors path="relatedTo" cssClass="red-font" />
						</div>
						
						<div class="col-md-6">
							<label class="form-label" for="subject"><spring:message code="label.subject" /></label> 
							<form:input path="subject" class="form-control" id="subject" />
							<form:errors path="subject" cssClass="red-font" />
						</div>
						<div class="col-md-6">
							<label class="form-label" for="name"><spring:message code="label.eventName" /></label> 
							<form:input path="name" class="form-control" id="name" />
							<form:errors path="name" cssClass="red-font" />
						</div>
						
						<div class="col-md-4">
							<label class="form-label" for="eventDate"><spring:message code="label.eventDate" /></label> 
							<form:input type="date" path="date" class="form-control" id="eventDate" />
							<form:errors path="date" cssClass="red-font" />
						</div>
						
						<div class="col-md-4">
							<spring:bind path="event.startTime">
								<label class="form-label" for="startTime"><spring:message code="label.startTime" /></label> 
								<input type="time" class="form-control" name="${status.expression}" value="${status.value}" />
								<c:if test="${status.error}">
									<c:forEach items="${status.errorMessages}" var="error">
               							<span class="red-font">${error}</span>
               						</c:forEach>
           						</c:if>
							</spring:bind>
						</div>
						<div class="col-md-4">
							<spring:bind path="event.endTime">
								<label class="form-label" for="endTime"><spring:message code="label.endTime" /></label> 
								<input type="time" class="form-control" name="${status.expression}" value="${status.value}" />
								<c:if test="${status.error}">
               						<c:forEach items="${status.errorMessages}" var="error">
               							<span class="red-font">${error}</span>
               						</c:forEach>
           						</c:if>
							</spring:bind>
						</div>
						
						<h3>Other information</h3>
						<div class="col-md-6">
							<label class="form-label" for="location"><spring:message code="label.location" /></label> 
							<form:input path="location" class="form-control" id="location" />
							<form:errors path="location" cssClass="red-font" />
						</div>
						<div class="col-md-6">
							<label class="form-label" for="description"><spring:message code="label.description" /></label> 
							<form:input path="description" class="form-control" id="description" />
							<form:errors path="description" cssClass="red-font" />
						</div>
						
						<div class="col-md-12 center">
							<input type="submit" class="btn btn-primary" value="Save"/>
							<form:errors path="isAlreadyRegistered" cssClass="red-font" />
						</div>
						
					</form:form>
				</div>
			</div>
		</div>
		</div>	
		<div class="container">
	    	<div class="calendar">
	    		<div class="month">
	    			<i class="fas fa-angle-left prev"></i>
	    			<div class="date">
	    				<h1></h1>
	    				<p></p>
	    			</div>
	    			<i class="fas fa-angle-right next"></i>
	    		</div>
	    		<div class="weekdays">
				    <div>Sun</div>
				    <div>Mon</div>
				    <div>Tue</div>
				    <div>Wed</div>
				    <div>Thu</div>
				    <div>Fri</div>
				    <div>Sat</div>
	    		</div>
	   			<div class="days"></div>
	    	</div>
		</div>
       
    
		<!-- Bootstrap Bundle with Popper -->
		<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js"></script>
		
	    <script src="/custom_css/calendarScript.js"></script>
</body>
</html>

