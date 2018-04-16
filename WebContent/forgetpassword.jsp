<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Forgot Password</title>
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
		<a href="#" class="brand-logo">Library System</a>
		<ul id="nav-mobile" class="right hide-on-med-and-down">
			<li><a href="login.jsp">Login</a></li>
			<li><a href="logout.jsp">Logout</a></li>
			<li><a href="signup.jsp">Sign Up</a></li>
		</ul>
	</div>
	</nav>

	<form name="myForm" action="forgetpassword"
		onsubmit="return validateForm()" method="post">



		<div class="row">
			<div class="input-field col s6">
				<i class="material-icons prefix">account_circle</i> <input
					type="text" id="UserName" name="UserName"
					oninput="setCustomValidity('')"> <label
					for="UserName">UserName</label>


			</div>
		</div>
		
		<button class="btn waves-effect waves-light" type="submit"
			name="action" id="boton">
			Submit <i class="material-icons right">send</i>
		</button>

	</form>

</body>

</html>