package pojo;

import java.io.Serializable;
import java.util.Random;

public class Trip implements Serializable {
	private int id;
    private String personName;
    private String destination;

    public Trip(){
    	Random r = new Random();
    	this.setId(r.nextInt());
    	this.setName("Mr Trip Horak #" + r.nextInt(1000));
    	this.setDestination("Nice place #" + r.nextInt(1000));
    }

    public Trip(int id, String name, String destination){
    	this.setId(id);
    	this.setName(name);
    	this.setDestination(destination);
    }

	public String getName() {
		return personName;
	}

	public void setName(String name) {
		this.personName = name;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}