package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.AuthDAOLib;
import com.model.DbCon;
import com.model.user;

/**
 * Servlet implementation class reseve_special
 */
@WebServlet("/reseve_special")
public class reseve_special extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public reseve_special() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    		user u= new user();
    		DbCon con = new DbCon();
    		Connection conn = con.connectionmanager();
		int book_id = Integer.parseInt(request.getParameter("book_id"));
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		int quantity =Integer.parseInt(request.getParameter("quantity"));
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date current = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(current); // Now use today date.
		
		c.add(Calendar.DATE, 25);
		Date due=c.getTime();
		java.sql.Date sqlDate = new java.sql.Date(current.getTime());
		java.sql.Date due_date = new java.sql.Date(due.getTime());
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) conn.prepareStatement(
					"insert into special_reserve_register(bookid,userid_reserved,issue_date,due_date,quantity) values(?,?,?,?,?)");
		

		ps.setInt(1, book_id);
		ps.setInt(2, user_id);
		ps.setDate(3, sqlDate);
		ps.setDate(4, due_date);
		ps.setInt(5, quantity);

		boolean rs = ps.execute();
		if (!rs) {
			PreparedStatement ps1 = (PreparedStatement) conn
					.prepareStatement("update inventory set reserved_quantity =reserved_quantity + ? where bookid=?");
			ps1.setInt(1, quantity);
			ps1.setInt(2, book_id);
			int rs1 = ps1.executeUpdate();
			
			
		}
		String message = "Book reserved Sucessfully";
		response.sendRedirect("main_page_student.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
            		
		conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

    }
}
