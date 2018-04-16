package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class DbCon
 */
@WebServlet("/DbCon")
public class DbCon extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Connection connectionmanager() {
		Connection con = null;
		try {
			String connectionURL = "jdbc:mysql://den1.mysql2.gear.host/mydb7";
			//String connectionURL = "jdbc:mysql://localhost/seproject";
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = (Connection) DriverManager.getConnection(connectionURL, "mydb7", "MacBookAir@Team_1");
			//con = (Connection) DriverManager.getConnection(connectionURL, "root", "Password123");
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}
}