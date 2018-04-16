package com.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AuthDAOLib
 */
@WebServlet("/AuthDAOLib")
public class AuthDAOLib extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AuthDAOLib() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static Connection connectionmanager() {

		DbCon conn = new DbCon();
		Connection con = conn.connectionmanager();
		return con;
	}

	public static boolean checkuserpassword(String username, String password, String role) throws SQLException {
		boolean rtn;
		
		Connection conn = (Connection) connectionmanager();
		PreparedStatement ps = (PreparedStatement) conn
				.prepareStatement("select * from user where username=? and password=? and role=? ");

		ps.setString(1, username);
		ps.setString(2, password);
		ps.setString(3, role);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {

			rtn = true;
		} else {
			rtn = false;
		}
		conn.close();
		return rtn;
	}
	

	public static boolean enterNewBook(String isbn, String tittle, String type, String department, String author,
			String edition, int quantity, String publisher, String price) throws SQLException {
		boolean result;
		Connection conn = (Connection) connectionmanager();
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(
				"insert into inventory(isbn_number,book_title,book_type,department,auther_name,edition,quantity,publisher,price) values(?,?,?,?,?,?,?,?,?)");

		ps.setString(1, isbn);
		ps.setString(2, tittle);
		ps.setString(3, type);
		ps.setString(4, department);
		ps.setString(5, author);
		ps.setString(6, edition);
		ps.setInt(7, quantity);
		ps.setString(8, publisher);
		ps.setString(9, price);
		boolean rs = ps.execute();
		if (!rs) {
			result = true;
		} else {
			result = false;
		}
		conn.close();
		return result;
	}

	public static boolean reservation_book(int book_id, int user_id, Date current, Date due) throws SQLException {
		boolean result = false;
		java.sql.Date sqlDate = new java.sql.Date(current.getTime());
		Connection conn = (Connection) connectionmanager();
		java.sql.Date due_date = new java.sql.Date(due.getTime());
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(
				"insert into reserve_register(bookid,userid_reserved,issue_date,due_date) values(?,?,?,?)");

		ps.setInt(1, book_id);
		ps.setInt(2, user_id);
		ps.setDate(3, sqlDate);
		ps.setDate(4, due_date);

		boolean rs = ps.execute();
		if (!rs) {
			PreparedStatement ps1 = (PreparedStatement) conn
					.prepareStatement("update inventory set reserved_quantity =reserved_quantity + 1 where bookid=?");
			ps1.setInt(1, book_id);
			int rs1 = ps1.executeUpdate();
			System.out.println("thsi is updating the data base for registration table");
			if (rs1 > -1) {
				result = true;
			} else {
				result = false;
			}
		}
		conn.close();
		return result;
	}

	public static void reservation_book_(int book_id, int user_id, Date current, Date due) throws SQLException {
		java.sql.Date sqlDate = new java.sql.Date(current.getTime());
		Connection conn = (Connection) connectionmanager();
		java.sql.Date due_date = new java.sql.Date(due.getTime());
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(
				"insert into reserve_register(bookid,userid_reserved,issue_date,due_date) values(?,?,?,?)");

		ps.setInt(1, book_id);
		ps.setInt(2, user_id);
		ps.setDate(3, sqlDate);
		ps.setDate(4, due_date);
		ps.execute();
		conn.close();
	}

	public static boolean wait_list_add(int book_id, int user_id, Date current) throws SQLException {
		boolean result = false;
		java.sql.Date sqlDate = new java.sql.Date(current.getTime());
		Connection conn = (Connection) connectionmanager();
		PreparedStatement psc = (PreparedStatement) conn
				.prepareStatement("select max(current_wait_list_no) from waitlist_register where bookid = ?");
		psc.setInt(1, book_id);
		ResultSet rsc = psc.executeQuery();
		rsc.next();
		int number_current = rsc.getInt(1);
		if (number_current >= 0) {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(
					"insert into waitlist_register(bookid,userid_reserved,requested_date,wait_list_no,current_wait_list_no) values(?,?,?,?,?)");

			ps.setInt(1, book_id);
			ps.setInt(2, user_id);
			ps.setDate(3, sqlDate);
			ps.setInt(4, number_current + 1);
			ps.setInt(5, number_current + 1);

			boolean rs = ps.execute();

			if (!rs) {
				result = true;
			} else {
				result = false;
			}

		}
		conn.close();
		return result;

	}

	public int getbooknumber(String bookingid) throws SQLException {
		int bookingid_ = Integer.parseInt(bookingid);
		Connection conn = (Connection) connectionmanager();
		PreparedStatement ps = (PreparedStatement) conn
				.prepareStatement("select quantity from special_reserve_register where sp_reserve_id =? and return_date is null");
		ps.setInt(1, bookingid_);

		ResultSet rs = ps.executeQuery();
		rs.next();
		int bookid = rs.getInt(1);
		conn.close();
		return bookid;
	}

	public int numberofNumId() throws SQLException {
		Connection conn = (Connection) connectionmanager();
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement("select count(*) from nuquestion");
		ResultSet rs = ps.executeQuery();
		rs.next();
		int nummcq = rs.getInt(1);
		conn.close();
		System.out.println(nummcq);
		return nummcq;
	}

	public Boolean return_udate(String bookingid) throws SQLException {
		Date current = new Date();
		java.sql.Date sqlDate = new java.sql.Date(current.getTime());
		int bookingid_ = Integer.parseInt(bookingid);
		Connection conn = (Connection) connectionmanager();
		PreparedStatement ps = (PreparedStatement) conn
				.prepareStatement("update reserve_register set return_date =? where reserve_id =?");
		ps.setDate(1, sqlDate);
		ps.setInt(2, bookingid_);

		boolean rs = ps.execute();
		conn.close();
		return rs;
	}

	public Boolean reduce_waitlist(int bookingid) throws SQLException {
		Boolean ret = false;
		Connection conn = (Connection) connectionmanager();
		PreparedStatement ps = (PreparedStatement) conn
				.prepareStatement("select waitlist_id from waitlist_register where bookid =?");
		ps.setInt(1, bookingid);
		ResultSet rs = ps.executeQuery();
		rs.last();
		int numberOfRows = rs.getRow();
		if (numberOfRows > 0) {
			rs.beforeFirst();
			while (rs.next()) {
				int id = rs.getInt(1);
				System.out.println(id);
				PreparedStatement ps1 = (PreparedStatement) conn.prepareStatement(
						"update waitlist_register set current_wait_list_no =current_wait_list_no - 1 where waitlist_id=?");
				ps1.setInt(1, id);
				ps1.executeUpdate();
			}
			ret = true;
		} else {
			ret = false;
		}
		conn.close();
		return ret;
	}

	public int getbookid(String bookingid) throws SQLException {
		int bookingid_ = Integer.parseInt(bookingid);
		Connection conn = (Connection) connectionmanager();
		PreparedStatement ps = (PreparedStatement) conn
				.prepareStatement("select bookid from reserve_register where reserve_id =?");
		ps.setInt(1, bookingid_);

		ResultSet rs = ps.executeQuery();
		rs.next();
		int bookid = rs.getInt(1);
		conn.close();
		return bookid;
	}

	public Boolean remove_waitlist(int bookid) throws SQLException {
		SendEmail snedmail =new SendEmail();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date current = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(current);
		c.add(Calendar.DATE, 25);
		Date due = c.getTime();
		Connection conn = (Connection) connectionmanager();
		PreparedStatement ps = (PreparedStatement) conn
				.prepareStatement("select userid_reserved from waitlist_register where current_wait_list_no =?");
		ps.setInt(1, 0);
		ResultSet rs = ps.executeQuery();
		rs.next();
		int id = rs.getInt(1);
		reservation_book_(bookid,id, current, due);
		PreparedStatement ps1 = (PreparedStatement) conn
				.prepareStatement("delete from waitlist_register where current_wait_list_no=0;");
		boolean rs1 = ps1.execute();
		snedmail.sendit_wait_reserv(id,"waitlist",bookid);
		conn.close();
		return rs1;
	}

	public Boolean check_return(String bookingid) throws SQLException {
		boolean rtn = false;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date current = new Date();
		int bookingid_ = Integer.parseInt(bookingid);
		Connection conn = (Connection) connectionmanager();
		PreparedStatement ps = (PreparedStatement) conn
				.prepareStatement("select return_date from reserve_register where reserve_id =?");
		ps.setInt(1, bookingid_);

		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			if (rs.getDate(1) != null) {
				System.out.println(rs.getDate(1));
				rtn = false;
			} else {
				rtn = true;
			}
		} else {
			rtn = false;
		}

		conn.close();
		return rtn;
	}

	public void reduce_inventory(int bookid) throws SQLException {
		Connection conn = (Connection) connectionmanager();
		PreparedStatement ps1 = (PreparedStatement) conn
				.prepareStatement("update inventory set reserved_quantity =reserved_quantity - 1 where bookid=?");
		ps1.setInt(1, bookid);
		ps1.executeUpdate();
		conn.close();
	}

	public static Boolean checkbookholding(int book_id, int user_id) throws SQLException {
		Connection conn = (Connection) connectionmanager();
		Boolean rtn = false;
		PreparedStatement ps1 = (PreparedStatement) conn.prepareStatement(
				"select return_date from reserve_register where bookid=? and userid_reserved=? and return_date is null");
		ps1.setInt(1, book_id);
		ps1.setInt(2, user_id);
		ResultSet rs = ps1.executeQuery();
		if (!rs.next()) {
			rtn = true;
		} else {

			rtn = false;
		}

		conn.close();
		return rtn;
	}

	// sagar
	public static int getReservedQty(String bookingid) throws SQLException {
		// boolean retBoolean=false;
		int reserved_qty = 99999;
		int bookingid_ = Integer.parseInt(bookingid);
		Connection conn = (Connection) connectionmanager();
		PreparedStatement ps = (PreparedStatement) conn
				.prepareStatement("select reserved_quantity from inventory where bookid =?");
		ps.setInt(1, bookingid_);

		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			// return true;

			reserved_qty = rs.getInt(1);
		}
		conn.close();
		return reserved_qty;
	}

	// sagar
	public static void deleteBookFromInv(String bookingid) throws SQLException {
		int bookingid_ = Integer.parseInt(bookingid);
		Connection conn = (Connection) connectionmanager();
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement("Delete from inventory where bookid=?");
		ps.setInt(1, bookingid_);

		ps.execute();
		// rs.next();
		// int bookid=rs.getInt(1);
		conn.close();
		// return bookid;
	}

	public static boolean checkwaitliststatus(int book_id, int user_id) throws SQLException {
		Connection conn = (Connection) connectionmanager();
		Boolean rtn = false;
		PreparedStatement ps1 = (PreparedStatement) conn
				.prepareStatement("select * from waitlist_register where bookid=? and userid_reserved=?");
		ps1.setInt(1, book_id);
		ps1.setInt(2, user_id);
		ResultSet rs = ps1.executeQuery();
		if (!rs.next()) {
			rtn = true;
		} else {

			rtn = false;
		}
		conn.close();
		return rtn;
	}

	public static String getbookname(int bookid) throws SQLException {
		DbCon con = new DbCon();
		String bookname = null;
		Connection conn = (Connection) con.connectionmanager();
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) conn.prepareStatement("Select book_title from inventory where bookid=?");
			ps.setInt(1, bookid);
			ResultSet rs = ps.executeQuery();
			rs.next();
			bookname = rs.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conn.close();
		return bookname;
	}

	public int getbookidspecial(String bookingid)throws SQLException {
		int bookingid_ = Integer.parseInt(bookingid);
		Connection conn = (Connection) connectionmanager();
		PreparedStatement ps = (PreparedStatement) conn
				.prepareStatement("select bookid from special_reserve_register where sp_reserve_id =?");
		ps.setInt(1, bookingid_);

		ResultSet rs = ps.executeQuery();
		rs.next();
		int bookid = rs.getInt(1);
		conn.close();
		return bookid;
	}

	public void check_return_special(String bookingid) throws SQLException {
		Date current = new Date();
		java.sql.Date sqlDate = new java.sql.Date(current.getTime());
		int bookingid_ = Integer.parseInt(bookingid);
		Connection conn = (Connection) connectionmanager();
		PreparedStatement ps = (PreparedStatement) conn
				.prepareStatement("update special_reserve_register set return_date =? where sp_reserve_id =?");
		ps.setDate(1, sqlDate);
		ps.setInt(2, bookingid_);

		ps.execute();
		conn.close();
		
	}
	public static boolean enterBookClaim(String isbn,String tittle,String type,String department,String author,String edition,int quantity,String publisher,String price)
			throws SQLException {
			boolean result;
			user u = new user();
			Connection conn = (Connection) connectionmanager();
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(
			"insert into bookclaim(userid,firstname,lastname,email,isbn_number,book_title,book_type,department,auther_name,edition,quantity,publisher,price) values(?,?,?,?,?,?,?,?,?,?,?,?,?)");

			ps.setInt(1,u.getId());
			ps.setString(2,u.getfirstname());
			ps.setString(3,u.getlastname());
			ps.setString(4,u.getemail());

			ps.setString(5, isbn);
			ps.setString(6, tittle);
			ps.setString(7, type);
			ps.setString(8, department);
			ps.setString(9, author);
			ps.setString(10, edition);
			ps.setInt(11, quantity);
			ps.setString(12, publisher);
			ps.setString(13, price);
			boolean rs = ps.execute();
			if (!rs) {
			result = true;
			} else {
			result = false;
			}
			conn.close();
			return result;
			}
	public static void deleteFromBookClaim(String isbn) throws SQLException {
        int bookingid_ = Integer.parseInt(isbn);
        Connection conn = (Connection) connectionmanager();
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement("Delete from bookclaim where isbn_number=?");
        ps.setInt(1, bookingid_);

        ps.execute();
        // rs.next();
        // int bookid=rs.getInt(1);
        conn.close();
        // return bookid;
}
}