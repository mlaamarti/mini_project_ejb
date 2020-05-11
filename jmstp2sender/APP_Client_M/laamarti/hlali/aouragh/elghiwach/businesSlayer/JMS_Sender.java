package laamarti.hlali.aouragh.elghiwach.businesSlayer;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.NamingException;


import javax.jms.MessageProducer;

/**
 *
 * @author NFAOUI
 */

public class JMS_Sender {
    public static void main(String[] args) throws NamingException, JMSException  {
        
        
        //Initialize JNDI context using our class ClientUtil
        Context ctx = Client_U.getInitialContext();
        
        ConnectionFactory fabrique = (ConnectionFactory) ctx.lookup("jms/CF");
        System.out.println("Ok");
        Destination destination = (Destination) ctx.lookup("jms/Des");
        Connection connetion = fabrique.createConnection();
        Session session = connetion.createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageProducer messageProducer = session.createProducer(destination);

        TextMessage message = session.createTextMessage();
        message.setText("Ma première application JMS, EJB MDB");
        
        messageProducer.send(message);
        connetion.close();
        System.out.print("Ok: message sent");
    }
}

