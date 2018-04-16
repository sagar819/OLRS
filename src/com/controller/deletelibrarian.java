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
@WebServlet("/deletelibrarian")
public class deletelibrarian extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deletelibrarian() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	RequestDispatcher rd;
		String bookid = request.getParameter("bookid");
		int checkBookReserve = 0;
		try {
			 checkBookReserve = AuthDAOLib.getReservedQty(bookid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(checkBookReserve==0) {
			
			//String bookid;
			try {
				AuthDAOLib.deleteBookFromInv(bookid);
				request.setAttribute("errorDelete", "book removed from inventory successfully.");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(checkBookReserve==99999){
			
			request.setAttribute("errorDelete", "This book is not in invetory ");
			
		}else{
			request.setAttribute("errorDelete", "book is in reserved state.Cannot removed book from inventory");
			System.out.println("book is reserved");
		}
		 rd=request.getRequestDispatcher("/delete_librarian.jsp");
		 rd.forward(request, response);
			
		
	}

}
