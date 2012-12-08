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

public interface ISMTPClient {
    
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
           );
}
