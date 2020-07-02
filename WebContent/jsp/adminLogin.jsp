<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Login</title>
</head>
<body>


<h1>Login</h1>

<h3><%=request.getAttribute("error")==null?"":request.getAttribute("error").toString() %></h3>

<form method="post" action="/TaxiDriverSystem/Login">
	Username : <input type="text" name="username"><br/> 
	Password : <input type="password" name="password"><br/>
	<button>Submit</button>
</form>

</body>
</html>