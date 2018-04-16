<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

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
	<a href="#" class="brand-logo">Library</a>
	<ul id="nav-mobile" class="right hide-on-med-and-down">
		<li><a href="main_page_lib.jsp">Main page</a></li>
		<li><a href="addbook.jsp">Add</a></li>
		<li><a href="search4lib.jsp">Search</a></li>
		<li><a href="report.jsp">Report</a></li>
		
	</ul>
</div>
</nav>

<h3>Delete Item</h3>
<body background="#FF0000">

	<script type="text/javascript"
		src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="js/materialize.min.js"></script>
<div class="row">
		<form class="col s12" method="post" action="deletelibrarian">
			<div class="row">
				
				<div class="input-field col s6">
					<input name="bookid" id="bookid" type="text" class="validate"> <label
						for="isbn">Book ID :</label>
				</div>
			</div>

	
			<button class="btn waves-effect waves-light" type="submit"
				name="action">
				Submit <i class="material-icons right">send</i>
			</button>
			
			<%
					//Error emission on screen if necessary
					if (request.getAttribute("errorDelete") != null) {
						out.println(request.getAttribute("errorDelete"));
					}
				%>

		</form>
	</div>

</body>
</html>