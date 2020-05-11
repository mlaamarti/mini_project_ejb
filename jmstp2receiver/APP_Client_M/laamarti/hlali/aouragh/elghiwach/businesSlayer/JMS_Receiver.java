package laamarti.hlali.aouragh.elghiwach.businesSlayer;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;

import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

import javax.jms.MessageConsumer;
import javax.jms.MessageListener;

/**
 *
 * @author NFAOUI
 */
public class JMS_Receiver implements MessageListener {
	public JMS_Receiver() throws Exception {
		System.out.println("Begin");
		//Initialize JNDI context using our class ClientUtil
        Context ctx = Client_U.getInitialContext();
		ConnectionFactory connectionFactory = (ConnectionFactory) ctx.lookup("jms/CF");
		Destination destination = (Destination) ctx.lookup("jms/Des");
		Connection connetion = connectionFactory.createConnection();
		Session session = connetion.createSession(false, Session.AUTO_ACKNOWLEDGE);
		MessageConsumer messageConsumer = session.createConsumer(destination);
		messageConsumer.setMessageListener(this);
		connetion.start();
		System.out.println("Ok connection strats");
		connetion.stop();
	}

	public void onMessage(Message arg) {
		try {
			// traitement du message reçu
			System.out.println("appel automatique de onMessage()");
			TextMessage message = (TextMessage) arg;
			System.out.println(message.getText());
		} catch (Exception ex) {
		}
	}

	public static void main(String[] args) throws Exception {
		new JMS_Receiver();
	}
}
