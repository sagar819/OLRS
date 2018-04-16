package com.model;

public class Inventory {
	private int bookid;
	private String isbn_number;
	private String book_title;
	private String book_type;
	private String department;
	private String auther_name;
	private String edition;
	private String quantity;
	private String price;
	private String pages_number;
	private String publisher;
	private String availability;
	private String firstnameofbookrequester;
	private String availbleQty;
	public String getAvailbleQty() {
		return availbleQty;
	}
	public void setAvailbleQty(String availbleQty) {
		this.availbleQty = availbleQty;
	}
	public String getFirstnameofbookrequester() {
		return firstnameofbookrequester;
	}
	public void setFirstnameofbookrequester(String firstnameofbookrequester) {
		this.firstnameofbookrequester = firstnameofbookrequester;
	}
	public int getBookid() {
		return bookid;
	}
	public void setBookid(int bookid) {
		this.bookid = bookid;
	}
	public String getIsbn_number() {
		return isbn_number;
	}
	public void setIsbn_number(String isbn_number) {
		this.isbn_number = isbn_number;
	}
	public String getBook_title() {
		return book_title;
	}
	public void setBook_title(String book_title) {
		this.book_title = book_title;
	}
	public String getBook_type() {
		return book_type;
	}
	public void setBook_type(String book_type) {
		this.book_type = book_type;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getAuther_name() {
		return auther_name;
	}
	public void setAuther_name(String auther_name) {
		this.auther_name = auther_name;
	}
	public String getEdition() {
		return edition;
	}
	public void setEdition(String edition) {
		this.edition = edition;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPages_number() {
		return pages_number;
	}
	public void setPages_number(String pages_number) {
		this.pages_number = pages_number;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	public String getAvailability() {
		return availability;
	}
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	@Override
	public String toString() {
		return "Inventory [bookid=" + bookid + ", isbn_number=" + isbn_number + ", book_title=" + book_title
				+ ", book_type=" + book_type + ", department=" + department + ", auther_name=" + auther_name
				+ ", edition=" + edition + ", quantity=" + quantity + ", price=" + price + ", pages_number="
				+ pages_number + ", publisher=" + publisher + ", availability=" + availability + "]";
	}
	
	
	
	
	

}
