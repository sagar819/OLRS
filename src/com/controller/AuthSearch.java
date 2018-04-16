package com.controller;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.model.DbCon;
import com.model.Inventory;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class AuthSearch {
	String rtn = null;

	public String authenticatesignin(String bookName) {
		try {

			//String connectionURL = "jdbc:mysql://connnection_change_here"; // students is my database name
			DbCon dbcon = new DbCon();
			Connection con = (Connection) dbcon.connectionmanager();
			//Class.forName("com.mysql.jdbc.Driver").newInstance();
			// "password_change_here"
			//con = (Connection) DriverManager.getConnection(connectionURL, "User_id_change_here", "password_change_here");
			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement("Select * from inventory where bookname=? ");

			ps.setString(1, bookName);
		
			// preparedStmt.execute();

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				rtn = "sucess";
			} else {
				rtn = "failed";
			}
			con.close();

		} catch (Exception ex) {
			System.out.println("Error ->" + ex.getMessage());
		} finally {

			// System.out.close();
		}
		return rtn;
	}
	public ArrayList<Inventory> getInvetoryTableObject (String bookName) {
		ArrayList<Inventory> arrayOfInv = new ArrayList<Inventory>();
		
		try {
			
			//String connectionURL = "jdbc:mysql://connnection_change_here"; // students is my database name
			DbCon dbcon = new DbCon();
			Connection con = (Connection) dbcon.connectionmanager();
			//Class.forName("com.mysql.jdbc.Driver").newInstance();
			// "password_change_here"
			//con = (Connection) DriverManager.getConnection(connectionURL, "User_id_change_here", "password_change_here");
			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement("Select * from inventory where department like ? ");

			ps.setString(1,"%"+ bookName +"%");
		
			// preparedStmt.execute();

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				//rtn = "sucess";
			Inventory invObj = new Inventory();	
				invObj.setBookid(rs.getInt("bookid"));
				invObj.setIsbn_number(rs.getString("isbn_number"));
				invObj.setBook_title(rs.getString("book_title"));
				invObj.setBook_type(rs.getString("book_type"));
				invObj.setDepartment(rs.getString("department"));
				invObj.setAuther_name(rs.getString("auther_name"));
				invObj.setEdition(rs.getString("edition"));
				invObj.setQuantity(rs.getString("quantity"));
				invObj.setPrice(rs.getString("price"));
				invObj.setAvailbleQty(rs.getString("reserved_quantity"));
				//invObj.setPages_number(rs.getString("pages_number"));
				invObj.setPublisher(rs.getString("publisher"));
				invObj.setAvailability(checkAvailability(rs.getInt("bookid")));
				//System.out.println(invObj);
				arrayOfInv.add(invObj);
				
				
//				rs.getString("isbn_number")
//				rs.getString("book_title")
//				rs.getString("book_type")
//				rs.getString("department")
//				rs.getString("auther_name")
//				rs.getString("edition")
//				rs.getString("quantity")
//				rs.getString("price")
//				rs.getString("pages_number")
//				rs.getString("publisher")
				
//				CREATE TABLE seproject.inventory (
//						 
//					    bookid int auto_increment,
//					    isbn_number varchar(20)  unique,
//					    book_title varchar(20),
//					     book_type varchar(20) not null ,
//					     department varchar(20)  ,
//					     auther_name varchar(20)  not null,
//					     edition varchar(20)  not null,
//					     quantity varchar(20)  ,
//					     price varchar(20)  ,
//					     pages_number varchar(20)  ,
//					     publisher varchar(20)  ,
//					     primary key (bookid)
//					    
//					);
			}
			if(!rs.next()){
				rtn = "failed";
				//write code for fail case
			}
			con.close();

		} catch (Exception ex) {
			System.out.println("Error ->" + ex.getMessage());
		} finally {

			// System.out.close();
		}
		return arrayOfInv;
	}
	
	public ArrayList<Inventory> getInvetoryTableObjectByBookTitle (String bookName) {
		ArrayList<Inventory> arrayOfInv = new ArrayList<Inventory>();
		
		try {
			
			//String connectionURL = "jdbc:mysql://connnection_change_here"; // students is my database name
			//Connection con = null;
			DbCon dbcon = new DbCon();
			Connection con = (Connection) dbcon.connectionmanager();
			//Class.forName("com.mysql.jdbc.Driver").newInstance();
			// "password_change_here"
			//con = (Connection) DriverManager.getConnection(connectionURL, "User_id_change_here", "password_change_here");
			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement("Select * from inventory where book_title like ? ");

			ps.setString(1,"%"+ bookName +"%");
		
			// preparedStmt.execute();

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				//rtn = "sucess";
			Inventory invObj = new Inventory();	
				invObj.setBookid(rs.getInt("bookid"));
				invObj.setIsbn_number(rs.getString("isbn_number"));
				invObj.setBook_title(rs.getString("book_title"));
				invObj.setBook_type(rs.getString("book_type"));
				invObj.setDepartment(rs.getString("department"));
				invObj.setAuther_name(rs.getString("auther_name"));
				invObj.setEdition(rs.getString("edition"));
				invObj.setQuantity(rs.getString("quantity"));
				invObj.setPrice(rs.getString("price"));
				//invObj.setPages_number(rs.getString("pages_number"));
				invObj.setPublisher(rs.getString("publisher"));
				invObj.setAvailability(checkAvailability(rs.getInt("bookid")));
				System.out.println(invObj);
				arrayOfInv.add(invObj);
				
//				rs.getString("isbn_number")
//				rs.getString("book_title")
//				rs.getString("book_type")
//				rs.getString("department")
//				rs.getString("auther_name")
//				rs.getString("edition")
//				rs.getString("quantity")
//				rs.getString("price")
//				rs.getString("pages_number")
//				rs.getString("publisher")
				
//				CREATE TABLE seproject.inventory (
//						 
//					    bookid int auto_increment,
//					    isbn_number varchar(20)  unique,
//					    book_title varchar(20),
//					     book_type varchar(20) not null ,
//					     department varchar(20)  ,
//					     auther_name varchar(20)  not null,
//					     edition varchar(20)  not null,
//					     quantity varchar(20)  ,
//					     price varchar(20)  ,
//					     pages_number varchar(20)  ,
//					     publisher varchar(20)  ,
//					     primary key (bookid)
//					    
//					);
			}
			if(!rs.next()){
				rtn = "failed";
				//write code for fail case
			}
			con.close();

		} catch (Exception ex) {
			System.out.println("Error ->" + ex.getMessage());
		} finally {

			// System.out.close();
		}
		return arrayOfInv;
	}
	
	
	public String checkAvailability(int bookid) {
		try {

			//String connectionURL = "jdbc:mysql://connnection_change_here"; // students is my database name
			//Connection con = null;
			DbCon dbcon = new DbCon();
			Connection con = (Connection) dbcon.connectionmanager();
			//Class.forName("com.mysql.jdbc.Driver").newInstance();
			// "password_change_here"
			//con = (Connection) DriverManager.getConnection(connectionURL, "User_id_change_here", "password_change_here");
			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement("Select quantity,reserved_quantity from inventory where bookid=? ");

			ps.setInt(1, bookid);
		
			// preparedStmt.execute();

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				if ((rs.getInt(1)-rs.getInt(2))>0) {
					System.out.println(rs.getInt(1));
					System.out.println(rs.getInt(2));

				rtn = "Available";
				}else {
					rtn = "Reserved";
				}
			} else {
				rtn = "Problem";
			}
			
			con.close();

		} catch (Exception ex) {
			System.out.println("Error ->" + ex.getMessage());
		} finally {

			// System.out.close();
		}
		System.out.println("rtn"+rtn);
		return rtn;
	}
	public ArrayList<Inventory> getBookClaimTableObjectArray () {
		ArrayList<Inventory> arrayOfInv = new ArrayList<Inventory>();
		
		try {
			
			//String connectionURL = "jdbc:mysql://connnection_change_here"; // students is my database name
			DbCon dbcon = new DbCon();
			Connection con = (Connection) dbcon.connectionmanager();
			//Class.forName("com.mysql.jdbc.Driver").newInstance();
			// "password_change_here"
			//con = (Connection) DriverManager.getConnection(connectionURL, "User_id_change_here", "password_change_here");
			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement("Select * from bookClaim  ");

		//	ps.setString(1, bookName);
		
			// preparedStmt.execute();

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				//rtn = "sucess";
			Inventory invObj = new Inventory();	
				//invObj.setBookid(rs.getInt("bookid"));
				invObj.setIsbn_number(rs.getString("isbn_number"));
				invObj.setBook_title(rs.getString("book_title"));
				invObj.setBook_type(rs.getString("book_type"));
				invObj.setDepartment(rs.getString("department"));
				invObj.setAuther_name(rs.getString("auther_name"));
				invObj.setEdition(rs.getString("edition"));
				invObj.setQuantity(rs.getString("quantity"));
				invObj.setPrice(rs.getString("price"));
				//invObj.setPages_number(rs.getString("pages_number"));
				invObj.setPublisher(rs.getString("publisher"));
				invObj.setFirstnameofbookrequester(rs.getString("firstname"));
				//invObj.setAvailability(checkAvailability(rs.getInt("bookid")));
				System.out.println(invObj);
				arrayOfInv.add(invObj);
				
				
//				rs.getString("isbn_number")
//				rs.getString("book_title")
//				rs.getString("book_type")
//				rs.getString("department")
//				rs.getString("auther_name")
//				rs.getString("edition")
//				rs.getString("quantity")
//				rs.getString("price")
//				rs.getString("pages_number")
//				rs.getString("publisher")
				
//				CREATE TABLE seproject.inventory (
//						 
//					    bookid int auto_increment,
//					    isbn_number varchar(20)  unique,
//					    book_title varchar(20),
//					     book_type varchar(20) not null ,
//					     department varchar(20)  ,
//					     auther_name varchar(20)  not null,
//					     edition varchar(20)  not null,
//					     quantity varchar(20)  ,
//					     price varchar(20)  ,
//					     pages_number varchar(20)  ,
//					     publisher varchar(20)  ,
//					     primary key (bookid)
//					    
//					);
			}
			if(!rs.next()){
				rtn = "failed";
				//write code for fail case
			}
			con.close();

		} catch (Exception ex) {
			System.out.println("Error ->" + ex.getMessage());
		} finally {

			// System.out.close();
		}
		return arrayOfInv;
	}
}
