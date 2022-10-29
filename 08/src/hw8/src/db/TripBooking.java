package db;

public class TripBooking extends Booking {	
    public TripBooking(){
    	super();
    }

    public TripBooking(String name, String destination){
    	super(name, destination);
    }
	
	@Override
	public String toString() {
		return "Trip: " + super.toString();
	}
}