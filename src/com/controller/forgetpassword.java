package com.controller;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.Code;
import com.model.SendEmail;
import com.model.DbCon;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class login
 */
@WebServlet("/forgetpassword")
public class forgetpassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public forgetpassword() {
        super();
        
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String username = request.getParameter("UserName");
		RequestDispatcher rd = null;
		DbCon con = new DbCon();
		Connection conn = (Connection) con.connectionmanager();
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement("Select id from user where username=?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
		
			if (rs.next()) {
				int id = rs.getInt(1);
				Code cd = new Code();
				cd.InserCode(id);
				System.out.println("code generated");
				SendEmail snedmail =new SendEmail();
				
				snedmail.sendit(id ,"forget");
				request.setAttribute("Error", "Password Reset Link sent to your Email ID");
				rd = request.getRequestDispatcher("login.jsp");
		} else {
			
            request.setAttribute("Error", "Not a User Name ");
			rd = request.getRequestDispatcher("login.jsp");
		}
		rd.forward(request, response);
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		}
}
