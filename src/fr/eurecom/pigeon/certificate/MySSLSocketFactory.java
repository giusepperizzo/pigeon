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

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.security.SecureRandom;

import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

public class MySSLSocketFactory extends SSLSocketFactory  
{  
    private SSLSocketFactory factory;  
       
    public MySSLSocketFactory()  
    {  
        System.out.println("Untrusted SSL/TLS connection opened");  
        try  
        {  
            SSLContext sslcontext = SSLContext.getInstance( "TLS" );  
            sslcontext.init( null,  // No KeyManager required  
                             new TrustManager[]{ new AllTrustManager() }, 
                             new SecureRandom() 
                            );  
            factory = (SSLSocketFactory) sslcontext.getSocketFactory();  
               
        } catch ( Exception ex )  {  
            ex.printStackTrace();  
        }  
    }  
       
    public static SocketFactory getDefault()  
    {  
        return new MySSLSocketFactory();  
    }  
       
    public Socket createSocket( Socket socket, String s, int i, boolean flag )  
    throws IOException  
    {  
        return factory.createSocket( socket, s, i, flag );  
    }  
       
    public Socket createSocket( InetAddress inaddr, int i, InetAddress inaddr1, int j ) 
    throws IOException  
    {  
        return factory.createSocket( inaddr, i, inaddr1, j );  
    }  
       
    public Socket createSocket( InetAddress inaddr, int i ) throws IOException  
    {  
        return factory.createSocket( inaddr, i );  
    }  
       
    public Socket createSocket( String s, int i, InetAddress inaddr, int j )  
    throws IOException  
    {  
        return factory.createSocket( s, i, inaddr, j );  
    }  
       
    public Socket createSocket( String s, int i ) throws IOException  
    {  
        return factory.createSocket( s, i );  
    }  
       
    public String [] getDefaultCipherSuites()  
    {  
        return factory.getSupportedCipherSuites();  
    }  
       
    public String [] getSupportedCipherSuites()  
    {  
        return factory.getSupportedCipherSuites();  
    }  
}
