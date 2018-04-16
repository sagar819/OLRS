package com.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class AuthSignup {
	DbCon con = new DbCon();
	Connection conn = (Connection) con.connectionmanager();

	public Boolean authenticate(String username) throws SQLException {
		// DbCon con = new DbCon();
		// Connection conn =(Connection) con.connectionmanager();
		String uname = username;
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement("select username from user where username=?");

		boolean result = false;
		try {
			ps.setString(1, uname);

			ResultSet rs = ps.executeQuery();

			if (!rs.next()) {

				result = true;
			} else {
				result = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public Boolean DbInsert(String username, String fname, String lname, String email, String password, String role)
			throws SQLException {
		Boolean result;
		PreparedStatement ps = (PreparedStatement) conn
				.prepareStatement("insert into user(username,fname,lname,email,password,role) values(?,?,?,?,?,?)");

		ps.setString(1, username);
		ps.setString(2, fname);
		ps.setString(3, lname);
		ps.setString(4, email);
		ps.setString(5, password);
		ps.setString(6, role);

		boolean rs = ps.execute();
		System.out.println("connection closed");
		if (!rs) {
			System.out.println(rs);
			System.out.println("in auth servet");

			result = true;
		} else {
			result = false;
		}

		return result;

	}
}