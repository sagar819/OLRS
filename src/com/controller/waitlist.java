package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
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
import com.model.user;

/**
 * Servlet implementation class waitlist
 */
@WebServlet("/waitlist")
public class waitlist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public waitlist() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int book_id = Integer.parseInt(request.getParameter("book_id"));
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date current = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(current); // Now use today date.
		
		c.add(Calendar.DATE, 25);
		Date due=c.getTime();
		//String due=dateFormat.format(c.getTime());
		RequestDispatcher rd = null;
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		user u = new user();
		Boolean result = false;
		
	Boolean resultfinal;
			
				try {
					if (AuthDAOLib.checkbookholding(book_id, user_id)) {
					if (AuthDAOLib.checkwaitliststatus(book_id,user_id)) {
					resultfinal = AuthDAOLib.wait_list_add(book_id,user_id,current);
				
				if (resultfinal) {
					String role =u.getRole();
					
					if (role != null) {
						if (role.equals("Student")) {
							String message = "You are added to the wait list";
							response.sendRedirect("main_page_student.jsp?message=" + URLEncoder.encode(message, "UTF-8"));

							//rd = request.getRequestDispatcher("/main_page_student.jsp");
						} else if (role.equals("TeachingStaff")) {
							String message = "You are added to the wait list";
							response.sendRedirect("main_page_faculty.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
						} else if (role.equals("Librarian")) {
							rd = request.getRequestDispatcher("/main_page_lib.jsp");							
						}
					}

					
				} else {

					String role =u.getRole();
					
					if (role != null) {
						if (role.equals("Student")) {
							String message = "some thing bad happened";
							response.sendRedirect("main_page_student.jsp?message=" + URLEncoder.encode(message, "UTF-8"));

							//rd = request.getRequestDispatcher("/main_page_student.jsp");
						} else if (role.equals("TeachingStaff")) {
							String message = "something bad happened";
							response.sendRedirect("main_page_faculty.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
						} else if (role.equals("Librarian")) {
							rd = request.getRequestDispatcher("/main_page_lib.jsp");							
						}
					}

				}
					}else {
						String role =u.getRole();
						if (role != null) {
							if (role.equals("Student")) {
								String message = "You are already in the wait list for the book";
								response.sendRedirect("main_page_student.jsp?message=" + URLEncoder.encode(message, "UTF-8"));

								//rd = request.getRequestDispatcher("/main_page_student.jsp");
							} else if (role.equals("TeachingStaff")) {
								String message = "You are already in th ewait list for the book";
								response.sendRedirect("main_page_faculty.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
							} else if (role.equals("Librarian")) {
								rd = request.getRequestDispatcher("/main_page_lib.jsp");							
							}
						}
					}
				}else {
					String role =u.getRole();
					
					if (role != null) {
						if (role.equals("Student")) {
							String message = "You are already holding the book";
							response.sendRedirect("main_page_student.jsp?message=" + URLEncoder.encode(message, "UTF-8"));

							//rd = request.getRequestDispatcher("/main_page_student.jsp");
						} else if (role.equals("TeachingStaff")) {
							String message = "You are already holding the book";
							response.sendRedirect("main_page_faculty.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
						} else if (role.equals("Librarian")) {
							rd = request.getRequestDispatcher("/main_page_lib.jsp");							
						}
					}
				}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
	}

}
