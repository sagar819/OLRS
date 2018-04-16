<%@page import="com.model.user"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<!--Import Google Icon Font-->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<!--Import materialize.css-->
<link type="text/css" rel="stylesheet" href="css/materialize.min.css"
	media="screen,projection" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

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
		<a href="#" class="brand-logo">Library</a>
		<ul id="nav-mobile" class="right hide-on-med-and-down">
			<li><a href="addbook.jsp">Add</a>add</li>
			<li><a href="delete_librarian.jsp">Delete</a></li>
			
			<li><a href="return.jsp">return</a></li>
						<li><a href="main_page_lib.jsp">Main page</a></li>
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




		<p style="color:red;">  ${message} </p>
		
		<script>
	function validateForm() {
		var isbn = document.myForm.isbn.value;
		var tittle = document.myForm.tittle.value;
		var type = document.myForm.type.value;
		var department = document.myForm.department.value;
		var author = document.myForm.author.value;
		var edition = document.myForm.edition.value;

		if (isbn == null || isbn == "") {
			if (tittle == null || tittle == "") {
				alert("Enter isbn and tittle.");
				return false;
			}
			alert("isbn can't be blank.");
			return false;
		} else if (tittle == null || tittle == "") {
			alert("tittle can't be blank.");
			return false;
		}
		 else if (type == null || type == "") {
				alert("type can't be blank.");
				return false;
			}
		 else if (department == null || department == "") {
				alert("department can't be blank.");
				return false;
			}
		 else if (author == null || author == "") {
				alert("author can't be blank.");
				return false;
			}
		 else if (edition == null || edition == "") {
				alert("edition can't be blank.");
				return false;
			}
	}
</script>


</head>
<h2>
	<%
		String book_page_add = (String) request.getAttribute("error");
		if (book_page_add != null)
			out.println("<font color=red size=4px>" + book_page_add + "</font>");
	%>
</h2>
<body>
	<script type="text/javascript"
		src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="js/materialize.min.js"></script>

	




	<form name="myForm" action="addbook" onsubmit="return validateForm()"
		method="post" class="col s12">

		

		<div class="row">
			<div class="input-field col s6">

				 <input type="text" id="isbn" name="isbn" 
			oninvalid="this.setCustomValidity('Please enter the ISBN')">
				<label for="isbn">ISBN</label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s6">

				<input type="text" id="title" name="tittle" 
			oninvalid="this.setCustomValidity('Please enter the title')">
				<label for="title">Title</label>
			</div>
		</div>	

		<div class="row">
			<div class="input-field col s6">

				<input type="text" id="department" name="department"
					oninvalid="this.setCustomValidity('Please enter the Department')">
				<label for="department">Department</label>
			</div>
		</div>


		<div class="col s12">
			<div class="row">
				<div class="input-field col s6">

					<input type="text" id="type" name="type" 
						oninvalid="this.setCustomValidity('Please enter the Type')">
					<label for="type">Type</label>


				</div>
				<div class="input-field col s6">

					<input type="text" id="edition" name="edition"
						
						oninvalid="this.setCustomValidity('Please enter the Edition')">
					<label for="edition">Edition</label>

				</div>
			</div>
		</div>



		<div class="col s12">
			<div class="row">
				<div class="input-field col s6">

					<input type="text" id="author" name="author"
						oninvalid="this.setCustomValidity('Please enter the Author')">
					<label for="author">Author</label>


				</div>
				<div class="input-field col s6">

					<input type="text" id="publisher" name="publisher"
						oninvalid="this.setCustomValidity('Please enter the Publisher')">
					<label for="publisher">Publisher</label>

				</div>
			</div>
		</div>



		<div class="input-field col s12">
			<div class="row">
				<div class="input-field col s6">

					<input type="text" id="quantity" name="quantity"
						oninvalid="this.setCustomValidity('Please enter the Quantity')">
					<label for="quantity">Quantity</label>


				</div>
				<div class="input-field col s6">

					<input type="text" id="price" name="price"
						oninvalid="this.setCustomValidity('Please enter the Price')">
					<label for="price">Price</label>

				</div>
			</div>
		</div>

		<button class="btn waves-effect waves-light" type="submit"
			name="action" id="boton">
			Save And Next <i class="material-icons right">save</i>
		</button>
	</form>
		
	
</body>
</html>