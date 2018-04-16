package com.model;

import java.sql.DriverManager;
import java.sql.ResultSet;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class resetpassword {
	String rtn = null;
	DbCon con = new DbCon();
	Connection conn = (Connection) con.connectionmanager();
	public String authreset(String username, String password, String password1) {
		try {

			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement("Select * from user where username=? and password=?");

			ps.setString(1, username);

			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				PreparedStatement psc = (PreparedStatement) conn
						.prepareStatement("update user set password =? where username=?");
				psc.setString(1, password1);
				psc.setString(2, username);
				psc.executeUpdate();
				rtn = "sucess";
			} else {
				rtn = "failed";
				// out.println("Invalid Credentials provided!!!!");
			}
			conn.close();

		} catch (Exception ex) {
			System.out.println("Error ->" + ex.getMessage());
		} finally {

			// System.out.close();
		}
		return rtn;
	}
}
