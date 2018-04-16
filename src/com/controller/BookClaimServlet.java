package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.model.user;
import com.model.AuthDAOLib;

/**
 * Servlet implementation class BookClaimServlet
 */
@WebServlet("/BookClaimServlet")
public class BookClaimServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookClaimServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String isbn = request.getParameter("isbn");
		String tittle = request.getParameter("tittle");
		String type = request.getParameter("type");
		String department = request.getParameter("department");
		String author = request.getParameter("author");
		String edition = request.getParameter("edition");
		int quantity = Integer.valueOf(request.getParameter("quantity"));
		String publisher = request.getParameter("publisher");
		String price = request.getParameter("price");

		RequestDispatcher rd = null;
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		Boolean result = false;

		Boolean resultfinal;

		try {
			resultfinal = AuthDAOLib.enterBookClaim(isbn, tittle, type, department, author, edition, quantity,
					publisher, price);

			user u = new user();
			if (resultfinal) {
				String role = u.getRole();

				if (role != null) {
					if (role.equals("Student")) {
						String message = "Book  Requested Sucessfully";
						response.sendRedirect("bookclaim.jsp?message=" + URLEncoder.encode(message, "UTF-8"));

					} else if (role.equals("TeachingStaff")) {
						String message = "Book Requested Sucessfully";
						response.sendRedirect("bookclaim.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
					} else if (role.equals("Librarian")) {
						rd = request.getRequestDispatcher("/bookclaim.jsp");
					}
				}

			} else {

				String role = u.getRole();

				if (role != null) {
					if (role.equals("Student")) {
						String message = "Something bad happened";
						response.sendRedirect("bookclaim.jsp?message=" + URLEncoder.encode(message, "UTF-8"));

					} else if (role.equals("TeachingStaff")) {
						String message = "Something bad happened";
						response.sendRedirect("bookclaim.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
					} else if (role.equals("Librarian")) {
						rd = request.getRequestDispatcher("/bookclaim.jsp");
					}
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
