<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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

<title>Online Library</title>
<style>
body {
	background-image: url("background.jpg");
	background-repeat: no-repeat;
	background-size: cover;
}
</style>

<!--Import Google Icon Font-->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<!--Import materialize.css-->
<link type="text/css" rel="stylesheet" href="css/materialize.min.css"
	media="screen,projection" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />


</head>




<body background="background.jpg">
	<script type="text/javascript"
		src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="js/materialize.min.js"></script>
	<nav>
	<div class="nav-wrapper">
		<a href="#" class="brand-logo">Library System</a>
		<ul id="nav-mobile" class="right hide-on-med-and-down">
			<li><a href="login.jsp">Login</a></li>
			
			<li><a href="signup.jsp">Sign Up</a></li>
		</ul>
	</div>
	</nav>


</body>
<%-- <footer class="page-footer">
          <div class="container">
            <div class="row">
              <div class="col l6 s12">
                <h5 class="white-text">Library Management System</h5>
                <p class="grey-text text-lighten-4">This Application was developed by Team-1.</p>
              </div>
              <div class="col l4 offset-l2 s12">
                <h5 class="white-text">Links</h5>
                <ul>
                  <li><a class="grey-text text-lighten-3" href="login.jsp">Login</a></li>
                  <li><a class="grey-text text-lighten-3" href="logout.jsp">Logout</a></li>
                  <li><a class="grey-text text-lighten-3" href="signup.jsp">Sign Up</a></li>
                  
                </ul>
              </div>
            </div>
          </div>
          
     </footer>--%>
</html>