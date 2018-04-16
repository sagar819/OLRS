package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.controller.SendEmail;
import com.model.AuthSignup;
import com.model.Code;
import com.model.DbCon;
import com.model.SendEmail;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class signup
 */
@WebServlet("/signup")
public class signup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public signup() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("name");
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		RequestDispatcher rd = null;
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		AuthSignup authsignup = new AuthSignup();
		Boolean result = false;
		try {
			result = authsignup.authenticate(username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (result) {
			System.out.println("inside result");
			Boolean resultfinal;
			try {
				resultfinal = authsignup.DbInsert(username, fname, lname, email, password, role);

				if (resultfinal) {
					Code cd = new Code();
					int codex = cd.codegenrator(username, password);
					if (codex == 1) {
						DbCon con = new DbCon();
						Connection conn = (Connection) con.connectionmanager();
						PreparedStatement ps = (PreparedStatement) conn
								.prepareStatement("Select id from user where username=?");

						ps.setString(1, username);
						ResultSet rs = ps.executeQuery();
						rs.next();

						int id = rs.getInt(1);
						System.out.println(id);
						SendEmail snedmail = new SendEmail();

						snedmail.sendit(id, "auth");
						conn.close();
						request.setAttribute("Error", "Email authentication Link sent to your Email ID");
						rd = request.getRequestDispatcher("login.jsp");
						rd.include(request, response);
					} else {
						request.setAttribute("error", "User Name Already Exist");
						rd = request.getRequestDispatcher("/signup.jsp");
						rd.include(request, response);

					}
				} else {

					request.setAttribute("error", "User Name Already Exist");
					rd = request.getRequestDispatcher("/signup.jsp");
					rd.include(request, response);

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			request.setAttribute("error", "User Name Already Exist");
			rd = request.getRequestDispatcher("/signup.jsp");
			rd.include(request, response);

		}
		// SendEmail snedmail =new SendEmail();
		// CodeGen cdgen = new CodeGen();
		// String code = cdgen.codegenrator(username, fname);
		// System.out.println(code);
		// String text= "plese verify";
		// String code1="http://team-1.azurewebsites.net/accveri?cd="+code;
		// snedmail.sendit(email,code1,text);

		// if (result.equals("sucess")) {
		// DbSignup Dbinsert = new DbSignup();
		// String resultfinal1 = Dbinsert.authdb(username, fname, lname, email,
		// password, role);
		//// if (resultfinal1.equals("failed")) {

		// user user = new user(username, fname, lname, email, password, role);
		// request.setAttribute("user", user);
		// String str = request.getParameter("str1");
		// request.setAttribute("errorMessage", "Please submit an amount of at least
		// 1");
		// request.getRequestDispatcher("signup.jsp").forward(request, response);
		//
		// rd = request.getRequestDispatcher("signup.jsp");
		// }

		// rd.forward(request, response);

	}

}
