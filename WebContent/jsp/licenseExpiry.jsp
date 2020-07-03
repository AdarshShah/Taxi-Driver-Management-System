<%@page import="com.bean.Training"%>
<%@page import="com.bean.Qualification"%>
<%@page import="java.util.Set"%>
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
	Set<Qualification> qual = (Set<Qualification>)request.getAttribute("qualifications");
	Set<Training> train = (Set<Training>)request.getAttribute("trainings");
%>
<div class="container">
<div class="jumbotron mt-5 pl-3">
<h1 class="text-center">Candidate Information</h1>

<div class="jumbotron">
<h2 class="text-center">Expiring Qualification</h2>
<table class="table table-bordered text-center table-sm">
	<thead class="thead-dark">
		<tr><th>Id</th><th>Driver Name</th><th>Qualification</th><th>Expiry Date</th>
	</thead>
	<%
	for(Qualification q : qual){ %>
	<tr>
		
			<td><%=q.getDriver().getDriverId()%></td>
			<td><%=q.getDriver().getDriverName()%></td>
			<td><%=q.getQualification()%></td>
			<td><%=q.getDateOfExpiry()%></td>
		</tr>
	<%} %>
</table>
</div>


<div class="jumbotron">
<h2 class="text-center">Expiring Trainings</h2>
<table class="table table-bordered text-center table-sm">
	<thead class="thead-dark">
		<tr><th>Id</th><th>Driver Name</th><th>Training</th><th>Expiry Date</th>
	</thead>
	<%
	for(Training t: train){ %>
	<tr>
		
			<td><%=t.getDriver().getDriverId()%></td>
			<td><%=t.getDriver().getDriverName()%></td>
			<td><%=t.getTraining()%></td>
			<td><%=t.getDateOfExpiry()%></td>
		</tr>
	<%} %>
</table>
</div>

</div>
</div>
</body>
</html>