package helper;

//Customer has name and identifier
public class ForwardedServer {
	private String url;
    private boolean isHealthy;
    
    ForwardedServer(String url) {
    	this.setUrl(url);
    	this.setHealthy(false);
    }
    
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isHealthy() {
		return isHealthy;
	}
	public void setHealthy(boolean isHealthy) {
		this.isHealthy = isHealthy;
	}
}