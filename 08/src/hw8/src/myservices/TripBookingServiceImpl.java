package myservices;

import java.util.ArrayList;

import javax.jws.WebParam;
import javax.jws.WebService;

import db.TripBooking;
import db.TripBookingDB;

@WebService(endpointInterface = "myservices.TripBookingService")
public class TripBookingServiceImpl implements TripBookingService {	
	public ArrayList<TripBooking> getAllBookings() {
		return TripBookingDB.getInstance().getAllBookings();
	}
	
	public TripBooking bookTrip(@WebParam(name = "personName")String name, @WebParam(name = "flightDestination")String destination) {
		TripBooking booking = new TripBooking(name, destination);
		if (TripBookingDB.getInstance().addBooking(booking)) {
			return booking;
		}
		return new TripBooking("", "");
	}
	
	public void removeTrip(@WebParam(name = "flightId")int id) {
		TripBookingDB.getInstance().removeBooking(id);
	}
}