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
</head>
<body>
<%
	Driver d = (Driver)request.getAttribute("driver");
	Set<Training> train = d.getTrainings();
	DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
%>
<h1>Trainings</h1>
<h4>Name : <%=d.getDriverName()%></h4>
<h4>Contact : <%=d.getContact() %></h4>
<h4>License : <%=d.getLicense() %></h4>

<table>
	<thead>
		<tr><th>Training Id</th><th>Training Name</th><th>Date Of Expiry</th><th colspan="2">Operations</th></tr>
	</thead>
	<%for(Training t : train){ %>
		<tr>
		<form method="post" action="/TaxiDriverSystem/Training">
			<td><input type="text" value="<%=t.getTid()%>" disabled="disabled"><input type="hidden" name="tid" value="<%=t.getTid()%>"></td>
			<td><select name="type" required>
			<%for(enumTraining et : enumTraining.values()){ %>
				<option value="<%=et.toString() %>" <%=t.getTraining()==et?"selected":"" %> ><%=et.toString() %></option>
			<%} %>
				</select>
			</td>
			<td><input type="date" name="expirydate" value="<%=df.format(t.getDateOfExpiry())%>" required></td>
			<td><button name="tfunc" value="update">update</button></td>
			<td><button name="tfunc" value="delete">delete</button></td>
		</form>
		</tr>
	<%} %>
	<form method="post" action="/TaxiDriverSystem/Training">
			<td></td>
			<td><select name="type" required>
			<%for(enumTraining et : enumTraining.values()){ %>
				<option value="<%=et.toString() %>" ><%=et.toString() %></option>
			<%} %>
				</select>
			</td>
			<td><input type="date" name="expirydate" required></td>
			<td colspan="2"><button name="tfunc" value="add">add</button></td>
		</form>
</table>

</body>
</html>