package myservices;

import javax.jws.WebParam;
import javax.jws.WebService;

import db.*;

@WebService
public class GeneralBookingService {	
	
	public String makeBooking(
	        @WebParam(name = "type")
	        String type,
	        @WebParam(name = "name")
	        String personName,
	        @WebParam(name = "destination")
	        String destination) {
		if (type.equals("flight")) {
			FlightBookingServiceImplService flightService = new FlightBookingServiceImplService();
			FlightBookingService flightBooking = flightService.getFlightBookingServiceImplPort();
			FlightBooking flight = flightBooking.bookFlight(personName, destination);
			if (flight.isInvalid()) {
				return "No flight available";
			} else {
				return flight.toString();
			}
		} else if (type.equals("trip")) {
			TripBookingServiceImplService tripService = new TripBookingServiceImplService();
			TripBookingService tripBooking = tripService.getTripBookingServiceImplPort();
			TripBooking trip = tripBooking.bookTrip(personName, destination);
			if (trip.isInvalid()) {
				return "No trip available";
			} else {
				return trip.toString();
			}
		} else {
			return "Invalid booking type";
		}
	}
}