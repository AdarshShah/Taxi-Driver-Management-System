<%@page import="com.bean.TrainingSession"%>
<%@page import="com.bean.Training"%>
<%@page import="com.bean.Qualification"%>
<%@page import="java.util.Set"%>
<%@page import="com.bean.Driver"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
</head>
<body>

<%
	Driver d = (Driver)request.getAttribute("driver");
	if(d==null){
		response.sendRedirect("TaxiDriverSystem/jsp/driverindex.jsp");	
	}
%>
<div class="container">
<div class="jumbotron mt-5 pl-3">

<h1 class="text-center">Search Result</h1>

<h4>Name : <%=d.getDriverName()%></h4>
<h4>Contact : <%=d.getContact() %></h4>
<h4>License : <%=d.getLicense() %></h4>

<div class="jumbotron">
<h2 class="text-center">Qualifications</h2>
<table class="table table-bordered text-center table-sm">
	<thead class="thead-dark">
		<tr><th>Id</th><th>Qualification Name</th><th>Date Of Expiry</th>
	</thead>
	<%
	Set<Qualification> qual = d.getQualifications();
	for(Qualification q : qual){ %>
	<tr>
		
			<td><%=q.getQid()%></td>
			<td><%=q.getQualification()%></td>
			<td><%=q.getDateOfExpiry() %></td>
		</tr>
	<%} %>
</table>
</div>

<div class="jumbotron">
<h2 class="text-center">Trainings</h2>
<table class="table table-bordered text-center table-sm">
	<thead class="thead-dark">
		<tr><th>Id</th><th>Training Name</th><th>Date Of Expiry</th>
	</thead>
	<%
	Set<Training> train = d.getTrainings();
	for(Training t : train){ %>
	<tr>
		
			<td><%=t.getTid()%></td>
			<td><%=t.getTraining()%></td>
			<td><%=t.getDateOfExpiry()%></td>
		</tr>
	<%} %>
</table>
</div>

<div class="jumbotron">
<h2 class="text-center">Enrolled Training Sessions</h2>
<table class="table table-bordered text-center table-sm">
	<thead class="thead-dark">
		<tr><th>Id</th><th>Training Name</th><th>Session Date</th>
	</thead>
	<%
	Set<TrainingSession> trainSess = d.getTrainingSessions();
	for(TrainingSession t : trainSess){ %>
	<tr>
		
			<td><%=t.getTsId()%></td>
			<td><%=t.getTraining()%></td>
			<td><%=t.getSessionDate()%></td>
		</tr>
	<%} %>
</table>
</div>

</div></div>
</body>
</html>