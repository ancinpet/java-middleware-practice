package helper;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ServerMonitor {
    private static ServerMonitor instance = null;
    private List<ForwardedServer> bt = new ArrayList<ForwardedServer>();

    public static ServerMonitor getInstance() {
        if (instance == null) {
            instance = new ServerMonitor();
            //Add some customers to database at start
            for (int i = 1; i <= 3; ++i) {          	
        		instance.addServer(new ForwardedServer("http://147.32.233.18:8888/MI-MDW-LastMinute" + i + "/list"));
            }
            new Thread(() -> {
            	while (true) {
            		ServerMonitor.getInstance().healthCheck();
            		try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						//DO NOTHING
					}
            	}
            }).start();
    	}
        return instance;
    }

    public void addServer(ForwardedServer tmp) {
    	bt.add(tmp);
    }
    
    public ForwardedServer getHeathlyServer() {
    	List<ForwardedServer> healthy = new ArrayList<ForwardedServer>();
    	for (ForwardedServer s: this.bt) {
    		if (s.isHealthy()) {
    			healthy.add(s);
    		}
    	}
    	if (healthy.size() <= 0) {
    		return null;
    	}
    	return healthy.get(new Random().nextInt(healthy.size()));
    }

    public void healthCheck() {
    	for (ForwardedServer s: this.bt) {
    		s.setHealthy(this.isServerHealthy(s.getUrl()));
    	}
    }
    
    private boolean isServerHealthy(String url) {
		HttpURLConnection connection;
		try {
			connection = (HttpURLConnection) (new URL(url)).openConnection();
			connection.setRequestMethod("GET");
			System.out.println("Server " + url + " responded with status: " + connection.getResponseCode());
			return connection.getResponseCode() == 200;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}