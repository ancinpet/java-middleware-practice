package jms;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Random;

import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import pojo.*;
 
public class OrderClient {

    // connection factory
    private QueueConnectionFactory qconFactory;
    
    // connection to a queue
    private QueueConnection qcon;
    
    // session within a connection
    private QueueSession qsession;
    
    // queue sender that sends a message to the queue
    private QueueSender qsender;
    
    // queue where the message will be sent to
    private Queue queueOut;
    
    // a message that will be sent to the queue
    private ObjectMessage msg;
    
    
    public double rand;
   

	// create a connection to the WLS using a JNDI context
    public void init(Context ctx) throws NamingException, JMSException {
        qconFactory = (QueueConnectionFactory) ctx.lookup(Config.JMS_FACTORY);
        qcon = qconFactory.createQueueConnection();
        qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
        
        queueOut = (Queue) ctx.lookup("jms/hw9-queue-all");
        qsender = qsession.createSender(queueOut);
        msg = qsession.createObjectMessage(); 
        qcon.start();
    }
        
    // close sender, connection and the session
    public void close() throws JMSException {
        qsender.close();
        qsession.close();
        qcon.close();
    }

    // sends the message to the queue
    public void send(Serializable message) throws Exception {
        // send the message and close
        msg.setObject(message);
        qsender.send(msg, DeliveryMode.PERSISTENT, 8, 0);
        System.out.println("The message: <" + message + "> was sent to the destination " +
                qsender.getDestination().toString());
    }
    
    public static void main(String[] args) throws Exception {
        Hashtable<String, String> env = new Hashtable<String, String>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, Config.JNDI_FACTORY);
        env.put(Context.PROVIDER_URL, Config.PROVIDER_URL);
        InitialContext ic = new InitialContext(env);   
        
        // create the producer object and send the message
        OrderClient client = new OrderClient();
        client.init(ic);
        
        Random r = new Random();
        //Sends randomly Booking or Trip order, Booking and Trip are randomly generated in constructor
        for (int i = 0; i < 50; ++i) {
        	Serializable msg;
        	if (r.nextInt(2) == 0) {
        		msg = new Booking();
        	} else {
        		msg = new Trip();
        	}
            client.send(msg); 
        }        
        client.close();
    }
}