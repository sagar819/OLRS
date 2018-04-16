package com.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.AuthLogin;
import com.model.DbCon;
import com.model.user;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class search
 */
@WebServlet("/search")
public class search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public search() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    		user u =new user();
		RequestDispatcher rd = null;
		List booklist=new ArrayList();

				DbCon con = new DbCon();
				Connection conn = (Connection) con.connectionmanager();
					PreparedStatement ps;
					try {
						ps = (PreparedStatement) conn.prepareStatement("Select * from inventory ");
					
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
				    	  List book=new ArrayList();
				    	  book.add(rs.getInt(1));
				    	  book.add(rs.getString(2));
				    	  book.add(rs.getString(3));
				    	  book.add(rs.getString(4));
				    	  int total=rs.getInt(8);
				    	  int current=rs.getInt(11);
				    	  if((total-current)>0) {
				    		boolean  state=true;
				    		  book.add(state);
				    	  }
				    	  else {
				    		  boolean state =false;
				    		  book.add(state);
				    		  
				    	  }
				    	  booklist.add(book);
				    	}
					//rs.next();			
	          //  request.setAttribute("Error", rs);
	            request.setAttribute("booklist",booklist);  
				String role =u.getRole();
				
				if (role != null) {
					if (role.equals("Student")) {
						request.getRequestDispatcher("main_page_student.jsp").forward(request, response);

						//rd = request.getRequestDispatcher("/main_page_student.jsp");
					} else if (role.equals("TeachingStaff")) {
						request.getRequestDispatcher("main_page_faculty.jsp").forward(request, response);
					} else if (role.equals("Librarian")) {
						request.getRequestDispatcher("main_page_lib.jsp").forward(request, response);
					}
				}
				}
					 catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    }
 
	}