//   pigeon - Library for sending emails using SSL and TLS connection
//
//   Copyright 2012 held by the author
//
//   Authors:
//      Giuseppe Rizzo <giuse.rizzo@gmail.com>
//
// This program is free software; you can redistribute it and/or modify it
// under the terms of the GNU General Public License published by
// the Free Software Foundation, either version 3 of the License, or (at 
// your option) any later version. See the file Documentation/GPL3 in the
// original distribution for details. There is ABSOLUTELY NO warranty.

package fr.eurecom.pigeon;

import java.security.Security;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SMTPTLSClient {

    public static boolean send( final String username, final String password, 
                                String server, String from, String to, String cc,
                                String bcc, String subject, String text, 
                                boolean validateCertificate ) 
    {

        if (!validateCertificate)
            Security.setProperty(   "ssl.SocketFactory.provider",
                                    "fr.eurecom.pigeon.certificate.MySSLSocketFactory"
                                );  
        
        Properties props = new Properties();     
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", server);
        props.put("mail.smtp.port", "587");
 
        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });
 
        try { 
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
                       
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            if(cc != null) message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));
            if(bcc != null) message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(bcc));
            
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);
 
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

}
