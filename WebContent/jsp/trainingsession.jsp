<%@page import="com.bean.Driver"%>
<%@page import="java.util.Set"%>
<%@page import="com.bean.TrainingSession"%>
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
	TrainingSession ts = (TrainingSession)session.getAttribute("trainingSession");
%>
<div class="container">
<div class="jumbotron mt-5 pl-3">
<h1 class="text-center">Training Session</h1>
<%=request.getAttribute("error")==null?"":"<div class=\"alert alert-primary\">"+request.getAttribute("error").toString()+"</div>" %>
<h3>Training : <%=ts.getTraining() %></h3>
<h3>Date : <%=ts.getSessionDate() %></h3>
<h1>Candidates</h1>
<table class="table table-bordered text-center table-sm">
	<thead class="thead-dark">
		<tr>
			<th>Driver Id</th><th>Driver Name</th><th>License</th><th>Contact</th><th>Operations</th>
		</tr>
	</thead>
	<tbody>
	<%
		Set<Driver> drivers = ts.getCandidates();
		for(Driver driver : drivers){
	%>
		<tr><form method="post" action="/TaxiDriverSystem/TrainingSession">
			<td><input type="hidden" name="did" value="<%=driver.getDriverId() %>"><%=driver.getDriverId() %></td>
			<td><%=driver.getDriverName() %></td><td><%=driver.getLicense() %></td><td><%=driver.getContact() %></td>
			<td><button  class="btn btn-primary"  name="function" value="remove">Remove</button></td>
		</form></tr>
	<%} %>
	
	<thead class="thead-dark">
	<tr><th colspan="5">Add Candidates</th></tr>
	</thead>	
	<%
		drivers = (Set<Driver>)request.getAttribute("drivers");
		for(Driver driver : drivers){
	%>
		<tr><form method="post" action="/TaxiDriverSystem/TrainingSession">
			<td><input type="hidden" name="did" value="<%=driver.getDriverId() %>"><%=driver.getDriverId() %></td>
			<td><%=driver.getDriverName() %></td><td><%=driver.getLicense() %></td><td><%=driver.getContact() %></td>
			<td><button class="btn btn-primary"  name="function" value="add">Add</button></td>
		</form></tr>
	<%} %>
	</tbody>
</table>
</body>
</html>