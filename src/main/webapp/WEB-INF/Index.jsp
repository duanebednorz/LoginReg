<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
<link rel="stylesheet"
	href="/webjars/bootstrap/4.5.0/css/bootstrap.min.css" />
<script src="/webjars/jquery/3.5.1/jquery.min.js"></script>
<script src="/webjars/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="jumbotron">
			<h1>Dashboard</h1>
		</div>
		<div>
			<a href="/new">New Something</a> | <a href="/dashboard">Dashboard</a>
		</div>
		<hr>
		<h1>Login</h1>
		<p>
			<c:out value="${error}" />
		</p>
		<div class="row">
			<div class="col-sm-4">
				<form method="post" action="/login">
					<div class="form-group">
						<label for="email">Email</label> <input type="text" id="email"
							name="email" class="form-control" />
					</div>
					<div class="form-group">
						<label for="password">Password</label> <input type="password"
							id="password" name="password" class="form-control" />
					</div>
					<input type="submit" value="Login!" class="btn btn-primary" />
				</form>
			</div>
		</div>
		<hr>
		<h1>Register!</h1>
		<p>
			<form:errors path="user.*" class="text-danger" />
		</p>
		<div class="row">
			<div class="col-sm-4">
				<form:form method="POST" action="/registration"
					modelAttribute="user">
					<div class="form-group">
						<form:label path="first_name">First Name:</form:label>
						<form:input type="text" path="first_name" class="form-control" />
						<form:errors path="first_name" class="text-danger" />
					</div>
					<div class="form-group">
						<form:label path="last_name">Last Name:</form:label>
						<form:input type="text" path="last_name" class="form-control" />
						<form:errors path="last_name" class="text-danger" />
					</div>
					<div class="form-group">
						<form:label path="email">Email:</form:label>
						<form:input type="email" path="email" class="form-control" />
						<form:errors path="email" class="text-danger" />
					</div>
					<div class="form-group">
						<form:label path="password">Password:</form:label>
						<form:password path="password" class="form-control" />
						<form:errors path="password" class="text-danger" />
					</div>
					<div class="form-group">
						<form:label path="passwordConfirmation">Password Confirmation:</form:label>
						<form:password path="passwordConfirmation" class="form-control" />
						<form:errors path="passwordConfirmation" class="text-danger" />
					</div>
					<input type="submit" value="Register!" class="btn btn-primary" />
				</form:form>
			</div>
		</div>
		
	</div>
</body>
</html>