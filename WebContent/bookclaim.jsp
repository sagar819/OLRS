<%@page import="com.model.user"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	if (session != null) {
		if (session.getAttribute("role") != null) {
			String name = (String) session.getAttribute("role");
			if (name.equals("Librarian")) {
				out.println("you are already logged in");
				response.sendRedirect("index.jsp");
			}
		} else {
			response.sendRedirect("index.jsp");
		}
	}
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Book Claim</title>
<script>
	function validateForm() {
		var firstname = document.myForm.firstname.value;
		var lastname = document.myForm.lastname.value;
		var email = document.myForm.email.value;
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
		}

		else if (firstname == null || firstname == "") {
			alert("firstname can't be blank.");
			return false;
		}

		else if (lastname == null || lastname == "") {
			alert("lastname can't be blank.");
			return false;
		}

		else if (email == null || email == "") {
			alert("email can't be blank.");
			return false;
		}

		else if (tittle == null || tittle == "") {
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
	<nav>
	<div class="nav-wrapper">
		<a href="#" class="brand-logo">Library System</a>
		<ul id="nav-mobile" class="right hide-on-med-and-down">
			<li><a href="index.jsp">Home</a></li>
			
			
		</ul>
	</div>
	</nav>
	
	<h2>
	<%
		String book_claim_add = (String) request.getAttribute("error");
		if (book_claim_add != null)
			out.println("<font color=red size=4px>" + book_claim_add + "</font>");
	%>
</h2>
	<center>
		
		<form name="myForm" action="BookClaimServlet"
			onsubmit="return validateForm()" method="post" class="col s12">
			<%
				user u = new user();
			%>

			<hidden type="text" name="userId" value=<%=u.getId()%>> 
			<hidden type="text" name="firstname" value=<%=u.getfirstname()%>>
			<hidden type="text" name="lastname" value=<%=u.getlastname()%>>
			<hidden type="text" name="email" value=<%=u.getemail()%>>

			
				
				
				<div class="row">
			<div class="col s12">
				<div class="row">
					<div class="input-field col s6">
						<input type="text" id="isbn" name="isbn" > <label for="isbn">
							ISBN</label>
					</div>
					<div class="input-field col s6">
						<input type="text" id="tittle" name="tittle" > <label for="tittle">Title</label>
					</div>
				</div>
			</div>
		</div>
		
			<div class="row">
			<div class="col s12">
				<div class="row">
					<div class="input-field col s6">
						<input type="text" id="type" name="type" > <label for="type">
							Type</label>
					</div>
					<div class="input-field col s6">
							<input type="text" name="department" id="department"><label for="department">Department</label>
					</div>
				</div>
			</div>
		</div>
				
					<div class="row">
			<div class="col s12">
				<div class="row">
					<div class="input-field col s6">
						<input type="text" name="author" id="author"> <label for="author">
							Author</label>
					</div>
					<div class="input-field col s6">
							<input type="text" name="edition" id="edition"><label for="edition">Edition</label>
					</div>
				</div>
			</div>
		</div>
		
			
					<div class="row">
			<div class="col s12">
				<div class="row">
					<div class="input-field col s6">
						<input type="text" name="quantity" id="quantity"> <label for="quantity">
							Quantity</label>
					</div>
					<div class="input-field col s6">
							<input type="text" name="publisher" id="publisher"><label for="publisher">Publisher</label>
					</div>
				</div>
			</div>
		</div>
					
				
					<div class="row">
				<div class="input-field col s6">
							<input type="text" name="price" id="price"><label for="price">Price</label>
					</div>
				</div>
					
					
					<button class="btn waves-effect waves-light" type="submit"
			name="action" id="boton" onclick="chk_function()">
			Claim Book <i class="material-icons right">send</i>
		</button>
				
		</form>
	</center>
</body>
</html>