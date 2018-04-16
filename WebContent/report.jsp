<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Report</title>

<!--Import Google Icon Font-->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<!--Import materialize.css-->
<link type="text/css" rel="stylesheet" href="css/materialize.min.css"
	media="screen,projection" />
</head>
<nav>
	<div class="nav-wrapper">
		<a href="#" class="brand-logo">Library</a>
		<ul id="nav-mobile" class="right hide-on-med-and-down">
			<li><a href="main_page_lib.jsp">Main Page</a></li>
			<li><a href="addbook.jsp">Add</a></li>
			<li><a href="delete_librarian.jsp">Delete</a></li>
			<li><a href="search4lib.jsp">Search</a></li>			
		</ul>
	</div>
</nav>
<body>
<script type="text/javascript"
		src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="js/materialize.min.js"></script>
<p >Number of Books in the inventory :   ${message} </p>

 <table border="0" cellpadding="10">
        <thead>
            <tr>
                <th>Book Id</th>
                <th>ISBN</th>
                <th>Book Name</th>
                <th>Book Type</th>
                <th>Department</th>
                <th>Author Name</th>
                <th>Edition</th>
                <th>Quantity</th>
                <th>Price</th>
            
                <th>Publisher</th>
            </tr>
        </thead>
        <tbody>

<%@page import="java.sql.*"%>
            <%
            
            String sql = "SELECT * FROM inventory ";
    		try{
            Class.forName("com.mysql.jdbc.Driver");
    		Connection conn = DriverManager.getConnection("jdbc:mysql://den1.mysql2.gear.host/mydb7","mydb7","MacBookAir@Team_1");
    		  Statement ps = (Statement) conn.createStatement();
    		ResultSet rs= ps.executeQuery(sql);
                while(rs.next()){
            %>
            <tr>
                <%
                    int id = rs.getInt("bookid");
                    String name = rs.getString("isbn_number");
                    String company =rs.getString("book_title");
                    String salary = rs.getString("book_type");
                  String dept=rs.getString("department");
        			String author = rs.getString("auther_name");
        			String edition=rs.getString("edition");
        			String q=rs.getString("quantity");
        			String price=rs.getString("price");
        			
        			String pub=rs.getString("publisher");
                %>
                <td><%=id %></td>
                <td><%=name %></td>
                <td><%=company %></td>
                <td><%=salary %></td>
                <td><%=dept %></td>
                <td><%=author %></td>
                <td><%=edition %></td>
                <td><%=q %></td>
                <td><%=price %></td>
                
                  <td><%=pub %></td>
            </tr>               

            <%      
                }
    		}catch(Exception e){
                	
                }
                
            %>

        </tbody>
    </table>
</body>
</html>