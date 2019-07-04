package com.jin.httpclient;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

public class CertsSSLClient extends DefaultHttpClient{
	 public CertsSSLClient() throws Exception{  
	        super();  
//	        SSLContext ctx = SSLContext.getInstance("TLS");  
//	        ctx.init(null, new TrustManager[]{tm}, null);  
//	        SSLSocketFactory ssf = new SSLSocketFactory(ctx,SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);  
//	        ClientConnectionManager ccm = this.getConnectionManager();  
	        
	        ClientConnectionManager ccm = this.getConnectionManager();  
	        SchemeRegistry sr = ccm.getSchemeRegistry();  
	        sr.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
	        sr.register(new Scheme("https", createAdditionalCertsSSLSocketFactory(), 443));
	        final HttpParams params = new BasicHttpParams();
	        final ThreadSafeClientConnManager cm = new ThreadSafeClientConnManager(params,sr);
	 }
	 
	 protected org.apache.http.conn.ssl.SSLSocketFactory createAdditionalCertsSSLSocketFactory() {
		    try {
		        final KeyStore ks = KeyStore.getInstance("BKS");

		        // the bks file we generated above
		        File file = new File("D:/download/mystore.bks");
		        final InputStream in = new FileInputStream(file);  
		        try {
		            // don't forget to put the password used above in strings.xml/mystore_password
		            ks.load(in, "111111".toCharArray());
		        } finally {
		            in.close();
		        }

		        return new AdditionalKeyStoresSSLSocketFactory(ks);

		    } catch( Exception e ) {
		        throw new RuntimeException(e);
		    }
		}
}
