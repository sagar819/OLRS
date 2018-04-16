package com.controller;

import java.io.IOException;
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

import com.model.AuthDAOLib;
import com.model.DbCon;
import com.model.user;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class mywaitlist
 */
@WebServlet("/mywaitlist")
public class mywaitlist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mywaitlist() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		List waitlistbag=new ArrayList();
		user u=new user();
				DbCon con = new DbCon();
				Connection conn = (Connection) con.connectionmanager();
					PreparedStatement ps;
					try {
						ps = (PreparedStatement) conn.prepareStatement("Select * from waitlist_register where userid_reserved=?");
					ps.setInt(1, u.getId());
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
				    	  List wtlistbag=new ArrayList();
				    	  wtlistbag.add(rs.getInt(2));
				    	  wtlistbag.add(rs.getDate(4));
				    	  wtlistbag.add(rs.getInt(5));
				    	  wtlistbag.add(rs.getInt(6));
				    	  waitlistbag.add(wtlistbag);
				    	  }
				 	
				    	  
					conn.close();
					//rs.next();			
	          //  request.setAttribute("Error", rs);
	            request.setAttribute("waitlistbag",waitlistbag);  
	            
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