<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html  PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd" PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd" >

<html>
<head>
<%
	if (session != null) {
		if (session.getAttribute("role") != null) {
			String name = (String) session.getAttribute("role");
			if (name.equals("Student")) {
				out.println("you are already logged in");
				response.sendRedirect("main_page_student.jsp");
			} else if (name.equals("TeachingStaff")) {
				out.println("you are already logged in");
				response.sendRedirect("main_page_faculty.jsp");
			} else if (name.equals("Librarian")) {
				out.println("you are already logged in");
				response.sendRedirect("main_page_lib.jsp");
			}
		}
	}
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Library Management System</title>
<!--Import Google Icon Font-->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<!--Import materialize.css-->
<link type="text/css" rel="stylesheet" href="css/materialize.min.css"
	media="screen,projection" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

</head>

<body>

<script type="text/javascript">
function mouseoverPass(obj) {
	  var obj = document.getElementById('password');
	  obj.type = "text";
	}
	function mouseoutPass(obj) {
	  var obj = document.getElementById('password');
	  obj.type = "password";
	}
	</script> 
<script type="text/javascript"
		src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="js/materialize.min.js"></script>

	<nav>
	<div class="nav-wrapper">
		<a href="#" class="brand-logo">Login</a>
		<ul id="nav-mobile" class="right hide-on-med-and-down">
			<li><a href="index.jsp">Home</a></li>
		</ul>
	</div>
	</nav>


	<h2>
		<%
			String login_msg = (String) request.getAttribute("error");
			if (login_msg != null)
				out.println("<font color=red size=4px>" + login_msg + "</font>");
		%>
	</h2>
	<form name="myForm" action="login" method="post"
		onsubmit="return validateForm()" class="col s12">

		<div class="row">
			<div class="input-field col s6">
			  <i class="material-icons prefix">account_circle</i>
				<input type="text" name="name" required="" oninvalid="this.setCustomValidity('Please enter Username')" oninput="setCustomValidity('')">
					 <label for="User Name">User name</label>
			</div>
		</div>


		<div class="row">
			<div class="input-field col s6">
				 <i class="material-icons prefix">lock</i>
						<input type="password" id="password" name="password" required=""	oninvalid="this.setCustomValidity('Please enter Password')"
					oninput="setCustomValidity('')">
					<label for="Password">Password</label>
					  <i class="material-icons prefix" onmouseover="mouseoverPass();" onmouseout="mouseoutPass();">remove_red_eye</i>
					
		 	</div>
		</div>
			
	<button class="btn waves-effect waves-light" type="submit" name="action" id="boton">Submit
    <i class="material-icons right">send</i>
    </button>
					 
					<%-- <input type="submit" value="Submit" id="boton">--%>
				<%
					//Error emission on screen if necessary
					if (request.getAttribute("Error") != null) {
						out.println(request.getAttribute("Error"));
					}
				%>
			
	</form>
	Not member? Click
	 <a href="signup.jsp">REGISTER</a> to create account.
	<br/> Forgot <a href="usernamerecovery.jsp">User Name</a> 
	<a href="forgetpassword.jsp">password</a> 
	

</body>
</html>