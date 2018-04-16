package com.model;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class submitreenterpassword
 */
@WebServlet("/submitreenterpassword")
public class submitreenterpassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public submitreenterpassword() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		String password = request.getParameter("Password");
		DbCon con = new DbCon();
		Connection conn = (Connection) con.connectionmanager();
		RequestDispatcher rd = null;

		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement("Select state, id from auth where id=?");

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			rs.next();
			int foundType = rs.getInt(1);

			if (foundType > -1) {
				if (foundType == 1) {
					request.setAttribute("Error", "ILink Expired");
					// rd = request.getRequestDispatcher("/error.jsp");
					response.sendRedirect("login.jsp");
				} else if (foundType == 0) {

					PreparedStatement ps1 = (PreparedStatement) conn
							.prepareStatement("update user set password =? where id=?");
					ps1.setString(1, password);
					System.out.println(password);
					ps1.setInt(2, id);
					ps1.executeUpdate();
					PreparedStatement ps2 = (PreparedStatement) conn
							.prepareStatement("update auth set state =? where id=?");
					ps2.setInt(1, 1);
					ps2.setInt(2, id);
					ps2.executeUpdate();
					conn.close();
					request.setAttribute("error", "Password Changed Successfully");
					rd = request.getRequestDispatcher("login.jsp");
					rd.forward(request, response);


				}

			}
		} catch (Exception ex) {
			System.out.println("Error ->" + ex.getMessage());
		} finally {
			// System.out.close();
		}

	}

}