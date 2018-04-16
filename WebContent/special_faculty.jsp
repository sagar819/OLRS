<jsp:include page="flush.jsp" flush="true" />
<%@page import="com.model.user"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

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
		<a href="#" class="brand-logo">Faculty Section</a>
		<ul id="nav-mobile" class="right hide-on-med-and-down">
		<li><a href="index.jsp">Home</a></li>
			<li><a href="search2.jsp">Search</a></li>
			
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

<%
					if (request.getAttribute("Error") != null) {
						out.println(request.getAttribute("Error"));
					}
				%>
	<a href="mybag_special" class="waves-effect waves-light btn"><i
		class="material-icons left">book</i>Mybag</a>
	
		<form name="myForm" action="search_" method="post"
		onsubmit="return validateForm()">
	<input type="submit" value ="All Books"class="waves-effect waves-light btn">
		


		<p style="color:red;">  ${message} </p>
		
		
				
				
	</form>
	<p>${param.message}</p>
	<table align="center">
 <%
   List booklist=new ArrayList();
 user u = new user();
   booklist=(ArrayList)request.getAttribute("booklist");
   if(booklist!=null && booklist.size()>0 ){
 %>
   <h2 align="center">Book List</h2>
   <tr>
       <th>Book Id</th>
    <th>Book Name</th>
    <th>Book Type</th>
    <th>Action</th>
    <th>Quantity</th>
   </tr>
 <%
   for(int i=0;i<booklist.size();i++){
	   List book=(List)booklist.get(i);
 %>
  <tr>
   <td><%=book.get(0) %></td>
   <td><%=book.get(1) %></td>
   <td><%=book.get(2) %></td>
      <td><%=book.get(3) %></td>
         <td><%=book.get(4) %></td>
         
   <% if (book.get(4).equals(0)){%>
   <%}else{ %>
   <form name="myForm" action="reseve_special" method="get" onsubmit="return validateForm()">
		<input type="hidden" name="book_id" value="<%=book.get(0)%>" >
		<input type="hidden" name="user_id" value ="<%=u.getId()%>">
<td><input type="number" name="quantity" placeholder="0"></td>
<td><input type="submit" value="reserve" ></td>
</form>
<%--      <td><a href="reseve_special?book_id=<%=book.get(0)%>&&user_id=<%=u.getId()%>&&quantity=<%request.getParameter("uname");%>">Reserve </a></td> --%>
  </tr>
 
 <%}
   }
   }%>
 
 </table>
 

					<table align="center">
				
				<%
   List waitlistbag=new ArrayList();
	waitlistbag=(ArrayList)request.getAttribute("waitlistbag");
   if(waitlistbag!=null && waitlistbag.size()>0 ){
 %>
   <h2 align="center">Book List</h2>
   <tr>
       <th>Book Id</th>
    <th>requested date</th>
    <th>Initial waitlist no </th>
    <th>Current waitlist no</th>
   
   </tr>
 <%
   for(int i=0;i<waitlistbag.size();i++){
	   List wtlistbag=(List)waitlistbag.get(i);
 %>
  <tr>
   <td><%=wtlistbag.get(0) %></td>
   <td><%=wtlistbag.get(1) %></td>
      <td><%=wtlistbag.get(2) %></td>
      <td><%=wtlistbag.get(3) %></td>
  </tr>
 <%
  }
 }else{
 %>
 <%}%>
 
 </table>
	<%
					if (request.getAttribute("bag") != null) {
						out.println(request.getAttribute("bag"));
					}
				%>
	
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
    <th>Return</th>
    
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
   <td><a href="return_book?bookingid=<%=bag.get(0)%>&&g=2">Return <a></td>
 <%
  }
 }else{
 %>
 <%}%>
 </table>			
</body>
</html>