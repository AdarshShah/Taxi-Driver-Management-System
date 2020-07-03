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
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
</head>
<body>
<%
	Driver d = (Driver)request.getAttribute("driver");
	Set<Qualification> qual = d.getQualifications();
	DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
%>
<div class="container">
<div class="jumbotron mt-5 pl-3">
<h1 class="text-center">Qualifications</h1>
<%=request.getAttribute("error")==null?"":"<div class=\"alert alert-primary\">"+request.getAttribute("error").toString()+"</div>" %>
<h4>Name : <%=d.getDriverName()%></h4>
<h4>Contact : <%=d.getContact() %></h4>
<h4>License : <%=d.getLicense() %></h4>

<table class="table table-bordered text-center table-sm">
	<thead class="thead-dark">
		<tr><th>Qualification Id</th><th>Qualification Name</th><th>Date Of Expiry</th><th colspan="2">Operations</th></tr>
	</thead>
	<%for(Qualification q : qual){ %>
		<tr>
		<form method="post" action="/TaxiDriverSystem/Qualification">
			<td><%=q.getQid()%><input type="hidden" name="qid" value="<%=q.getQid()%>"></td>
			<td><select class="form-control"  name="type" required>
					<option value="Central_London" <%=q.getQualification()==enumQualification.Central_London?"selected":"" %> >Central London</option>
					<option value="North_London" <%=q.getQualification()==enumQualification.North_London?"selected":"" %>>North London</option>
					<option value="East_London" <%=q.getQualification()==enumQualification.East_London?"selected":"" %>>East London</option>
					<option value="South_London" <%=q.getQualification()==enumQualification.South_London?"selected":"" %>>South London</option>
					<option value="West_London" <%=q.getQualification()==enumQualification.West_London?"selected":"" %>>West London</option>
				</select>
			</td>
			<td><input class="form-control"  type="date" name="expirydate" value="<%=df.format(q.getDateOfExpiry())%>" required></td>
			<td><button class="btn btn-primary" name="qfunc" value="update">Update</button></td>
			<td><button  class="btn btn-primary" name="qfunc" value="delete">Delete</button></td>
		</form>
		</tr>
	<%} %>
	<form method="post" action="/TaxiDriverSystem/Qualification">
			<td></td>
			<td><select class="form-control" name="type" required>
					<option value="Central_London">Central London</option>
					<option value="North_London" >North London</option>
					<option value="East_London" >East London</option>
					<option value="South_London" >South London</option>
					<option value="West_London" >West London</option>
				</select>
			</td>
			<td><input type="date" class="form-control" name="expirydate" required></td>
			<td colspan="2"><button  class="btn btn-primary" name="qfunc" value="add">Add</button></td>
		</form>
</table>

</body>
</html>