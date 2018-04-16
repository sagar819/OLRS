<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">



<title>Library</title>


<!--Import Google Icon Font-->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<!--Import materialize.css-->
<link type="text/css" rel="stylesheet" href="css/materialize.min.css"
	media="screen,projection" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

</head>




<body>

	<script type="text/javascript"
		src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="js/materialize.min.js"></script>
	<nav>
	<div class="nav-wrapper">
		<a href="#" class="brand-logo">SignUp</a>
		<ul id="nav-mobile" class="right hide-on-med-and-down">
			<li><a href="index.jsp">Home</a></li>
			<li><a href="login.jsp">Login</a></li>

		</ul>
	</div>
	</nav>

	<form name="myForm" action="submitreenterpassword"
		onsubmit="return validateForm()" method="post">


		<input type="hidden" name="id"
			value="<%=request.getAttribute("error")%>"><br>


		<div class="row">
			<div class="col s12">
				<div class="row">
					<div class="input-field col s6">
						<input type="Password" id="Password" name="Password"
							oninvalid="this.setCustomValidity('Please enter First Name')">


						<label for="Password">Password</label>
					</div>
					<div class="input-field col s6">
						<input type="Password" id="Password1" name="Password1"
							oninvalid="this.setCustomValidity('Please enter Password')">
						<label for="Password1">Password</label>
					</div>
				</div>
			</div>
		</div>









		<button class="btn waves-effect waves-light" type="submit"
			name="action" id="boton" onclick="chk_function()">
			Submit <i class="material-icons right">send</i>
		</button>
	</form>
</body>
</html>