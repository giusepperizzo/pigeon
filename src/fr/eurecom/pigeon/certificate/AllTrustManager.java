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

package fr.eurecom.pigeon.certificate;

import javax.net.ssl.*;  

import java.security.cert.CertificateException;  
import java.security.cert.X509Certificate;  
   
public class AllTrustManager implements X509TrustManager  
{  
   
    public X509Certificate [] getAcceptedIssuers()  
    {  
        return new X509Certificate[0];  
    }  
       
    public void checkClientTrusted( X509Certificate [] arg0, String arg1 )  
    throws CertificateException  
    {  
    }  
       
    public void checkServerTrusted( X509Certificate [] arg0, String arg1 )  
    throws CertificateException  
    {  
    }  
}  