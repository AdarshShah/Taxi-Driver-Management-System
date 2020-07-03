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
</head>
<body>
<h1>Drivers Management System</h1>
<h3><%=request.getAttribute("error")==null?"":request.getAttribute("error").toString() %></h3>
<table>
	<thead>
		<tr><th>Driver Id</th><th>Driver Name</th><th>License</th><th>Contact</th><th colspan="4">Operations</th></tr>
	</thead>
	<%
		Set<Driver> drivers = new HashSet<Driver>((List<Driver>)request.getAttribute("drivers"));
		for(Driver d : drivers){
			%>
		<tr>
		<form method="post" action="/TaxiDriverSystem/DriverCRUD">
			<td><input type="text" value="<%=d.getDriverId() %>" disabled="disabled"/><input type="hidden" name="driverId" value="<%=d.getDriverId() %>"></td>
			<td><input type="text" name="name" value="<%=d.getDriverName() %>" required="required"/></td>
			<td><input type="text" name="license" value="<%=d.getLicense() %>" required="required"/></td>
			<td><input type="text" name="contact" value="<%=d.getContact() %>" required="required"/></td>
			<td><button name="function" value="update">Update</button></td>
			<td><button name="function" value="delete">Delete</button></td>
			<td><button name="function" value="qualification">Qualifications</button></td>
			<td><button name="function" value="training">Trainings</button></td>
		</form>	
		</tr>
			<%
		}
	%>
	<tr><th colspan="8">Add a new Driver</th></tr>
	<tr>
		<form method="post" action="/TaxiDriverSystem/DriverCRUD">
			<td></td>
			<td><input type="text" name="name" value="" required/></td>
			<td><input type="text" name="license" value="" required/></td>
			<td><input type="text" name="contact" value="" required/></td>
			<td rowspan="4"><button name="function" value="add">Add</button></td>
		</form>	
		</tr>
</table>
<br>
<h1>Upcoming Training Sessions</h1>
<table>
	<thead>
		<tr>
			<th>Session id</th>
			<th>Training</th>
			<th>Date</th>
			<th>Operations</th>
		</tr>
	</thead>
	<%
		Set<TrainingSession> trainingSessions = new HashSet<TrainingSession>((List<TrainingSession>)request.getAttribute("trainingSessions"));
		for(TrainingSession ts : trainingSessions){
	%>
		<tr><form method="post" action="/TaxiDriverSystem/DriverCRUD">
			<td><%=ts.getTsId() %></td>
			<td><%=ts.getTraining() %></td>
			<td><%=ts.getSessionDate() %></td>
			<td><input type="hidden" name="tsid" value="<%=ts.getTsId() %>"><button name="tsfunc" value="delete">Delete</button></td>
			<td><button name="tsfunc" value="manage">Manage</button></td>
		</form></tr>
	<%} %>
	<tr>
		<th colspan="4">Create Training Session</th>
	</tr>
	<tr>
	<form method="post" action="/TaxiDriverSystem/DriverCRUD">
		<td></td>
		<td><select name="type" required>
			<%for(enumTraining et : enumTraining.values()){ %>
				<option value="<%=et.toString() %>"><%=et.toString() %></option>
			<%} %>
		</select></td>
		<td><input type="date" name="sessiondate" required></td>
		<td colspan="2"><button name="tsfunc" value="add">Add</button></td>
	</form>
	</tr>
</table>
</body>
</html>