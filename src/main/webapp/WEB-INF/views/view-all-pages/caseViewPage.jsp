<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>CRM - All Cases</title>
	
	<!-- Bootstrap CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet">
	
	<link rel="stylesheet" href="/custom_css/entityViewPageStyles.css">
</head>
<body>
	<header>
		<h2>Case View Page</h2>
	</header>
	
	<nav>
		<a href="/CRM/dashboard"> 
			<img alt="back-icon" id="back-button" src="https://cdn5.vectorstock.com/i/thumb-large/22/24/back-icon-vector-10472224.jpg"> 
			DashBoard 
		</a>
		<a href="/CRM/logout">
			<img src="https://findicons.com/files/icons/1620/crystal_project/128/exit.png" alt="Logout icon" />Logout
		</a>
	</nav>

		<div class="content">
			<div class="card">
				<div class="entity-table">
					<table>
						<c:choose>
							<c:when test="${ not empty caseList }">
								<caption>Cases</caption>
								<tr>
									<th>Contact Name</th>
									<th>Contact Mobile</th>
									<th>Account Name</th>
									<th>Email ID</th>
									<th>Subject</th>
									<th>Status</th>
									<th>Type</th>
									<th>Case Origin</th>
									<th>Case Reason</th>
									<th>Case Priority</th>
									<th>Description</th>
								</tr>
								<c:forEach var="caseObj" items="${ caseList }">
									<tr>
										<td>${ caseObj.contactName }</td>
										<td>${ caseObj.contactMobile }</td>
										<td>${ caseObj.accountName }</td>
										<td>${ caseObj.emailId }</td>
										<td>${ caseObj.subject }</td>
										<td>${ caseObj.status }</td>
										<td>${ caseObj.type }</td>
										<td>${ caseObj.caseOrigin }</td>
										<td>${ caseObj.caseReason }</td>
										<td>${ caseObj.priority }</td>
										<td>${ caseObj.description }</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<caption>No Cases available</caption>
							</c:otherwise>
						</c:choose>
					</table>
				</div>
			</div>

		
		<!-- Bootstrap Bundle with Popper -->
		<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js"></script>
  </div>
  </body>
</html>

