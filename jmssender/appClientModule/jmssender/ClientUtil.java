package jmssender;
import java.util.Properties;

import javax.naming.*;


public class ClientUtil {
	public static Context getInitialContext() throws NamingException {
		
		Properties prop = new Properties();
		prop.put("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
		prop.put("java.naming.factory.url.pkgs","com.sun.enterprise.naming");
		prop.put("java.naming.provider.url","iiop://localhost:1050/"); 
		
		Context ctx = new InitialContext(prop);
		
		return ctx;
	}
	
		
	} 


