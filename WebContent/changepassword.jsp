<%@page import="com.model.user"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html  PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd" PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd" >

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">



<title>Change Password</title>
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
	function mouseoverPass(obj) {
		  var obj = document.getElementById('password');
		  obj.type = "text";
		  
		}
		function mouseoutPass(obj) {
		  var obj = document.getElementById('password');
		  obj.type = "password";
		  
		
		}
		function mouseoverPass1(obj) {
			  var obj = document.getElementById('password1');
			  obj.type = "text";
			  
			}
			function mouseoutPass1(obj) {
			  var obj = document.getElementById('password1');
			  obj.type = "password";
			  
			
			}
	function chk_function(obj) {
		var obj1=document.getElementById("password1");		  
		
		
		  
		  if(obj.value == obj1.value)
			  {
			  document.getElementById("demo").innerHTML ="Passwords Matched";
			  }
		  else{
			  document.getElementById("demo").innerHTML ="Passwords Do not Match";  
		  		}
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
	
	
	</script>
	
  
  <nav>
    <div class="nav-wrapper">
      <a href="#" class="brand-logo">Library System</a>
      <ul id="nav-mobile" class="right hide-on-med-and-down">
       </ul>
    </div>
  </nav>


	<center>
	
		<h2><%
						//Error emission on screen if necessary
						if (request.getAttribute("Error") != null) {
							out.println(request.getAttribute("Error"));
						}
					%></h2>
	
		<form name="myFormchange" action="changepassword" method="post"
			onsubmit="return validateForm()" class="col s12">
	
	<% user u=new user();%>
	<div class="row">
       
          <input type="hidden" id="name" name="name" value=<%=u.getUsername() %>>
		 <div class="input-field col s4">
          
					<input type="password" id="password" name="password"
						 required=""
						oninvalid="this.setCustomValidity('Please enter Old Password')"
						oninput="setCustomValidity('')">
					
          <label for="password">Password</label>
          <i class="material-icons prefix" onmouseover="mouseoverPass();" onmouseout="mouseoutPass();">remove_red_eye</i>
        </div>
      </div>
				
				<div class="row">
						
        <div class="input-field col s4">
          		<input type="password" id="password1" name="password1"
						 required=""
						oninvalid="this.setCustomValidity('Please enter New Password')"
						>
			
          <label for="password1">New Password</label>
          <i class="material-icons prefix" onmouseover="mouseoverPass1();" onmouseout="mouseoutPass1();">remove_red_eye</i>
        </div>
        </div>
        		<div class="row">
        <div class="input-field col s4">
          
					<input type="password"  id="password2"  name="password2"
						required=""
						oninvalid="this.setCustomValidity('Please enter New Password')"
						onblur="chk_function(this);">
					
          <label for="password2">Confirm New Password</label>
        </div>
      </div>
    		
      <p id="demo"></p>
					
			
			<button class="btn waves-effect waves-light" type="submit" name="action" id="boton">Submit
    <i class="material-icons right">send</i>
    </button>
								
					
				
		</form>
	</center>
</body>

</html>