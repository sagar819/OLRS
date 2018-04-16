<%@page import="com.model.user"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%
		if (session != null) {
			if (session.getAttribute("role") != null) {
				String name = (String) session.getAttribute("role");
				if (name.equals("Student")) {
					out.println("you are already logged in");
					response.sendRedirect("index.jsp");
				} else if (name.equals("TeachingStaff")) {
					out.println("you are already logged in");
					response.sendRedirect("index.jsp");
			} 
		}else {
			response.sendRedirect("index.jsp");
		}
		}
	%>	
<!--Import Google Icon Font-->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<!--Import materialize.css-->
<link type="text/css" rel="stylesheet" href="css/materialize.min.css"
	media="screen,projection" />


<meta name="viewport" content="width=device-width, initial-scale=1.0" />
</head>

<nav>
	<div class="nav-wrapper">
		<a href="#" class="brand-logo">Library</a>
		<ul id="nav-mobile" class="right hide-on-med-and-down">
			<li><a href="addbook.jsp">Add</a>add</li>
			<li><a href="delete_librarian.jsp">Delete</a></li>
			
			<li><a href="return.jsp">return</a></li>
						<li><a href="bookClaimAddfromLib.jsp">Approve Add Requests</a></li>
						<li><a href="changepassword.jsp">Change_Password</a></li>
						<li><a href="logout.jsp">Logout</a></li>
			
			
		</ul>
	</div>
</nav>


<body background="#FF0000">

	<script type="text/javascript"
		src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="js/materialize.min.js"></script>
	<h1>Hello :
	<%
			user user = new user();
			out.println(user.getfirstname() + " " +user.getlastname() +"!");
		%></h1>


	
	<a href="delete_librarian.jsp" class="waves-effect waves-light btn"><i
		class="material-icons left">delete</i>Delete</a>
	<a href="search4lib.jsp" class="waves-effect waves-light btn"><i
		class="material-icons left">search</i>Search</a>
	<a href="report.jsp" class="waves-effect waves-light btn">
	<i class="material-icons left">report</i>Report</a>


		<p style="color:red;">  ${message} </p>
	
</body>
</html>