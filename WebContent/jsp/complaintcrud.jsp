<%@page import="com.enumeration.enumDisciplinaryRecord"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.bean.DisciplinaryRecord"%>
<%@page import="java.util.Set"%>
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
	Set<DisciplinaryRecord> complaints = d.getDisciplinaryRecords();
	DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
%>
	<div class="container">
		<div class="jumbotron mt-5 pl-3">
			<h1 class="text-center">Disciplinary Records</h1>
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
						<th>Id</th>
						<th>Complaint</th>
						<th>Date</th>
						<th colspan="2">Operations</th>
					</tr>
				</thead>
				<%for(DisciplinaryRecord t : complaints){ %>
				<tr>
					<form method="post" action="/TaxiDriverSystem/Complaint">
						<td><%=t.getDrId()%><input type="hidden" name="drid"
							value="<%=t.getDrId()%>"></td>
						<td><select class="form-control" name="type" required>
								<%for(enumDisciplinaryRecord et : enumDisciplinaryRecord.values()){ %>
								<option value="<%=et.toString() %>"
									<%=t.getDisciplinaryRecord()==et?"selected":"" %>><%=et.toString() %></option>
								<%} %>
						</select></td>
						<td><input class="form-control" type="date" name="date"
							value="<%=df.format(t.getDate())%>" required></td>
						<td><button class="btn btn-primary" name="tfunc"
								value="update">update</button></td>
						<td><button class="btn btn-primary" name="tfunc"
								value="delete">delete</button></td>
					</form>
				</tr>
				<%} %>
				<form method="post" action="/TaxiDriverSystem/Complaint">
					<td></td>
					<td><select class="form-control" name="type" required>
							<%for(enumDisciplinaryRecord et : enumDisciplinaryRecord.values()){ %>
							<option value="<%=et.toString() %>"><%=et.toString() %></option>
							<%} %>
					</select></td>
					<td><input class="form-control" type="date" name="date"
						required></td>
					<td colspan="2"><button class="btn btn-primary" name="tfunc"
							value="add">add</button></td>
				</form>
			</table>
</body>
</html>