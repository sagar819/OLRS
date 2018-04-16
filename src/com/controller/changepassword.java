package com.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.resetpassword;
import com.model.user;

/**
 * Servlet implementation class login
 */
@WebServlet("/changepassword")
public class changepassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public changepassword() {
        super();
        
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		user u = new user();
		String username = request.getParameter("name");
		String password = request.getParameter("password");
		String password1 = request.getParameter("password1");
		RequestDispatcher rd = null;

		resetpassword resetpswd = new resetpassword();
		String result = resetpswd.authreset(username, password, password1);
		if (result.equals("sucess")) {
			request.setAttribute("Error", "Your Password got changed");
			String role =u.getRole();
			
			if (role != null) {
				if (role.equals("Student")) {
					String message = "Password Changed Suessfully";
					response.sendRedirect("main_page_student.jsp?message=" + URLEncoder.encode(message, "UTF-8"));

					//rd = request.getRequestDispatcher("/main_page_student.jsp");
				} else if (role.equals("TeachingStaff")) {
					String message = "Password Changed Suessfully";
					response.sendRedirect("main_page_faculty.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
				} else if (role.equals("Librarian")) {
					rd = request.getRequestDispatcher("/main_page_lib.jsp");							
				}
			}
		} else {
			HttpSession session = request.getSession();
            session.invalidate();
            request.setAttribute("Error", "Username or old password are invalid");
			rd = request.getRequestDispatcher("changepassword.jsp");
			rd.forward(request, response);
		}

	}
}
