package com.controller;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

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
 * Servlet implementation class accveri
 */
@WebServlet("/resse")
public class resse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public resse() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = null;
		String s = request.getParameter("cd");
 		DbCon con = new DbCon();
		Connection conn;

		conn = (Connection) con.connectionmanager();

		// response.getWriter().append("Served at: ").append(request.getContextPath());
		try {

			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement("Select state, id from auth where code_ver=?");

			ps.setString(1, s);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int foundType = rs.getInt(1);
			int id = rs.getInt(2);
			int tempid = foundType;
			if (tempid > -1) {
				if (tempid == 1) {
					request.setAttribute("error", "Link Expired");
					rd = request.getRequestDispatcher("login.jsp");
					rd.forward(request, response);

				} else if (tempid == 0) {
					request.setAttribute("error", id);

					request.getRequestDispatcher("reenterpassword.jsp").forward(request, response);
				}

			}

			else {
				response.getWriter().append("It seems you are lost somewere").append(request.getContextPath());
			}
			conn.close();

		} catch (Exception ex) {
			System.out.println("Error ->" + ex.getMessage());
		} finally {
		}

	}
}
