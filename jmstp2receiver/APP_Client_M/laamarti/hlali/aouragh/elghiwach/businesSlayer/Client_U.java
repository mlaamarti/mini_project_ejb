package laamarti.hlali.aouragh.elghiwach.businesSlayer;

import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;




public class Client_U {
	

	public static Context getInitialContext() throws NamingException {
		
		
		
		
		
		
		
		// Parmi autres, on peut aussi configurer les propriétés du fichier jndi par
		// programme:
		Properties prop = new Properties();
		prop.put("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
		prop.put("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
		prop.put("java.naming.provider.url", "iiop://localhost:1050/");
		Context ctx = new InitialContext(prop);
		return ctx;
		
		
		
		
		// On peut aussi configurer les propriétés du fichier jndi par programme (using
		// Hashtable)
		// Hashtable t = new Hashtable();
		// t.put("java.naming.factory.initial","com.sun.enterprise.naming.SerialInitContextFactory");
		// t.put("java.naming.factory.url.pkgs","com.sun.enterprise.naming");
		// t.put("java.naming.provider.url","iiop://localhost:1050/");
		// Context ctx = new InitialContext(t);
		// return ctx;

		/*
		 * // on peut aussi récupérer les propriétés du fichier jndi.properties situé
		 * dans le dossier du projet et qui définit le contexte jndi
		 * 
		 * Properties props = new Properties(); props.load(new
		 * FileInputStream("jndi.properties")); Context ctx = new InitialContext(props);
		 * return ctx;
		 */
	}
}
