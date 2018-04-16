package com.model;

public class user {
	public static String username;
	public static String firstname;
	public static String lastname;
	public static String role;
	public static String email;
	public static int Id;
	
	public String getemail() {
		return email;
	}

	public  void setemail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}

	public  void setUsername(String username) {
		this.username = username;
	}
	public String getfirstname() {
		return firstname;
	}

	public void setfirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getlastname() {
		return lastname;
	}

	public void setlastname(String lastname) {
		this.lastname = lastname;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getId() {
		return Id;
	}

	public void setId(int Id) {
		this.Id = Id;
	}

}

