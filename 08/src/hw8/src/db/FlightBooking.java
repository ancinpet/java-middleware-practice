package db;

public class FlightBooking extends Booking {	
    public FlightBooking(){
    	super();
    }

    public FlightBooking(String name, String destination){
    	super(name, destination);
    }
	
	@Override
	public String toString() {
		return "Flight: " + super.toString();
	}
}