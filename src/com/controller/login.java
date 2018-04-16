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

import com.model.AuthLogin;
import com.model.DbCon;
import com.model.user;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public login() {
		super();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("name");
		String password = request.getParameter("password");
		RequestDispatcher rd = null;
		user u = new user();
		AuthLogin authenticator = new AuthLogin();
		String result = authenticator.authenticatesignin(username, password);
		if (result.equals("sucess")) {
			try {
				Boolean state = authenticator.Prelogin(username);
				if (state == true) {
					authenticator.getuser(username);

					HttpSession session = request.getSession();
					String role = u.getRole();
					session.setAttribute("role", role);
					if (role.equals("Student")) {
						System.out.println("inside student");

						request.getRequestDispatcher("main_page_student.jsp").forward(request, response);
					}

					else if (role.equals("TeachingStaff")) {
						request.getRequestDispatcher("main_page_faculty.jsp").forward(request, response);
					}

					else {
						request.getRequestDispatcher("main_page_lib.jsp").forward(request, response);
					}
				} else {
					request.setAttribute("Error", "Please validate your account");

					request.getRequestDispatcher("login.jsp").forward(request, response);
				}

			} catch (Exception e) {

			}
		}

		else {
			request.setAttribute("Error", "UserName/Password Incorrect");
			rd = request.getRequestDispatcher("/login.jsp");
			rd.include(request, response);
		}

	}
}
