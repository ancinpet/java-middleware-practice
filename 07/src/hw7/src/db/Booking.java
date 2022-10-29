package db;

import java.util.Calendar;

public class Booking {
	private int id;
    private String passenger;
    private AirportInfo departure;
    private AirportInfo arrival;

    public Booking(){
    	this.passenger = "";
    	this.setDeparture(new AirportInfo());
    	this.setArrival(new AirportInfo());
    	this.getArrival().getTime().add(Calendar.HOUR_OF_DAY, 2);
    }

    public Booking(String passenger, AirportInfo departure, AirportInfo arrival) {
        this.passenger = passenger;
        this.setDeparture(departure);
        this.setArrival(arrival);
    }
    
    public String getPassenger() {
        return passenger;
    }

    public void setPassenger(String passenger) {
        this.passenger = passenger;
    }

	public AirportInfo getDeparture() {
		return departure;
	}

	public void setDeparture(AirportInfo departure) {
		this.departure = departure;
	}

	public AirportInfo getArrival() {
		return arrival;
	}

	public void setArrival(AirportInfo arrival) {
		this.arrival = arrival;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}