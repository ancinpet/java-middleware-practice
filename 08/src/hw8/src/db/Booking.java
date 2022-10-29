package db;

public class Booking {
	private int id;
    private String personName;
    private String destination;

    public Booking(){
    	this.setName("");
    	this.setDestination("");
    }

    public Booking(String name, String destination){
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
	
	public boolean isInvalid() {
		return getName().equals("") && getDestination().equals("");
	}
	
	@Override
	public String toString() {
		return this.getName() + " is traveling to " + this.getDestination();
	}
}