package com.model;

import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;


public class SendEmail {
	DbCon con = new DbCon();
	Connection conn = (Connection) con.connectionmanager();
	AuthDAOLib auth=new AuthDAOLib();
	public void sendit(int id , String domain_) {

		try{
			String domain = domain_;
			String host ="smtp.gmail.com" ;
            String user = "team1.se.project@gmail.com";
            String pass = "Team1SeProject@613";
            String to = null;
            String from = "no-reply@gmail.com";
            String subject = null;
            String messageText = null;
            boolean sessionDebug = false;
            if(domain.equals("auth")) {
        		System.out.println("inside send mail");

            	to=GetMailId(id);
            //	messageText="http://localhost:8080/Se_Proj/accveri?cd="+GetCode(id);
            	messageText="https://team-1.azurewebsites.net/accveri?cd="+GetCode(id);

            	subject="Please Verify";
            }
            else if(domain.equals("forget")) {

            	to=GetMailId(id);
        		System.out.println("inside send mail here");

           // 	messageText="http://localhost:8080/Se_Proj/resse?cd="+GetCode(id);
            	messageText="https://team-1.azurewebsites.net/resse?cd="+GetCode(id);

            	subject="Reset Password";
            }
            else if(domain.equals("notification")) {
            	to=GetMailId(id);
            	messageText=GetCode(id);
            	subject="Book Issue Update";
            }
            else if(domain.equals("alert")) {
            	to=GetMailId(id);
            	messageText=GetCode(id);
            	subject="Alert Due Date";
            }
            else if(domain.equals("username")) {
            	to=GetMailId(id);
            	messageText="Your User Name is " +GetUserName(id);
            	subject="no-reply";
            }
            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject); msg.setSentDate(new Date());
            msg.setText(messageText);

           Transport transport=mailSession.getTransport("smtp");
           transport.connect(host, user, pass);
           transport.sendMessage(msg, msg.getAllRecipients());
           transport.close();
           System.out.println("message send successfully");
        }catch(Exception ex)
        {
            System.out.println(ex);
        }
	 
	}
	
	
	public void sendit_wait_reserv(int id , String domain_, int bookid) {
			
		try{
			Date current = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(current); // Now use today date.
			
			c.add(Calendar.DATE, 25);
			Date due=c.getTime();
			String domain = domain_;
			String host ="smtp.gmail.com" ;
            String user = "team1.se.project@gmail.com";
            String pass = "Team1SeProject@613";
            String to = null;
            String from = "no-reply@gmail.com";
            String subject = null;
            String messageText = null;
            boolean sessionDebug = false;
            
            if(domain.equals("reservation")) {
            	to=GetMailId(id);
            	messageText="You reserved " + auth.getbookname(bookid) + " on " + current + " and Due date for book is " + due;
            	subject=" Message for Book Reservation";
            }
            else if(domain.equals("waitlist")) {
            	to=GetMailId(id);
            	messageText="Your waitlist for " + auth.getbookname(bookid) + " is over and book has been reserved on " + current + " and Due date for book is " + due;
            	subject=" Message for Book WaitList";

            }
            
            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject); msg.setSentDate(new Date());
            msg.setText(messageText);

           Transport transport=mailSession.getTransport("smtp");
           transport.connect(host, user, pass);
           transport.sendMessage(msg, msg.getAllRecipients());
           transport.close();
           System.out.println("message send successfully");
        }catch(Exception ex)
        {
            System.out.println(ex);
        }
	 
	}
	

	private String GetUserName(int id) throws SQLException {
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement("select username from user where id=?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		String username = rs.getString(1);
		return username;
	}

	private String GetCode(int id) throws SQLException {
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement("select code_ver from auth where id=?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		String code = rs.getString(1);
		return code;
	}

	private String GetMailId(int id) throws SQLException {
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement("select email from user where id=?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		String email = rs.getString(1);
		
		return email;
		
	}

}
