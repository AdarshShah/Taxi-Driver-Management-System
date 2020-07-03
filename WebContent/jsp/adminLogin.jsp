<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Login</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
</head>
<body>

<div class="container mx-auto">
<div class="jumbotron mt-5 w-50">
<h1 class="text-center">Login</h1>

<%=request.getAttribute("error")==null?"":"<div class=\"alert alert-primary\">"+request.getAttribute("error").toString()+"</div>" %>

<form method="post" action="/TaxiDriverSystem/Login">
	<div class="form-group">
	<label for="username">Username</label> <input class="form-control" id="username" type="text" name="username"><br/>
	</div> 
	<div class="form-group">
	<label for="password">Password</label><input class="form-control" id="password" type="password" name="password"><br/>
	<button class="btn btn-primary">Login</button>
	</div>
</form>
</div>
</div>
</body>
</html>