<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html  PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd" PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd" >

<html>
<head>
<%
	if (session != null) {
		if (session.getAttribute("name") != null) {
			String name = (String) session.getAttribute("name");
			out.println("you are already logged in");
			response.sendRedirect("successlogin.jsp");

		}

	}
%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SignUp</title>

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

	<script type="text/javascript">
	$(document).ready(function() {
	    $('select').material_select();
	  });
function mouseoverPass(obj) {
	  var obj = document.getElementById('password');
	  obj.type = "text";
	  
	}
	function mouseoutPass(obj) {
	  var obj = document.getElementById('password');
	  obj.type = "password";
	  
	
	}
	
	function chk_pass_function(obj){
		var mediumRegex = new RegExp("^(((?=.*[a-z])(?=.*[A-Z]))|((?=.*[a-z])(?=.*[0-9]))|((?=.*[A-Z])(?=.*[0-9])))(?=.{6,})");
		var x=obj.value;
		if(mediumRegex.test(x)){
			document.getElementById("demo1").innerHTML ="Strong Password";
		}else{
			document.getElementById("demo1").innerHTML ="Weak Password Please Renter";
		}
	}
	
	function chk_function(obj) {
		var obj1=document.getElementById("password");		  
		
		
		  
		  if(obj.value == obj1.value)
			  {
			  document.getElementById("demo").innerHTML ="Passwords Matched";
			  
			  }
		  else{
			  document.getElementById("demo").innerHTML ="Passwords Do not Match";  
		  		addClass(obj,"invalid");		
		  }
		}
	
	
		</script>
<%--  Navigation Bar --%>
	<nav>
	<div class="nav-wrapper">
		<a href="#" class="brand-logo">SignUp</a>
		<ul id="nav-mobile" class="right hide-on-med-and-down">
			<li><a href="index.jsp">Home</a></li>
			<li><a href="login.jsp">Login</a></li>

		</ul>
	</div>
	</nav>

<%--  Header-2 --%>

	<h2>
		<%
			String login_msg = (String) request.getAttribute("error");
			if (login_msg != null)
				out.println("<font color=red size=4px>" + login_msg + "</font>");
		%>
	</h2>
	
	<%--  Form Begin --%>
	
	<form name="myForm" action="signup" method="post"
		onsubmit="return validateForm()">

		<div class="row">
			<div class="col s12">
				<div class="row">
					<div class="input-field col s6">
						<input type="text" name="fname" required=""
							oninvalid="this.setCustomValidity('Please enter First Name')"
							oninput="setCustomValidity('')"> <label for="fname">First
							Name</label>
					</div>
					<div class="input-field col s6">
						<input type="text" name="lname" required=""
							oninvalid="this.setCustomValidity('Please enter Last Name')"
							oninput="setCustomValidity('')"> <label for="lname">Last
							Name</label>
					</div>
				</div>
			</div>
		</div>


		<div class="row">
			<div class="input-field col s8">
				<input type="text" name="name" required=""
					oninvalid="this.setCustomValidity('Please enter Username')"
					oninput="setCustomValidity('')"> <label for="name">User
					Name</label>

			</div>
		</div>

		<div class="row">
		<div class="col s12">
			<div class="input-field col s5">
				<input type="password" id="password" name="password" required=""
					oninvalid="this.setCustomValidity('Please enter Password')"
					oninput="chk_pass_function(this);">
					 <label	for="password">Password</label> 
					<i class="material-icons prefix" onmouseover="mouseoverPass();" onmouseout="mouseoutPass();">remove_red_eye</i>
			</div>
			
			<div class="col s8">
			<p> <b>	Passwords must contain: Upper and Lower case Letter,Number and Spl Charc.</p>
			</div>
		</div>
		
		</div>
		
		<p id="demo1"></p>
		<div class="row">
			<div class="input-field col s8">
				<input type="password" id="cnfpassword" name="cnfpassword"
					required=""	oninvalid="this.setCustomValidity('Please reenter Password')"
					onblur="chk_function(this);">
					 <label	data-error="Password not match" data-success="Password Match" for="cnfpassword">Confirm Password</label>
			</div>
		</div>

		<p id="demo"></p>





		<div class="row">
			<div class="input-field col s8">
				<input name="email" id="email" type="email" class="validate"
					oninvalid="this.setCustomValidity('Please enter Email')"
					oninput="setCustomValidity('')"> <label for="email"
					data-error="wrong" data-success="right">Email</label>
			</div>
		</div>



		<p id="demo"></p>
		
		<div class="input-field col s6">
    <select name="role">
      <option value="" disabled selected>Choose your Role</option>
    	<option value="Student">Student</option>
		<option value="TeachingStaff">Teaching Staff</option>
		<option value="Librarian">Librarian</option>
	 </select>
    	<label>Role</label>
  	</div>

		<button class="btn waves-effect waves-light" type="submit"
			name="action" id="boton" onclick="chk_function()">
			Submit <i class="material-icons right">send</i>
		</button>


	</form>
	

						<%--
				//Error emission on screen if necessary
								if (request.getAttribute("Error") != null) {
									out.println(request.getAttribute("Error"));
								}
							--%>

	
	</body>

</html>