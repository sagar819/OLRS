<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html  PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd" PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd" >

<html>
<head>

<!--Import Google Icon Font-->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<!--Import materialize.css-->
<link type="text/css" rel="stylesheet" href="css/materialize.min.css"
	media="screen,projection" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Book Claim add from librarian</title>
</head>

<body>
	<script type="text/javascript"
		src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="js/materialize.min.js"></script>
	<h1>Add to Inventory</h1>


	<form class="col s12" name="myForm1" action="bookClaimAdd"
		method="post" onsubmit="return validateForm()">

		<table>
			<!-- here should go some titles... -->

			<tr>
				<th>Book Requester</th>
				<th>ISBN</th>
				<th>Title</th>
				<th>Author</th>
				<th>Edition</th>
				<th>Total Quantity</th>
				<th>Add To Inventory</th>

			</tr>



			<c:forEach begin="0" end="${total_records}" step="1"
				varStatus="loopCounter" items="${booklist}" var="book">
				<tr>
					<td><c:out value="${book.firstnameofbookrequester}" /></td>
					<td><c:out value="${book.isbn_number}" /></td>
					<td><c:out value="${book.book_title}" /></td>
					<td><c:out value="${book.auther_name}" /></td>
					<td><c:out value="${book.edition}" /></td>
					<td><c:out value="${book.quantity}" /></td>
					<%-- 						<td><c:out <a href="addbook?book_id=${book.isbn_number}&&user_id=${book.isbn_number}">Reserve </a> /></td> --%>

					<td><a
						href="addbookclaim?isbn_c=${book.isbn_number}&&title_c=${book.book_title}&&type_c=${book.book_type}&&department_c=${book.department}&&edition_c=${book.edition}&&author_c=${book.auther_name}&&quantity_c=${book.quantity}&&publisher_c=${book.publisher}&&price_c=${book.price}">Add
							To Inventory </a></td>

				</tr>
			</c:forEach>

		</table>


		<button id="boton" class="btn waves-effect waves-light" type="submit"
			name="action">
			Get Requests <i class="material-icons right">send</i>
			</button>
	</form>

	<a href="index.jsp">Home</a>

	<%
					//Error emission on screen if necessary
					if (request.getAttribute("ErrorForDeptsearch2") != null) {
						out.println(request.getAttribute("ErrorForDeptsearch2"));
					}
				%>
				<h1>${ErrorForDeptsearch2}</h1>

</body>


</html>