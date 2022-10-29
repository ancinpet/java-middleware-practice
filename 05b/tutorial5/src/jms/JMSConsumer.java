package jms;

import java.util.Hashtable;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
 
public class JMSConsumer implements MessageListener {

    // connection factory
    private QueueConnectionFactory qconFactory;
    
    // connection to a queue
    private QueueConnection qcon;
    
    // session within a connection
    private QueueSession qsession;
    
    // queue receiver that receives a message to the queue
    private QueueReceiver qreceiver;
    
    // queue sender that sends a message to the queue
    private QueueSender qsender;
    
    // queue where the message will be sent to
    private Queue queueIn;
    
    // queue where the message will be sent to
    private Queue queueOut;
    
    // a message that will be sent to the queue
    private TextMessage msg;
    
    // to receive
    private String toReceive;
    
    // callback when the message exist in the queue
    public void onMessage(Message msg) {
        try {
            String msgText;
            if (msg instanceof TextMessage) {
                msgText = ((TextMessage) msg).getText();
            } else {
                msgText = msg.toString();
            }
            respondToMessage(msgText);
        } catch (JMSException jmse) {
            System.err.println("An exception occurred: " + jmse.getMessage());
        }
    }
    
    private void respondToMessage(String msgText) {
    	if (msgText.substring(0, 7).equals(this.toReceive)) {
    		System.out.println("Booking: " + msgText + " received, sending confirmation...");
    		try {
				this.send("Confirmation" + msgText.substring(7));
			} catch (Exception e) {
	    		System.out.println("Unable to send message...");
				e.printStackTrace();
			}
    	}
	}

	// create a connection to the WLS using a JNDI context
    public void init(Context ctx) throws NamingException, JMSException {        
        qconFactory = (QueueConnectionFactory) ctx.lookup(Config.JMS_FACTORY);
        qcon = qconFactory.createQueueConnection();
        qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
        
        queueIn = (Queue) ctx.lookup("jms/mdw-queue-two");
        qreceiver = qsession.createReceiver(queueIn);
        
        queueOut = (Queue) ctx.lookup("jms/mdw-queue-one");
        qsender = qsession.createSender(queueOut);
        msg = qsession.createTextMessage(); 
        qcon.start();
    }
        
    // close sender, connection and the session
    public void close() throws JMSException {
        qsender.close();
        qsession.close();
        qcon.close();
    }
    
    // start receiving messages from the queue
    public void receive(String toReceive) throws Exception {    
    	this.toReceive = toReceive;
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
            System.out.println("Finished.");
        }
    }

    // sends the message to the queue
    public void send(String message) throws Exception {
        // send the message

        msg.setText(message);
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
        JMSConsumer consumer = new JMSConsumer();
        consumer.init(ic);
        consumer.receive("Booking");
    }
}