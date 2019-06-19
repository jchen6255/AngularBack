package com.java.controller;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import com.java.dto.User;

public class AmazonSES {

	static Logger logger= Logger.getLogger(AmazonSES.class);
    // Replace sender@example.com with your "From" address.
    // This address must be verified.
    static final String FROM = "tsaisan@gmail.com";
    static final String FROMNAME = "Service";
	
    // Replace recipient@example.com with a "To" address. If your account 
    // is still in the sandbox, this address must be verified.
    static String TO = "tsaisan@gmail.com";
    
    // Replace smtp_username with your Amazon SES SMTP user name.
    static final String SMTP_USERNAME = "AKIA3MCN6VIFUTZOPDUR";
    
    // Replace smtp_password with your Amazon SES SMTP password.
    static final String SMTP_PASSWORD = "BHF6iLexfhRz/IIABavXxYUkd3A2lwUGXUcUJY94N0eZ";
    
    // The name of the Configuration Set to use for this message.
    // If you comment out or remove this variable, you will also need to
    // comment out or remove the header below.
//    static final String CONFIGSET = "ConfigSet";
    
    // Amazon SES SMTP host name. This example uses the US West (Oregon) region.
    // See https://docs.aws.amazon.com/ses/latest/DeveloperGuide/regions.html#region-endpoints
    // for more information.
    static final String HOST = "email-smtp.us-east-1.amazonaws.com";
    
    // The port you will connect to on the Amazon SES SMTP endpoint. 
    static final int PORT = 587;
    
    static final String SUBJECT = "PostCraft Reset Password";
    
    static String BODY;
    
    public void setResetEmail(User user) {
    	
        // Replace recipient@example.com with a "To" address. If your account 
        // is still in the sandbox, this address must be verified.
        TO = user.getEmail();
      
        BODY= String.join(
        	    System.getProperty("line.separator"),
        	    "<h1>Hi "+user.getUsername()+", your new password is:</h1>",
        	    "<p>"+ user.getPassword() +"</p>", 
        	    "<a href='http://localhost:9000/Project2/login.do'>Login</a>"
        	    //Include link to reset password page reset_login.html
        	);
        // The name of the Configuration Set to use for this message.
        // If you comment out or remove this variable, you will also need to
        // comment out or remove the header below.
//        static final String CONFIGSET = "ConfigSet";
    	
    }

    public void sendResetEmail() throws MessagingException{

        // Create a Properties object to contain connection configuration information.
    	Properties props = System.getProperties();
    	props.put("mail.transport.protocol", "smtp");
    	props.put("mail.smtp.port", PORT); 
    	props.put("mail.smtp.starttls.enable", "true");
    	props.put("mail.smtp.auth", "true");

        // Create a Session object to represent a mail session with the specified properties. 
    	Session session = Session.getDefaultInstance(props);

        // Create a message with the specified information. 
        MimeMessage msg = new MimeMessage(session);
        try {
			msg.setFrom(new InternetAddress(FROM,FROMNAME));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
	        msg.setSubject(SUBJECT);
	        msg.setContent(BODY,"text/html");
		} catch (UnsupportedEncodingException | MessagingException e) {
			// TODO Auto-generated catch block
			System.out.println("MimeMessage exception");
		}
        
        
        // Add a configuration set header. Comment or delete the 
        // next line if you are not using a configuration set
//        msg.setHeader("X-SES-CONFIGURATION-SET", CONFIGSET);
            
        // Create a transport.
        Transport transport = session.getTransport();
                    
        // Send the message.
        try
        {
            System.out.println("Sending...");
            logger.info("Sending...");
            
            // Connect to Amazon SES using the SMTP username and password you specified above.
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
        	
            // Send the email
            transport.sendMessage(msg, msg.getAllRecipients());
            System.out.println("Email sent!");
            logger.info("Email sent!");
        }
        catch (Exception ex) {
            System.out.println("The email was not sent.");
            logger.info("The email was not sent.");
            System.out.println("Error message: " + ex.getMessage());
        }
        finally
        {
            // Close and terminate the connection.
            transport.close();
        }
    }
    
}
