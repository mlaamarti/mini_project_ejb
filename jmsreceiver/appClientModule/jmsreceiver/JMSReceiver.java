package jmsreceiver;

import javax.imageio.ImageWriter;
import javax.jms.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;
import javax.naming.Context;
import javax.naming.NamingException;

import jmsreceiver.ClientUtil;

public class JMSReceiver implements MessageListener {

	public JMSReceiver() throws Exception{
Context ctx = ClientUtil.getInitialContext();
		
		ConnectionFactory fabrique = (ConnectionFactory)ctx.lookup("jms/CF1"); 
		Destination destination = (Destination)ctx.lookup("DES1"); 
		
		
		
		Connection connexion = fabrique.createConnection();
		Session session = connexion.createSession(false, Session.AUTO_ACKNOWLEDGE); 
		MessageConsumer messageconsumer = session.createConsumer(destination); 
		messageconsumer.setMessageListener(this);
		connexion.start();
		System.out.println("connection starts");
		connexion.stop();

		
	}

	@Override
	public void onMessage(Message arg) {
		try {
			
			
			// on lit l'image reçue
			
			BytesMessage bytesMessage = (BytesMessage) arg;
		      byte[] data = new byte[(int) bytesMessage.getBodyLength()];
		      bytesMessage.readBytes(data);
		     
		    /* on enregistre l'image sous forme jpg (getIntProperty() pour ajouter un entier
		      à la fin du nom d'image pour éviter le conflit de noms*/
		    
		      
		      File file = new File("image"+bytesMessage.getIntProperty(null)+".jpg");
		      FileOutputStream fos = null;
		      fos = new FileOutputStream(file);
	      
	            fos.write(data);

		      
		    
		     
		      
		      
		      
		     

		}
		catch (Exception ex)
		{}
	}
	
	public static void main(String[] args) throws Exception  {
		new JMSReceiver();
	}
}
