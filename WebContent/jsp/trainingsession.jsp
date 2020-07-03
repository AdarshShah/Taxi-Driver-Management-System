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
</head>
<body>
<%
	TrainingSession ts = (TrainingSession)session.getAttribute("trainingSession");
%>
<h1>Training Session</h1>
<h3>Training : <%=ts.getTraining() %></h3>
<h3>Date : <%=ts.getSessionDate() %></h3>
<h1>Candidates</h1>
<table>
	<thead>
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
			<td><button name="function" value="remove">Remove</button></td>
		</form></tr>
	<%} %>
	<tr><th colspan="5">Add Candidates</th></tr>
	<%
		drivers = (Set<Driver>)request.getAttribute("drivers");
		for(Driver driver : drivers){
	%>
		<tr><form method="post" action="/TaxiDriverSystem/TrainingSession">
			<td><input type="hidden" name="did" value="<%=driver.getDriverId() %>"><%=driver.getDriverId() %></td>
			<td><%=driver.getDriverName() %></td><td><%=driver.getLicense() %></td><td><%=driver.getContact() %></td>
			<td><button name="function" value="add">Add</button></td>
		</form></tr>
	<%} %>
	</tbody>
</table>
</body>
</html>