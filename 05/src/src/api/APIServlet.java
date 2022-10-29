package api;

import java.io.IOException;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.Confirmation;
import db.DB;

//Servlet supports asynchronous operations
@WebServlet(urlPatterns={"/*"}, asyncSupported=true)
public class APIServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//Allows to delete customer asynchronously
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String[] path = request.getPathInfo().split("/");

	    //Format /hw5/customer/<customerId>
	    if (path.length == 3 && path[1].equalsIgnoreCase("customer")) {
            response.setHeader("Content-Type" , "text/html");
            Integer deleteID;
            
            //Parse delete id from path and return bad request if it is not parsable
            try {
                deleteID = Integer.parseInt(path[2]); 
            } catch (NumberFormatException e) {
    	    	response.setStatus(400);
    	    	response.getWriter().println("Invalid id");
    	    	return;
            }
            
    	    response.setStatus(202);
    	    //Create confirmation with state = pending and put it into database
    	    Confirmation conf = new Confirmation(DB.getInstance().getNextConfirm(), 0);
    	    DB.getInstance().addConfirmation(conf);
            
    	    //Run delete task asynchronously
            AsyncContext async = request.startAsync();
            async.start(new Runnable() {
					
				@Override
				public void run() {
					//Try to simulate 10 seconds of delay (f.e. confirmation)
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						System.out.println("Wait interrupted");
					}
					//Delete customer from database
		            DB.getInstance().deleteObject(deleteID);
		            //Update confirmation state
		            //Since this request is asynchronous, there could be time race because we are simulating DB
		            //Real DB would guarantee no time race
		            DB.getInstance().updateConfirmationState(conf.getId(), 1);
				}
			});
            async.complete();
            
            //Give link with redirect to a service that shows deletion state
    	    response.getWriter().println("Deletion pending...");
    	    response.getWriter().println("See <a href=\"" + 
    	    		request.getRequestURL().substring(0, request.getRequestURL().lastIndexOf("/hw5/") + "/hw5/".length())
    	    		+ "confirm/" + conf.getId() + "\">LINK</a> for deletion status.");
	    }
	}

	//GET for showing all customers and deletion state
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String[] path = request.getPathInfo().split("/");

	    //Helper to show all customers in DB
	    if (path.length == 2 && path[1].equalsIgnoreCase("customer")) {
            response.setHeader("Content-Type" , "text/html");
    	    response.setStatus(200);
    	    response.getWriter().print(DB.getInstance().getCustomers());
    	//Format /hw5/confirm/<confirmationId>
    	} else if (path.length == 3 && path[1].equalsIgnoreCase("confirm")) {
            response.setHeader("Content-Type" , "text/html");
            Integer confId;
            
            //Parse the confirmation id and return bad request if not parsable
            try {
            	confId = Integer.parseInt(path[2]);
    	    } catch (NumberFormatException e) {
        	    response.setStatus(400);
        	    return;
			}
            
            //Get confirmation from database
    	    Confirmation conf = DB.getInstance().getConfirmation(confId);
    	    //Confirmation doesn't exist
    	    if (conf == null) {
        	    response.setStatus(200);
        	    response.getWriter().print("Confirmation invalid or expired");     
        	//Deletion is pending
    	    } else if (conf.getState() == 0) {
        	    response.setStatus(200);
        	    response.getWriter().print("Delete is still pending...");     
        	//Deletion finished
    	    } else if (conf.getState() == 1) {
        	    response.setStatus(200);
        	    response.getWriter().print("Item deleted");
        	//Reserved for future use (f.e. expired confirmations)
    	    } else {
        	    response.setStatus(500);
        	    response.getWriter().print("Unexpected confirmation state");       	    	
    	    }    	    
    	}
	}
}
