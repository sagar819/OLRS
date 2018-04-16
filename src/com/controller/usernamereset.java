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

import com.model.DbCon;
import com.model.SendEmail;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class login
 */
@WebServlet("/usernamereset")
public class usernamereset extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public usernamereset() {
		super();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String emailtemp = request.getParameter("email");
		RequestDispatcher rd = null;
		DbCon con = new DbCon();
		Connection conn = (Connection) con.connectionmanager();
		try {
			System.out.println("inside username reset servlet");

			PreparedStatement ps = (PreparedStatement) conn.prepareStatement("Select id from user where email=?");
			ps.setString(1, emailtemp);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int id = rs.getInt(1);
				SendEmail snedmail = new SendEmail();
				snedmail.sendit(id, "username");

				request.setAttribute("Error", "User Name Sent to your Email Id");
				rd = request.getRequestDispatcher("login.jsp");
			} else {

				request.setAttribute("Error", "Not a valid email");
				rd = request.getRequestDispatcher("login.jsp");
			}
			rd.forward(request, response);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
