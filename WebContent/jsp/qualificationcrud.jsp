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
	Set<Qualification> qual = d.getQualifications();
	DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
%>
<h1>Qualifications</h1>
<h4>Name : <%=d.getDriverName()%></h4>
<h4>Contact : <%=d.getContact() %></h4>
<h4>License : <%=d.getLicense() %></h4>

<table>
	<thead>
		<tr><th>Qualification Id</th><th>Qualification Name</th><th>Date Of Expiry</th><th colspan="2">Operations</th></tr>
	</thead>
	<%for(Qualification q : qual){ %>
		<tr>
		<form method="post" action="/TaxiDriverSystem/Qualification">
			<td><input type="text" value="<%=q.getQid()%>" disabled="disabled"><input type="hidden" name="qid" value="<%=q.getQid()%>"></td>
			<td><select name="type" required>
					<option value="Central_London" <%=q.getQualification()==enumQualification.Central_London?"selected":"" %> >Central London</option>
					<option value="North_London" <%=q.getQualification()==enumQualification.North_London?"selected":"" %>>North London</option>
					<option value="East_London" <%=q.getQualification()==enumQualification.East_London?"selected":"" %>>East London</option>
					<option value="South_London" <%=q.getQualification()==enumQualification.South_London?"selected":"" %>>South London</option>
					<option value="West_London" <%=q.getQualification()==enumQualification.West_London?"selected":"" %>>West London</option>
				</select>
			</td>
			<td><input type="date" name="expirydate" value="<%=df.format(q.getDateOfExpiry())%>" required></td>
			<td><button name="qfunc" value="update">update</button></td>
			<td><button name="qfunc" value="delete">delete</button></td>
		</form>
		</tr>
	<%} %>
	<form method="post" action="/TaxiDriverSystem/Qualification">
			<td></td>
			<td><select name="type" required>
					<option value="Central_London">Central London</option>
					<option value="North_London" >North London</option>
					<option value="East_London" >East London</option>
					<option value="South_London" >South London</option>
					<option value="West_London" >West London</option>
				</select>
			</td>
			<td><input type="date" name="expirydate" required></td>
			<td colspan="2"><button name="qfunc" value="add">add</button></td>
		</form>
</table>

</body>
</html>