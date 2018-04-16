package com.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.AuthDAOLib;
import com.model.AuthLogin;
import com.model.user;

/**
 * Servlet implementation class return_book
 */
@WebServlet("/return_book")
public class return_book extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public return_book() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
 user u =new user();
		String bookingid = request.getParameter("bookingid");
		int quantity = Integer.parseInt(request.getParameter("g"));

		RequestDispatcher rd = null;
		AuthDAOLib authdao = new AuthDAOLib();
		if(quantity ==2) {
			try {
				int number_book = authdao.getbooknumber(bookingid);
				
				//Boolean ret =authdao.check_return(bookingid);
				if(number_book>0) {
					int bookid = authdao.getbookidspecial(bookingid);
				// for last
					//Boolean result = authdao.return_udate(bookingid);
					for (int i=0;i<number_book;i++) {
					Boolean result_waitlist=authdao.reduce_waitlist(bookid);
					if(result_waitlist) {
						Boolean wait_removal=authdao.remove_waitlist(bookid);
					}
					else {
						authdao.reduce_inventory(bookid);
								}
			}
					authdao.check_return_special(bookingid);
					String role = u.getRole();

					if (role != null) {
						if (role.equals("Student")) {
							String message = "Book  Returned Sucessfully";
							response.sendRedirect("main_page_student.jsp?message=" + URLEncoder.encode(message, "UTF-8"));

						} else if (role.equals("TeachingStaff")) {
							String message = "Book Returned Sucessfully";
							response.sendRedirect("main_page_faculty.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
						} else if (role.equals("Librarian")) {
							String message = "Book Returned Sucessfully";
							response.sendRedirect("main_page_lib.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
						}
					}
				}
					
			else {
				String role = u.getRole();

				if (role != null) {
					if (role.equals("Student")) {
						String message = "Some thing went wrong check if bookk is correct or book is already retuned";
						response.sendRedirect("main_page_student.jsp?message=" + URLEncoder.encode(message, "UTF-8"));

					} else if (role.equals("TeachingStaff")) {
						String message = "Some thing went wrong check if bookk is correct or book is already retuned";
						response.sendRedirect("main_page_faculty.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
					} else if (role.equals("Librarian")) {
						String message = "Some thing went wrong check if bookk is correct or book is already retuned";
						response.sendRedirect("main_page_lib.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
					}
				}
						
					}
			}	
			
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(quantity ==1){
		try {
			Boolean ret =authdao.check_return(bookingid);
			System.out.println(ret);
			if(ret) {
			Boolean result = authdao.return_udate(bookingid);
			System.out.println(result);

			if (!result) {
				System.out.println(result);
				int bookid = authdao.getbookid(bookingid);
				Boolean result_waitlist=authdao.reduce_waitlist(bookid);
				System.out.println(result_waitlist);
				if(result_waitlist) {
					Boolean wait_removal=authdao.remove_waitlist(bookid);
					String role = u.getRole();

					if (role != null) {
						if (role.equals("Student")) {
							String message = "Book  Returned Sucessfully";
							response.sendRedirect("main_page_student.jsp?message=" + URLEncoder.encode(message, "UTF-8"));

						} else if (role.equals("TeachingStaff")) {
							String message = "Book Returned Sucessfully";
							response.sendRedirect("main_page_faculty.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
						} else if (role.equals("Librarian")) {
							String message = "Book Returned Sucessfully";
							response.sendRedirect("main_page_lib.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
						}
					}
				}
				else {authdao.reduce_inventory(bookid);
				String role = u.getRole();

				if (role != null) {
					if (role.equals("Student")) {
						String message = "Book  Returned Sucessfully";
						response.sendRedirect("main_page_student.jsp?message=" + URLEncoder.encode(message, "UTF-8"));

					} else if (role.equals("TeachingStaff")) {
						String message = "Book Returned Sucessfully";
						response.sendRedirect("main_page_faculty.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
					} else if (role.equals("Librarian")) {
						String message = "Book Returned Sucessfully";
						response.sendRedirect("main_page_lib.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
					}
				}	}
			}
		}else {
			String role = u.getRole();

			if (role != null) {
				if (role.equals("Student")) {
					String message = "Some thing went wrong check if bookk is correct or book is already retuned";
					response.sendRedirect("main_page_student.jsp?message=" + URLEncoder.encode(message, "UTF-8"));

				} else if (role.equals("TeachingStaff")) {
					String message = "Some thing went wrong check if bookk is correct or book is already retuned";
					response.sendRedirect("main_page_faculty.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
				} else if (role.equals("Librarian")) {
					String message = "Some thing went wrong check if bookk is correct or book is already retuned";
					response.sendRedirect("main_page_lib.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
				}
			}
		} 
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		else{
			String role = u.getRole();

			if (role != null) {
				if (role.equals("Student")) {
					String message = "Some thing went wrong check if bookk is correct or book is already retuned";
					response.sendRedirect("main_page_student.jsp?message=" + URLEncoder.encode(message, "UTF-8"));

				} else if (role.equals("TeachingStaff")) {
					String message = "Some thing went wrong check if bookk is correct or book is already retuned";
					response.sendRedirect("main_page_faculty.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
				} else if (role.equals("Librarian")) {
					String message = "Some thing went wrong check if bookk is correct or book is already retuned";
					response.sendRedirect("main_page_lib.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
				}
			}
		} 
	}
}
