package com.model;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class AuthLogin {
	String rtn = null;
	DbCon con = new DbCon();
	public String authenticatesignin(String username, String password) {
		try {			
			Connection conn = (Connection) con.connectionmanager();

			PreparedStatement ps = (PreparedStatement) conn.prepareStatement("select * from user where username=? and password=?");

			ps.setString(1, username);
			ps.setString(2, password);
			// preparedStmt.execute();

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				rtn = "sucess";
			} else {
				rtn = "failed";
			}

		
		conn.close();
		} catch (Exception ex) {
			System.out.println("Error ->" + ex.getMessage());
		} finally {
		}
		return rtn;
	}
 public Boolean Prelogin(String username_) throws SQLException {
		Connection conn = (Connection) con.connectionmanager();

	 Boolean state = false;
  String username =username_;
	 try {		
	 PreparedStatement ps = (PreparedStatement) conn.prepareStatement("select id from user where username=?");

		ps.setString(1, username);

		ResultSet rs = ps.executeQuery();
		rs.next();
		int foundType = rs.getInt(1);
		PreparedStatement ps1 = (PreparedStatement) conn.prepareStatement("select state from auth where id=?");
		ps1.setInt(1, foundType);
		ResultSet rs1 = ps1.executeQuery();
		rs1.next();
		int foundType1 = rs1.getInt(1);
		if (foundType1 > 0) {
			conn.close();
			state = true;
		} else {
			conn.close();
			state = false;
		}
	 } catch (Exception ex) {
			System.out.println("Error ->" + ex.getMessage());
		} finally {
		}
	 return state;
}
 public user getuser(String username) throws SQLException {
		Connection conn = (Connection) con.connectionmanager();
		user e = new user();
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement("select * from  user where username=?");
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			e.setId(rs.getInt(1));
			e.setUsername(rs.getString(2));
			e.setfirstname(rs.getString(3));
			e.setlastname(rs.getString(4));
			e.setemail(rs.getString(5));
			e.setRole(rs.getString(7));
		}
		conn.close();
		return e;
	}
}

