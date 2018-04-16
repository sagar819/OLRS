package com.model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class Code {
	String Code = null;
	DbCon con = new DbCon();
	Connection conn = (Connection) con.connectionmanager();

	public int codegenrator(String username, String password) throws SQLException {
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement("Select id from user where username=?");
		ps.setString(1, username);

		System.out.println("inside code generator");
		ResultSet rs = ps.executeQuery();
		rs.next();
		int foundType = rs.getInt(1);
		if (foundType > -1) {
			int state = InserCode(foundType);
			conn.close();
			return state;
		} else
			conn.close();
			return 0;
	}

	public int InserCode(int id) throws SQLException {
		String ident = Integer.toString(id);
		String md1 = mdfive(ident);
		Random rand =new Random();
		int value = rand.nextInt(5000000);
		String str = Integer.toString(value);
		String Code1 = mdfive(str);
		String Code2 = mdfive(Code1);
		Code = md1 + Code1 + Code2;

		PreparedStatement ps = (PreparedStatement) conn.prepareStatement("select code_ver from  auth where id =?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		System.out.println("sdadasdasdasdasd");
		//System.out.println(foundType);
		if (	rs.next()) {
			PreparedStatement ps1 = (PreparedStatement) conn.prepareStatement("update auth set code_ver =? where id=?");
			ps1.setString(1,Code);
			ps1.setInt(2, id);
			ps1.executeUpdate();
			ps1.close();
			PreparedStatement ps2 = (PreparedStatement) conn.prepareStatement("update auth set state =? where id=?");
			ps2.setInt(1,0);
			ps2.setInt(2, id);
			ps2.executeUpdate();
			ps2.close();
			conn.close();
		}
		else {
		PreparedStatement ps1 = (PreparedStatement) conn.prepareStatement("insert into auth(id,code_ver) values(?,?)");

		ps1.setInt(1, id);
		ps1.setString(2, Code);
		ps1.executeUpdate();
		ps1.close();
		PreparedStatement ps2 = (PreparedStatement) conn.prepareStatement("update auth set state =? where id=?");
		ps2.setInt(1,0);
		ps2.setInt(2, id);
		ps2.executeUpdate();
		ps2.close();
		conn.close();
		}
		return 1;
	}

	public String mdfive(String test) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(test.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			String hashtext = number.toString(16);
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}

	}
}
