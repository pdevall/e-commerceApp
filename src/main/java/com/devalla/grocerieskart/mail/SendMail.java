package com.devalla.grocerieskart.mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
  
public class SendMail  
{  
 public static void main(String [] args){  
      String to = "devallap@yahoo.com";//change accordingly  
      String from = "ricoh.meta@gmail.com";//change accordingly  
      String host = "smtp.centurylink.net";//or IP address  
  
     //Get the session object  
      Properties properties = System.getProperties();  
      properties.setProperty("mail.smtp.host", host); 
      properties.setProperty("mail.smtp.port", "587");
     //properties.put("mail.smtps.auth", "true");
      Authenticator auth = new Authenticator() {
    	  
    	  protected PasswordAuthentication getPasswordAuthentication() {
              return new PasswordAuthentication(
                 "deepcentury@centurylink.net", "Prags0509");
              
           }
	};
      //properties.setProperty("mail.smtp.user", "devallapradeep"); 
      Session session = Session.getDefaultInstance(properties, auth);  
  
     //compose the message  
      try{  
         MimeMessage message = new MimeMessage(session);  
         message.setFrom(new InternetAddress(from));  
         message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
         message.setSubject("Ping");  
         message.setText("Hello, this is example of sending email  ");  
  
         // Send message  
         Transport.send(message);  
         System.out.println("message sent successfully....");  
  
      }catch (MessagingException mex) {mex.printStackTrace();}  
   }  
}  