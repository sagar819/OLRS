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

/**
 * Servlet implementation class login
 */
@WebServlet("/search2Clear")
public class SearchClear extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchClear() {
		super();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean populate_list=false;
		
		//String bookname = request.getParameter("bookName");
		//String password = request.getParameter("password");
		RequestDispatcher rd = null;

		AuthSearch seach_authenticator = new AuthSearch();
		//String result = seach_authenticator.authenticatesignin(bookname);
		//if (result.equals("sucess")) {
			HttpSession session = request.getSession(true);
			session.removeAttribute("booklist");
			session.removeAttribute("booklist_by_book_title");
			session.setAttribute("populate_list", false);
			session.setAttribute("populate_list_book_title", false);
//			//session.setAttribute("name", bookname);
//			ArrayList<Inventory> arrayOfInv1=seach_authenticator.getInvetoryTableObject(bookname);
//			if(arrayOfInv1.size() != 0 ) {
//			session.setAttribute("booklist", arrayOfInv1);
//			session.setAttribute("total_records", arrayOfInv1.size());
//			populate_list=true;
//			session.setAttribute("populate_list", populate_list);
//			rd = request.getRequestDispatcher("search.jsp");
//			}else {
//				populate_list=false;
//				session.setAttribute("populate_list", populate_list);
//				request.setAttribute("ErrorForDeptSearch", " Please enter correct department name ");
//				rd = request.getRequestDispatcher("search.jsp");
//			}
			// user user = new user(bookname, password);
			// request.setAttribute("user", user);
		//} else {
			
			//HttpSession session = request.getSession();
            //session.invalidate();
            //request.setAttribute("Error", " bookname not found ");
			//rd = request.getRequestDispatcher("search.jsp");
		//}
			
			rd = request.getRequestDispatcher("search2.jsp");
		rd.forward(request, response);

	}
}
