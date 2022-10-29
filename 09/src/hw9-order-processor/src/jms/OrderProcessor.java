package jms;

import java.io.Serializable;
import java.util.Hashtable;

import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import pojo.Booking;
import pojo.Trip;
 
public class OrderProcessor implements MessageListener {

    // connection factory
    private QueueConnectionFactory qconFactory;
    
    // connection to a queue
    private QueueConnection qcon;
    
    // session within a connection
    private QueueSession qsession;
    
    // queue receiver that receives a message to the queue
    private QueueReceiver qreceiver;
    
    // queue sender that sends a message to the queue
    private QueueSender qBookingSender;
    
    // queue sender that sends a message to the queue
    private QueueSender qTripSender;
    
    // queue where the message will be sent to
    private Queue queueIn;
    
    // queue where the message will be sent to
    private Queue queueBookings;
    
    // queue where the message will be sent to
    private Queue queueTrips;
    
    // a message that will be sent to the queue
    private ObjectMessage msg;
    
    // callback when the message exist in the queue
    public void onMessage(Message msg) {
        try {
        	Serializable myMsg;
            if (msg instanceof ObjectMessage) {
            	myMsg = ((ObjectMessage) msg).getObject();
            } else {
            	System.out.println("Failed to get message");
            	return;
            }
            this.send(myMsg);
        } catch (JMSException jmse) {
            System.err.println("An exception occurred: " + jmse.getMessage());
        }
    }

	// create a connection to the WLS using a JNDI context
    public void init(Context ctx) throws NamingException, JMSException {
        qconFactory = (QueueConnectionFactory) ctx.lookup(Config.JMS_FACTORY);
        qcon = qconFactory.createQueueConnection();
        qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
        
        queueIn = (Queue) ctx.lookup("jms/hw9-queue-all");
        qreceiver = qsession.createReceiver(queueIn);

        queueBookings = (Queue) ctx.lookup("jms/hw9-queue-bookings");
        qBookingSender = qsession.createSender(queueBookings);
        queueTrips = (Queue) ctx.lookup("jms/hw9-queue-trips");
        qTripSender = qsession.createSender(queueTrips);
        msg = qsession.createObjectMessage(); 
        qcon.start();
    }
        
    // close sender, connection and the session
    public void close() throws JMSException {
    	qBookingSender.close();
    	qTripSender.close();
        qsession.close();
        qcon.close();
    }
    
    // start receiving messages from the queue
    public void receive() throws Exception {  
        qreceiver.setMessageListener(this);
        System.out.println("Connected to " + queueIn.toString() + ", receiving messages...");
        try {
            synchronized (this) {
            	while (true) {
                    this.wait();
            	}
            }
        } finally {
            close();
        }
    }

    // sends the message to the queue
    public void send(Serializable message) throws JMSException {
        // send the message
        msg.setObject(message);
        if (message instanceof Booking) {
            qBookingSender.send(msg, DeliveryMode.PERSISTENT, 8, 0);
            System.out.println("Booking: <" + message + "> was forwarded to the destination " +
           		qBookingSender.getDestination().toString());
        } else if (message instanceof Trip) {
            qTripSender.send(msg, DeliveryMode.PERSISTENT, 8, 0);
            System.out.println("Trip: <" + message + "> was forwarded to the destination " +
           		qTripSender.getDestination().toString());
        } else {
         	System.out.println("Object was not recognized");
        }
    }
    
    public static void main(String[] args) throws Exception {
        Hashtable<String, String> env = new Hashtable<String, String>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, Config.JNDI_FACTORY);
        env.put(Context.PROVIDER_URL, Config.PROVIDER_URL);
        InitialContext ic = new InitialContext(env);   
        
        // create the producer object and send the message
        OrderProcessor app = new OrderProcessor();
        app.init(ic);
        app.receive();
    }
}