<%@page import="com.enumeration.enumTraining"%>
<%@page import="com.bean.Training"%>
<%@page import="java.util.Set"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.enumeration.enumQualification"%>
<%@page import="com.bean.Qualification"%>
<%@page import="com.bean.Driver"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
</head>
<body>
	<%
	Driver d = (Driver)request.getAttribute("driver");
	Set<Training> train = d.getTrainings();
	DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
%>
	<div class="container">
		<div class="jumbotron mt-5 pl-3">
			<h1 class="text-center">Trainings</h1>
			<%=request.getAttribute("error")==null?"":"<div class=\"alert alert-primary\">"+request.getAttribute("error").toString()+"</div>" %>
			<h4>
				Name :
				<%=d.getDriverName()%></h4>
			<h4>
				Contact :
				<%=d.getContact() %></h4>
			<h4>
				License :
				<%=d.getLicense() %></h4>

			<table class="table table-bordered text-center table-sm">
				<thead class="thead-dark">
					<tr>
						<th>Training Id</th>
						<th>Training Name</th>
						<th>Date Of Expiry</th>
						<th colspan="2">Operations</th>
					</tr>
				</thead>
				<%for(Training t : train){ %>
				<tr>
					<form method="post" action="/TaxiDriverSystem/Training">
						<td><%=t.getTid()%><input type="hidden" name="tid"
							value="<%=t.getTid()%>"></td>
						<td><select class="form-control" name="type" required>
								<%for(enumTraining et : enumTraining.values()){ %>
								<option value="<%=et.toString() %>"
									<%=t.getTraining()==et?"selected":"" %>><%=et.toString() %></option>
								<%} %>
						</select></td>
						<td><input class="form-control" type="date" name="expirydate"
							value="<%=df.format(t.getDateOfExpiry())%>" required></td>
						<td><button class="btn btn-primary" name="tfunc"
								value="update">update</button></td>
						<td><button class="btn btn-primary" name="tfunc"
								value="delete">delete</button></td>
					</form>
				</tr>
				<%} %>
				<form method="post" action="/TaxiDriverSystem/Training">
					<td></td>
					<td><select class="form-control" name="type" required>
							<%for(enumTraining et : enumTraining.values()){ %>
							<option value="<%=et.toString() %>"><%=et.toString() %></option>
							<%} %>
					</select></td>
					<td><input class="form-control" type="date" name="expirydate"
						required></td>
					<td colspan="2"><button class="btn btn-primary" name="tfunc"
							value="add">add</button></td>
				</form>
			</table>
</body>
</html>