package com.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.controller.AuthSearch;
import com.model.Inventory;
import com.model.user;

/**
 * Servlet implementation class login
 */
@WebServlet("/search2ByBookTitle")
public class SearchByBookTitle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchByBookTitle() {
		super();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean populate_list_book_title=false;
		System.out.println("in searchbybooktitle");
		String bookname = request.getParameter("bookName");
		//String password = request.getParameter("password");
		RequestDispatcher rd = null;

		AuthSearch seach_authenticator = new AuthSearch();
		//String result = seach_authenticator.authenticatesignin(bookname);
		//if (result.equals("sucess")) {
			HttpSession session = request.getSession(true);
			session.setAttribute("populate_list_book_title", populate_list_book_title);
			//session.setAttribute("name", bookname);
			ArrayList<Inventory> arrayOfInv1=seach_authenticator.getInvetoryTableObjectByBookTitle(bookname);
			if(arrayOfInv1.size() != 0 ) {
			session.setAttribute("booklist_by_book_title", arrayOfInv1);
			session.setAttribute("total_records_book_title", arrayOfInv1.size());
			populate_list_book_title=true;
			session.setAttribute("populate_list_book_title", populate_list_book_title);
			user u = new user();
			String role =u.getRole();
			request.setAttribute("error", "Some thing Bad happened");
		
				if (role != null) {
					if (role.equals("Student")) {
						rd = request.getRequestDispatcher("/search2.jsp");
					} else if (role.equals("TeachingStaff")) {
						rd = request.getRequestDispatcher("/search2.jsp");								
					} else if (role.equals("Librarian")) {
						rd = request.getRequestDispatcher("/search4lib.jsp");							
					}
				}
			
			rd.include(request, response);
			}else {
				populate_list_book_title=false;
				session.setAttribute("populate_list_book_title", populate_list_book_title);
				request.setAttribute("ErrorForBookTitle", " No book is available similar to this book name. ");
				user u = new user();
				String role =u.getRole();
				request.setAttribute("error", "Some thing Bad happened");
			
					if (role != null) {
						if (role.equals("Student")) {
							rd = request.getRequestDispatcher("/search2.jsp");
						} else if (role.equals("TeachingStaff")) {
							rd = request.getRequestDispatcher("/search2.jsp");								
						} else if (role.equals("Librarian")) {
							rd = request.getRequestDispatcher("/search4lib.jsp");							
						}
					}
				
				rd.include(request, response);
			}
			
		rd.forward(request, response);

	}
}
