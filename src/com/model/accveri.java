package com.model;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class accveri
 */
@WebServlet("/accveri")
public class accveri extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public accveri() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd =null;
		String s = request.getParameter("cd");
		System.out.println(s);
		try {

			DbCon con = new DbCon();
			Connection conn = (Connection) con.connectionmanager();

			PreparedStatement ps = (PreparedStatement) conn.prepareStatement("Select state from auth where code_ver=?");

			ps.setString(1, s);

			ResultSet rs = ps.executeQuery();
			rs.next();
			int foundType = rs.getInt(1);
			int tempid = foundType;
			System.out.println(tempid);
			if (tempid > -1) {
				if (tempid == 1) {
					request.setAttribute("error", "Your Account is already verified You can login now");
					rd = request.getRequestDispatcher("login.jsp");
					rd.forward(request, response);
				} else if (tempid == 0) {
					PreparedStatement ps1 = (PreparedStatement) conn
							.prepareStatement("update auth set state =? where code_ver=?");
					ps1.setInt(1, 1);
					ps1.setString(2, s);
					ps1.executeUpdate();
					request.setAttribute("error", "Your Account is activated");
					rd = request.getRequestDispatcher("login.jsp");
					rd.forward(request, response);
				}
			} else {
				response.getWriter().append("It seems you are lost somewere").append(request.getContextPath());
			}
			conn.close();

		} catch (Exception ex) {
			System.out.println("Error ->" + ex.getMessage());
		} finally {

			// System.out.close();
		}

	}
}
