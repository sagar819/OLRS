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

import com.model.DbCon;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class search_
 */
@WebServlet("/search_")
public class search_ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public search_() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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
				    	int  state = total-current;
				    		  book.add(state);
				    	  booklist.add(book);
				    	}
					//rs.next();			
	          //  request.setAttribute("Error", rs);
	            request.setAttribute("booklist",booklist);  
				request.getRequestDispatcher("special_faculty.jsp").forward(request, response);
				}
					 catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    }
 
	}