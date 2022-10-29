package myservices;

import java.util.ArrayList;

import javax.jws.WebParam;
import javax.jws.WebService;

import db.FlightBooking;
import db.FlightBookingDB;

@WebService(endpointInterface = "myservices.FlightBookingService")
public class FlightBookingServiceImpl implements FlightBookingService {	
	public ArrayList<FlightBooking> getAllBookings() {
		return FlightBookingDB.getInstance().getAllBookings();
	}
	
	public FlightBooking bookFlight(@WebParam(name = "personName")String name, @WebParam(name = "flightDestination")String destination) {
		FlightBooking booking = new FlightBooking(name, destination);
		if (FlightBookingDB.getInstance().addBooking(booking)) {
			return booking;
		}
		return new FlightBooking("", "");
	}
	
	public void removeFlight(@WebParam(name = "flightId")int id) {
		FlightBookingDB.getInstance().removeBooking(id);
	}
}