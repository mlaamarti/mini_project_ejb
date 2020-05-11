package laamarti.hlali.aouragh.elghiwach.Businesslayer;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Message-Driven Bean implementation class for: ReadMDB
 */
@MessageDriven(
		activationConfig = { 
				@ActivationConfigProperty(propertyName = "destination", propertyValue = "Des"), 
				@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
		}, 
		mappedName = "jms/Des")
public class ReadMDB implements MessageListener {

    public ReadMDB() {
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
       
    	System.out.println("Appel automatique de onMessage() from ReadMDB");
        TextMessage msg = (TextMessage) message;
        try {
            System.out.println(msg.getText());
        } catch (JMSException ex) {
            Logger.getLogger(ReadMDB.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

}
