package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Servlet implementation class GenerateOtpServlet
 */
@WebServlet({ "/GenerateOtpServlet", "/generateotp", "/otp", "/generate" })
public class GenerateOtpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenerateOtpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private void sendEmail(String to, String otp)
    {
   	 Properties props = new Properties();
     props.put("mail.smtp.host", "true");
     props.put("mail.smtp.starttls.enable", "true");
     props.put("mail.smtp.host", "smtp.gmail.com");
     props.put("mail.smtp.port", "587");
     props.put("mail.smtp.auth", "true");
     //Establishing a session with required user details
     Session session = Session.getInstance(props, new javax.mail.Authenticator() {
         protected PasswordAuthentication getPasswordAuthentication() {
             return new PasswordAuthentication("harshitakhandelwal9827@gmail.com", "Engineer@1998");
         }
     });
     try {
         //Creating a Message object to set the email content
         MimeMessage msg = new MimeMessage(session);
         //Storing the comma seperated values to email addresses
//         String to = "rjagadeeswaran@yahoo.com";
         /*Parsing the String with defualt delimiter as a comma by marking the boolean as true and storing the email
         addresses in an array of InternetAddress objects*/
         InternetAddress[] address = InternetAddress.parse(to, true);
         //Setting the recepients from the address variable
         msg.setRecipients(Message.RecipientType.TO, address);
         String timeStamp = new SimpleDateFormat("yyyymmdd_hh-mm-ss").format(new Date());
         msg.setSubject("OTP for signing up : " + timeStamp);
         msg.setSentDate(new Date());
//         msg.setText("Sampel System Generated mail");
         msg.setText(otp);
         msg.setHeader("XPriority", "1");
        
         Transport.send(msg);
         System.out.println("Mail has been sent successfully");
     } catch (MessagingException mex) {
         System.out.println("Unable to send an email:\n" + mex);
     }
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//call a dao method to generate otp. for this demo, i am doing it here itsef
//		int i=Math.random() *10			//generates a random number between 0 and 1		(not including)
		String email=request.getParameter("email");
		PrintWriter out = response.getWriter();
		if(email==null || email.equals(""))
		{
			out.print("Invalid email");
			return;
		}
		long rno=(long) (Math.random()*100000);
		
		String otp=rno+"";
		//send this otp by email to email address
		sendEmail(email, otp);
		out.print(otp);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
