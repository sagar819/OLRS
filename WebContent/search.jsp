<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html  PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd" PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd" >
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search</title>
</head>

	<h1>Login Please</h1>
	
	<form name="myForm" action="search" method="post"
		onsubmit="return validateForm()">
		
				<input type="submit" value="Submit" id="boton"></td>
				<%
					if (request.getAttribute("Error") != null) {
						out.println(request.getAttribute("Error"));
					}
				%>
	</form>


	<table align="center">
 <%
   List booklist=new ArrayList();
   booklist=(ArrayList)request.getAttribute("booklist");
   if(booklist!=null && booklist.size()>0 ){
 %>
   <h2 align="center">Book List</h2>
   <tr>
    <th>ISBN Code</th>
    <th>Book Name</th>
    <th>Category</th>
    <th>Action</th>
   </tr>
 <%
   for(int i=0;i<booklist.size();i++){
	   List book=(List)booklist.get(i);
 %>
  <tr>
   <td><%=book.get(0) %></td>
   <td><%=book.get(1) %></td>
   <td><%=book.get(2) %></td>
   <%if (book.get(4).equals(true)){ %>
   <th><a href="reservation?book_id=<%=book.get(0)%>&&user_id=<%=book.get(0)%>">Reserve <a></th><%}else{ %>
      <th><a href="waitlist?book_id=<%=book.get(0)%>&&user_id=<%=book.get(0)%>">Add to Waitlist <a></th><%} %>
  </tr>
 <%
  }
 }else{
 %>
 <%}%>
 
 </table>
  </html>