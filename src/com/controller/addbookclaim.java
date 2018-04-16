package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.AuthDAOLib;

/**
 * Servlet implementation class addbook
 */
@WebServlet("/addbookclaim")
public class addbookclaim extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addbookclaim() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String isbn = request.getParameter("isbn_c");
		String tittle = request.getParameter("title_c");
		String type = request.getParameter("type_c");
		String department = request.getParameter("department_c");
		String author = request.getParameter("author_c");
		String edition = request.getParameter("edition_c");
		int quantity = Integer.valueOf(request.getParameter("quantity_c"));
		String publisher = request.getParameter("publisher_c");
		String price = request.getParameter("price_c");
		System.out.println(isbn+" "+tittle+" "+type+" "+department+" "+author+" "+edition+" "+publisher+" "+price);
		RequestDispatcher rd = null;
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		Boolean result = false;
		
	Boolean resultfinal;
			
				try {
					resultfinal = AuthDAOLib.enterNewBook(isbn,tittle,type,department,author,edition,quantity,publisher,price);
					AuthDAOLib.deleteFromBookClaim(isbn);
				
				if (resultfinal) {
					request.setAttribute("ErrorForDeptsearch2", "Book Sucessfully added to Inventory .");
					rd = request.getRequestDispatcher("/bookClaimAddfromLib.jsp");
					rd.include(request, response);

					
				} else {

					request.setAttribute("ErrorForDeptsearch2", "Some Thing Bad Happned please try again after few seconds ");
					rd = request.getRequestDispatcher("/abookClaimAddfromLib.jsp");
					rd.include(request, response);
					

				}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String isbn = request.getParameter("book_isbn_claim");
		String title = request.getParameter("book_title_claim");
		System.out.println("here add book claim"+isbn+title);
//		String tittle = request.getParameter("tittle");
//		String type = request.getParameter("type");
//		String department = request.getParameter("department");
//		String author = request.getParameter("author");
//		String edition = request.getParameter("edition");
//		int quantity = Integer.valueOf(request.getParameter("quantity"));
//		String publisher = request.getParameter("publisher");
//		String price = request.getParameter("price");
//
//		RequestDispatcher rd = null;
//		response.setContentType("text/html");
//		PrintWriter out = response.getWriter();
//
//		Boolean result = false;
//		
//	Boolean resultfinal;
//			
//				try {
//					resultfinal = AuthDAOLib.enterNewBook(isbn,tittle,type,department,author,edition,quantity,publisher,price);
//				
//				if (resultfinal) {
//					request.setAttribute("error", "Book Sucessfully Entered .");
//					rd = request.getRequestDispatcher("/addbook.jsp");
//					rd.include(request, response);
//
//					
//				} else {
//
//					request.setAttribute("error", "Some Thing Bad Happned please try again after few seconds ");
//					rd = request.getRequestDispatcher("/addbook.jsp");
//					rd.include(request, response);
//
//				}
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}	
	}

}
