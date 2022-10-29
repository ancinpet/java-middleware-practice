package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.ForwardedServer;
import helper.ServerMonitor;

@WebServlet("/*")
public class APIServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//Conditional GET
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws MalformedURLException, IOException {
		ForwardedServer toForward = ServerMonitor.getInstance().getHeathlyServer();
		if (toForward == null) {
			response.setStatus(504);
			response.getWriter().println("Proxy server was unable to find healthy server to process your request.");
			return;
		}
		// url
        String url = toForward.getUrl();
        HttpURLConnection connection = (HttpURLConnection) (new URL(url)).openConnection();
        // HTTP method
        connection.setRequestMethod("GET");
        // copy headers
        Collections.list(request.getHeaderNames()).forEach(head -> connection.setRequestProperty(head, request.getHeader(head)));
        // copy body
        BufferedReader inputStream = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));
        String inputLine;
        ServletOutputStream sout = response.getOutputStream();
        while ((inputLine = inputStream.readLine()) != null) {
            sout.write(inputLine.getBytes());
        }
        // close
        inputStream.close();
        sout.flush();
	}
	
	
}