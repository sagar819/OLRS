<%@page import="com.model.user"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<h2><%
String login_msg=(String)request.getAttribute("error");  
if(login_msg!=null)
out.println("<font color=red size=4px>"+login_msg+"</font>");
%></h2>
<!--Import Google Icon Font-->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<!--Import materialize.css-->
<link type="text/css" rel="stylesheet" href="css/materialize.min.css"
	media="screen,projection" />


<meta name="viewport" content="width=device-width, initial-scale=1.0" />
</head>
<%
		if (session != null) {
			if (session.getAttribute("role") != null) {
				String name = (String) session.getAttribute("role");
				if (name.equals("Librarian")) {
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
<nav>
	<div class="nav-wrapper">
		<a href="#" class="brand-logo">Student Section</a>
		<ul id="nav-mobile" class="right hide-on-med-and-down">
			<li><a href="search2.jsp">Search</a></li>
			<li><a href="bookclaim.jsp">Request a New Book</a></li>
			<li><a href="changepassword.jsp">Change_Password</a></li>
			
			<li><a href="logout.jsp">logout</a></li>
			
		</ul>
	</div>
</nav>


<body background="#FF0000">

	<script type="text/javascript"
		src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="js/materialize.min.js"></script>
	<h1>Hello :<%
			user user = new user();
			out.println(user.getfirstname() + " " +user.getlastname() +"!");
		%></h1>


	<a href="mybag" class="waves-effect waves-light btn"><i
		class="material-icons left">book</i>Mybag</a>
	<a href="mywaitlist" class="waves-effect waves-light btn"><i
		class="material-icons left"></i>Wait List</a>
		<form name="myForm" action="search" method="post"
		onsubmit="return validateForm()">
	<input type="submit" value="All Books" class="waves-effect waves-light btn">
		<a href="report"  class="waves-effect waves-light btn"><i
		class="material-icons left">assignment</i>Report</a>


		<p style="color:red;">  ${message} </p>
		
		
				
				<%
					if (request.getAttribute("bag") != null) {
						out.println(request.getAttribute("bag"));
					}
				%>
	</form>
 <table align="center">
 <%
   List baglist=new ArrayList();
   baglist=(ArrayList)request.getAttribute("baglist");
   if(baglist!=null && baglist.size()>0 ){
 %>
   <h2> <align="center">My Bag</h2>
   <tr>
    <th>Booking Id</th>
    <th>Book_id</th>
    <th>Book Name</th>
    <th>Issue Date</th>
    <th>Due Date</th>
    
   </tr>
 <%
   for(int i=0;i<baglist.size();i++){
	   List bag=(List)baglist.get(i);
 %>
  <tr>
   <td><%=bag.get(0) %></td>
   <td><%=bag.get(1) %></td>
   <td><%=bag.get(2) %></td>
   <td><%=bag.get(3) %></td>
   <td><%=bag.get(4) %></td>
   
 <%
  }
 }else{
 %>
 <%}%>
 </table>
 				<%
					if (request.getAttribute("waitlist_") != null) {
						out.println(request.getAttribute("waitlist_"));
					}
				%>
</body>
</html>