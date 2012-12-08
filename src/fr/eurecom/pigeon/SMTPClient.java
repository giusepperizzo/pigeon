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

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SMTPClient implements ISMTPClient {
    
    public boolean send(    
                        final String username, 
                        final String password, 
                        String server, 
                        String from, 
                        String to, 
                        String cc,
                        String bcc, 
                        String subject, 
                        String text,
                        String filename,
                        Boolean validateCertificate
                       ) 
    {
        Properties props = new Properties();
        props.put("mail.smtp.host", server);  
        
        Session session = Session.getDefaultInstance(props, null);
 
        try { 
            MimeMessage message = new MimeMessage(session);
            message.setSentDate(new Date());
            message.setHeader("X-Mailer", "pigeon-v0.1");
            
            message.setFrom(new InternetAddress(from));                       
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            if(cc != null) message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));
            if(bcc != null) message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(bcc));
            message.setSubject(subject);
            
            if(filename == null)
                message.setText(text, "utf-8");
            
            else {
                // create and fill the first message part
                MimeBodyPart body = new MimeBodyPart();
                body.setText(text, "utf-8");
    
                // create the second message part
                MimeBodyPart attachment = new MimeBodyPart();
                // attach the file to the message
                FileDataSource fds = new FileDataSource(filename);
                attachment.setDataHandler(new DataHandler(fds));
                attachment.setFileName(fds.getName());
    
                // create the Multipart and add its parts to it
                Multipart mp = new MimeMultipart();
                mp.addBodyPart(body);
                mp.addBodyPart(attachment);
    
                // add the Multipart to the message
                message.setContent(mp);
            }
                        
            Transport.send(message);
 
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return true;     
    }
}
