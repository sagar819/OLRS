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
 * Servlet implementation class reservation_history
 */
@WebServlet("/reservation_history")
public class reservation_history extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public reservation_history() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		List reservationlist=new ArrayList();
		user u=new user();
				DbCon con = new DbCon();
				Connection conn = (Connection) con.connectionmanager();
					PreparedStatement ps;
					try {
						ps = (PreparedStatement) conn.prepareStatement("Select * from reserve_register where userid_reserved=? and return_date is not null");
					ps.setInt(1, u.getId());
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
				    	  List reg=new ArrayList();
				    	  reg.add(rs.getInt(1));
				    	  int bookid=rs.getInt(2);
				    	  String bookname= AuthDAOLib.getbookname(bookid);
				    	  reg.add(rs.getInt(2));
				    	  reg.add(bookname);
				    	  reg.add(rs.getDate(4));
				    	  reg.add(rs.getDate(6));
				    	  reservationlist.add(reg);
				    	  }
				 	
				    	  
					conn.close();
					//rs.next();			
	          //  request.setAttribute("Error", rs);
	            request.setAttribute("reservationlist",reservationlist);  
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