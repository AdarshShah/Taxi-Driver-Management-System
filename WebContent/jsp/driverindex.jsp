<%@page import="java.util.HashSet"%>
<%@page import="com.enumeration.enumTraining"%>
<%@page import="com.bean.TrainingSession"%>
<%@page import="java.util.Set"%>
<%@page import="com.bean.Driver"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Drivers Management System</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
</head>
<body>
<div class="jumbotron">
<h1>Taxi Driver Management System</h1>
<div class="row">
	<form class="form-inline col-6 mx-auto" method="post" action="/TaxiDriverSystem/DriverCRUD">
	<input class="form-control mr-2" type="text" name="license" placeholder="License No" required="required">
	<button class="btn btn-primary" name="function" value="search">Search</button>
	</form>
	<form class="form-inline col-6" method="post" action="/TaxiDriverSystem/DriverCRUD">
	<button class="btn btn-primary" name="function" value="expire">License Expiring Candidates</button>
	</form>
</div>

</div>
<%=request.getAttribute("error")==null?"":"<div class=\"alert alert-primary\">"+request.getAttribute("error").toString()+"</div>" %>
<div class="container">
<div class="jumbotron mt-5 pl-3">
<h1 class="text-center">Driver Information</h1>
<table class="table table-bordered text-center table-sm">
	<thead class="thead-dark">
		<tr><th>Id</th><th>Driver Name</th><th>License</th><th>Contact</th><th colspan="4">Operations</th></tr>
	</thead>
	<%
		Set<Driver> drivers = (HashSet<Driver>)request.getAttribute("drivers");
		for(Driver d : drivers){
			%>
		<tr>
		<form method="post" action="/TaxiDriverSystem/DriverCRUD">
			<td><%=d.getDriverId() %><input type="hidden" name="driverId" value="<%=d.getDriverId() %>"></td>
			<td><input type="text" class="form-control" name="name" value="<%=d.getDriverName() %>" required="required"/></td>
			<td><input type="text" class="form-control" name="license" value="<%=d.getLicense() %>" required="required"/></td>
			<td><input type="text" class="form-control" name="contact" value="<%=d.getContact() %>" required="required"/></td>
			<td><button class="btn btn-primary" name="function" value="update">Update</button></td>
			<td><button class="btn btn-primary" name="function" value="delete">Delete</button></td>
			<td><button class="btn btn-primary" name="function" value="qualification">Qualifications</button></td>
			<td><button class="btn btn-primary" name="function" value="training">Trainings</button></td>
		</form>	
		</tr>
			<%
		}
	%>
	<thead class="thead-dark text-center"><th colspan="8">Add a new Driver</th></thead>
	<tr>
		<form method="post" action="/TaxiDriverSystem/DriverCRUD">
			<td></td>
			<td><input type="text" class="form-control" name="name" value="" required/></td>
			<td><input type="text" class="form-control" name="license" value="" required/></td>
			<td><input type="text" class="form-control" name="contact" value="" required/></td>
			<td colspan="4"><button class="btn btn-primary" name="function" value="add">Add</button></td>
		</form>	
		</tr>
</table>
</div>
<div class="jumbotron mt-5 pl-3">
<h1 class="text-center">Upcoming Training Sessions</h1>
<table class="table table-bordered text-center table-sm">
	<thead class="thead-dark">
		<tr>
			<th>Id</th>
			<th>Training</th>
			<th>Date</th>
			<th colspan="2">Operations</th>
		</tr>
	</thead>
	<%
		Set<TrainingSession> trainingSessions = (HashSet<TrainingSession>)request.getAttribute("trainingSessions");
		for(TrainingSession ts : trainingSessions){
	%>
		<tr><form method="post" action="/TaxiDriverSystem/DriverCRUD">
			<td><%=ts.getTsId() %></td>
			<td><%=ts.getTraining() %></td>
			<td><%=ts.getSessionDate() %></td>
			<td><input type="hidden" name="tsid" value="<%=ts.getTsId() %>"><button class="btn btn-primary" name="tsfunc" value="delete">Delete</button></td>
			<td><button class="btn btn-primary"  name="tsfunc" value="manage">Manage</button></td>
		</form></tr>
	<%} %>
	<thead class="thead-dark">
	<tr>
		<th colspan="5">Create Training Session</th>
	</tr>
	</thead>
	<tr>
	<form method="post" action="/TaxiDriverSystem/DriverCRUD">
		<td></td>
		<td><select class="form-control" name="type" required>
			<%for(enumTraining et : enumTraining.values()){ %>
				<option value="<%=et.toString() %>"><%=et.toString() %></option>
			<%} %>
		</select></td>
		<td><input type="date" class="form-control" name="sessiondate" required></td>
		<td colspan="2"><button class="btn btn-primary" name="tsfunc" value="add">Add</button></td>
	</form>
	</tr>
</table>
</div>
</div>
</body>
</html>