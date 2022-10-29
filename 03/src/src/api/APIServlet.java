package api;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/*")
public class APIServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String[] path = request.getPathInfo().split("/");

	    if (path.length == 2 && path[1].equalsIgnoreCase("booking")) {
		    String api = request.getParameter("state");
	    	response.setStatus(200);
            response.setHeader("Content-Type" , "text/html");
            HttpSession session = request.getSession();
            String state = (String)session.getAttribute("bookingState");
            
            if (api == null || api.isEmpty()) {
            	response.getWriter().println("<h1>Welcome to the main page of the application!</h1>");
	            if (state != null) {
	            	response.getWriter().println("<p>Looks like you left off without finishing your order!</p>");
	            	response.getWriter().println(this.resolveProblem(state));
	            } else {
		            response.getWriter().println("<span>To create new booking, please follow this link: </span><a href=\"?state=new\">CREATE NEW ORDER</a>");
	            }
	            
	            return;
            }
		    
            //Moore FSM always 1 "behind"
		    switch (api) {
		    	case "new":
		    		if (state == null || state.isEmpty()) {
		    			session.setAttribute("bookingState", "new");
			            response.getWriter().println("<h1>Thank you for creating order!</h1>");	
			            response.getWriter().println("<span>Please follow the link: </span><a href=\"?state=payment\">PAY ORDER</a>");
			            return;
		    		} else {
			    		this.stateError(response, state);	
			            return;
		    		}
		    	case "payment":
		    		if (state != null && state.equals("new")) {
		    			session.setAttribute("bookingState", "payment");
			            response.getWriter().println("<h1>Thank you for payment of the order!</h1>");	
			            response.getWriter().println("<span>Please follow the link: </span><a href=\"?state=completed\">REVIEW</a>");
			            return;
		    		} else {
			    		this.stateError(response, state);		
			            return;
		    		}
		    	case "completed":
		    		if (state != null && state.equals("payment")) {
		    			session.setAttribute("bookingState", null);
			            response.getWriter().println("<h1>Your order is ready!</h1>");	
			            response.getWriter().println("<span>Thank you for your cooperation, for new order, follow: </span><a href=\"?state=new\">NEW ORDER</a>");
			            response.getWriter().println("<br><span>Go back to our main page: </span><a href=\"?\">START</a>");
			            return;
		    		} else {
			    		this.stateError(response, state);	
			            return;
		    		}
		    	default:
		    		this.stateError(response, state);
		    }   
	    } else {
    		response.setStatus(404);
		}
	}
	
	void stateError(HttpServletResponse response, String currentState) throws ServletException, IOException {
    	response.setStatus(403);
        response.getWriter().println("<h1>Whoops, you are not supposed to be here!</h1>");	
        response.getWriter().println(this.resolveProblem(currentState));
	}

	String resolveProblem(String state) {
		String tmp = "<span>Please follow the link: </span>";
		if (state == null) {
			return tmp + "<a href=\"?state=new\">NEW ORDER</a>";
		}
		
		switch (state) {
			case "new":
				return tmp + "<a href=\"?state=payment\">PAY ORDER</a>";				
			case "payment":
				return tmp + "<a href=\"?state=completed\">REVIEW</a>";
			default:
				return tmp + "<a href=\"?state=new\">NEW ORDER</a>";
		}
	}
}
