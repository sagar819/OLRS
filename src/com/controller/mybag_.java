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

import com.model.AuthDAOLib;
import com.model.DbCon;
import com.model.user;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class mybag_
 */
@WebServlet(name = "mybag_special", urlPatterns = { "/mybag_special" })
public class mybag_ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mybag_() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

  		RequestDispatcher rd = null;
  		List baglist=new ArrayList();
  		user u=new user();
  				DbCon con = new DbCon();
  				Connection conn = (Connection) con.connectionmanager();
  					PreparedStatement ps;
  					try {
  						ps = (PreparedStatement) conn.prepareStatement("Select * from special_reserve_register where userid_reserved=? and return_date is null");
  					ps.setInt(1, u.getId());
  					ResultSet rs = ps.executeQuery();
  					while (rs.next()) {
  				    	  List bag=new ArrayList();
  				    	  bag.add(rs.getInt(1));
  				    	  int bookid=rs.getInt(2);
  				    	  String bookname= AuthDAOLib.getbookname(bookid);
  				    	  bag.add(rs.getInt(2));
  				    	  bag.add(bookname);
  				    	  bag.add(rs.getDate(4));
  				    	  bag.add(rs.getDate(5));
  				    	  baglist.add(bag);
  				    	  }
  				 	
  				    	  
  					conn.close();
  					request.setAttribute("baglist",baglist);  
  					request.getRequestDispatcher("special_faculty.jsp").forward(request, response);
  				}
  					
  					 catch (SQLException e) {
  					// TODO Auto-generated catch block
  					e.printStackTrace();
  				}
  					
      }
  }