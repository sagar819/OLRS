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
@WebServlet("/addbook")
public class addbook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addbook() {
        super();
        // TODO Auto-generated constructor stub
    }

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
					resultfinal = AuthDAOLib.enterNewBook(isbn,tittle,type,department,author,edition,quantity,publisher,price);
				
				if (resultfinal) {
					request.setAttribute("error", "Book Successfully added to Inventory");
					rd = request.getRequestDispatcher("/addbook.jsp");
					rd.include(request, response);

					
				} else {

					request.setAttribute("error", "Some Thing Bad Happned please try again after few seconds ");
					rd = request.getRequestDispatcher("/addbook.jsp");
					rd.include(request, response);

				}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
	}

}
