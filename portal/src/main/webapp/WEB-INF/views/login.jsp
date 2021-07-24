<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>


<link rel="stylesheet" type="text/css" href="login.css">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


<style>
body {
	font-family: Arial, Helvetica, sans-serif;
	background-image: url('/images/mdstock.jpg');
	background-size: cover;
}
</style>

</head>

<body>
	<nav class="navbar navbar-light bg-light" style="background-color: #e3f2fd;">
	<ul class="navbar-nav">
		<li class="nav-item"><a class="navbar-brand" href="#"> <img
				src="/images/pharmacy.png"
				width="30" height="30" class="d-inline-block align-top" alt="">
				POD2 Pharmacy
		</a></li>

	</ul>
	</nav>

	<div class="container-fluid">

		<!-- Demo content-->
		<div class="container">
			<div class="row">

				<div class="col-lg-10 col-xl-7 mx-auto">
					<h3 align="center" class="display-4">${loginMessage}</h3>
					<p align="center" class="text-muted mb-4"></p>

					<form:errors path="usercredentials.*" />
					<form:form action="/user/homepage" method="post">
						<div class="form-group mb-3 col-lg-10 col-xl-7">
							<input id="userid" type="text" name="userid"
								placeholder="Username" required="" autofocus=""
								class="form-control  border-0 shadow-sm px-4">
						</div>
						<div class="form-group mb-3 col-lg-10 col-xl-7">
							<input id="password" type="password" name="password"
								placeholder="Password" required=""
								class="form-control  border-0 shadow-sm px-4 text-primary">
						</div>
						<div class="container">
							<button type="submit" name="submit"
								class="col-lg-10 col-xl-7 mb-3  form-group btn btn-primary  ">Login</button>
						</div>
						<div class="text-center d-flex justify-content-between mt-4">
							<p>${errormsg}</p>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>