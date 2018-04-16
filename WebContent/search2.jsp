<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.model.user"%>
<%
	user u = new user();
%>
<!DOCTYPE html  PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd" PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd" >

<html>
<head>
<%
		if (session != null) {
			if (session.getAttribute("role") != null) {
				String name = (String) session.getAttribute("role");
				if (name.equals("Librarian")) {
					//out.println("you are already logged in");
					//response.sendRedirect("index.jsp");
				} else if (name.equals("Student")) {
					//out.println("you are already logged in");
					//response.sendRedirect("index.jsp");
			} 
		}else {
			response.sendRedirect("index.jsp");
		}
		}
	%>	
<nav>
	<div class="nav-wrapper">
		<a href="#" class="brand-logo">Search Book</a>
		<ul id="nav-mobile" class="right hide-on-med-and-down">
					<li><a href="index.jsp">Home</a></li>
		
			<li><a href="Search">Search</a></li>
			<li><a href="changepassword.jsp">Change_Password</a></li>
			<li><a href="bookclaim.jsp">Book CLaim</a></li>
			<li><a href="logout.jsp">Logout</a></li>
		</ul>
	</div>
</nav>


<!--Import Google Icon Font-->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<!--Import materialize.css-->
<link type="text/css" rel="stylesheet" href="css/materialize.min.css"
	media="screen,projection" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>

<body>
	<script type="text/javascript"
		src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="js/materialize.min.js"></script>




	<h4>Search Book and Reserve/WaitList</h4>

	<div class="row">
		<form class="col s12" name="myForm1" action="search2" method="post"
			onsubmit="return validateForm()">
			<div class="input-field col s6">
				<input name="bookName" id="bookName" type="text" class="validate"
					required=""
					oninvalid="this.setCustomValidity('Please enter Department Name.')"
					oninput="setCustomValidity('')"> <label for="">Depratment
					Name</label>
			</div>




			<button id="boton" class="btn waves-effect waves-light" type="submit"
				name="action">
				Submit <i class="material-icons right">send</i>

			</button>
	</div>

	<!-- 		<table> -->
	<%-- 			<c:forEach items="${booklist}" var="item"> --%>
	<%-- 	   		 ${item}<br> --%>
	<%-- 			</c:forEach> --%>
	<!-- 		</table> -->


	<table>
		<!-- here should go some titles... -->
		<c:if test="${populate_list == true}">
			<tr>
				<th>ISBN</th>
				<th>Title</th>
				<th>Author</th>
				<th>Edition</th>
				<th>Total  Qty</th>
				<th>Current Reserved Qty</th>
				<th>Reserve / Waitlist</th>
			</tr>



			<c:forEach begin="0" end="${total_records}" step="1"
				varStatus="loopCounter" items="${booklist}" var="book">
				<tr>
					<td><c:out value="${book.isbn_number}" /></td>
					<td><c:out value="${book.book_title}" /></td>
					<td><c:out value="${book.auther_name}" /></td>
					<td><c:out value="${book.edition}" /></td>
					<td><c:out value="${book.quantity}" /></td>
					<td><c:out value="${book.availbleQty}" /></td>
					<c:if test="${book.availability == 'Available'}">
						<td><a
							href="reservation?book_id=${book.bookid}&&user_id=<%=u.getId()%>">Reserve
								<a></td>
					</c:if>

					<c:if test="${book.availability == 'Reserved'}">
						<td><a
							href="waitlist?book_id=${book.bookid}&&user_id=<%=u.getId()%>">Add
								to Waitlist <a></td>
					</c:if>


				</tr>
			</c:forEach>
		</c:if>
	</table>
	<tr>
		<td></td>

		<%
			//Error emission on screen if necessary
			if (request.getAttribute("ErrorForDeptsearch2") != null) {
				out.println(request.getAttribute("ErrorForDeptsearch2"));
			}
		%>

	</tr>
	</form>

	<div class="row">
		<form class="col s12" action="search2ByBookTitle" method="post"
			onsubmit="return validateForm()">
			<div class="input-field col s6">
				<input name="bookName" id="bookName" type="text" class="validate"
					required=""
					oninvalid="this.setCustomValidity('Please enter book Title..')"
					oninput="setCustomValidity('')"> <label for="">Book
					Name</label>
			</div>




			<button id="boton" class="btn waves-effect waves-light" type="submit"
				name="action">
				Submit <i class="material-icons right">send</i>

			</button>
	</div>


	<!-- 		<table> -->
	<%-- 			<c:forEach items="${booklist}" var="item"> --%>
	<%-- 	   		 ${item}<br> --%>
	<%-- 			</c:forEach> --%>
	<!-- 		</table> -->


	<table>
		<!-- here should go some titles... -->
		<c:if test="${populate_list_book_title == true}">
			<tr>
				<th>ISBN</th>
				<th>Title</th>
				<th>Author</th>
				<th>Edition</th>
				<th>Total  Qty</th>
				<th>Current Reserved Qty</th>
				<th>Reserve / Waitlist</th>
			</tr>



			<c:forEach begin="0" end="${total_records_book_title}" step="1"
				varStatus="loopCounter" items="${booklist_by_book_title}" var="book">
				<tr>
					<td><c:out value="${book.isbn_number}" /></td>
					<td><c:out value="${book.book_title}" /></td>
					<td><c:out value="${book.auther_name}" /></td>
					<td><c:out value="${book.edition}" /></td>
					<td><c:out value="${book.quantity}" /></td>
					<td><c:out value="${book.availbleQty}" /></td>


					<c:if test="${book.availability == 'Available'}">
						<td><a
							href="reservation?book_id=${book.bookid}&&user_id=<%=u.getId()%>">Reserve
								<a></td>
					</c:if>

					<c:if test="${book.availability == 'Reserved'}">
						<td><a
							href="waitlist?book_id=${book.bookid}&&user_id=<%=u.getId()%>">Add
								to Waitlist <a></td>
					</c:if>

				</tr>
					</>
				</c:forEach>
		</c:if>
	</table>
	<tr>
		<td></td>

		<%
			//Error emission on screen if necessary
			if (request.getAttribute("ErrorForBookTitle") != null) {
				out.println(request.getAttribute("ErrorForBookTitle"));
			}
		%>

	</tr>
	</form>

	<form name="myFormClearVariables" action="search2Clear" method="post"
		onsubmit="">

		<button id="boton" class="btn waves-effect waves-light" type="submit" 
			name="action">Reset Search        
			
	</form>



	</center>
</html>