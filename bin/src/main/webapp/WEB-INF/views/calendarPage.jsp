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
	
	<!-- Bootstrap CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
	integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"	crossorigin="anonymous" />
	
	<link rel="stylesheet" href="/custom_css/userRegisterSuccessPageStyles.css" />
	<link rel="stylesheet" href="/css/bootstrap.min.css" />
	<link rel="stylesheet" href="/custom_css/calendarStyles.css" />
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" />
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Quicksand:wght@300;400;500;600;700&display=swap" />
	<title>Customer Relationship Management - Calendar Page</title>
</head>
<body>
	
	<div class="container-fluid p-0">
		<h1 class="text-center font-white">CALENDAR PAGE</h1>
		<div align="center">
			<a href="/CRM/dashboard" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">Back to Dashboard</a>
			<a class="btn btn-primary btn-lg active" role="button" data-bs-toggle="modal"	
				data-bs-target="#eventsModal">Add Event</a>
			<a href="/CRM/all-events" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">View All Events</a>
		</div>
		<!-- Add Event Modal---------------------------------------------------------------------------------------->
		<div class="modal fade" id="eventsModal" tabindex="-1" aria-labelledby="eventsModal" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">Add Event</h4>
						<button class="btn-close" type="button" data-bs-dismiss="modal"></button>
					</div>
					<div class="modal-body">
						<form:form action="/CRM/add-event" method="post" modelAttribute="event" class="row g-3">
							<h4>Event information</h4>
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
                							<span style="color:red">${error}</span>
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
                							<span style="color:red">${error}</span>
                						</c:forEach>
            						</c:if>
								</spring:bind>
							</div>
							
							<h4>Other information</h4>
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
							</div>
							
						</form:form>
					</div>
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
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
		crossorigin="anonymous">
	</script>
	
    <script src="/custom_css/calendarScript.js"></script>
	<script src="/js/bootstrap.min.js"></script>
</body>
</html>

