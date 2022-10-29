package services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;

import javax.jws.WebParam;
import javax.jws.WebService;
import db.*;

@WebService
public class TestService {
	//Get all Bookings in prettier format for test purposes (BookingInfo)
	public ArrayList<BookingInfo> getAllBookings() {
		ArrayList<BookingInfo> tmp = new ArrayList();
		for (Booking booking: DB.getInstance().getAllBookings()) {
			tmp.add(new BookingInfo(booking));
		}
		return tmp;
	}
	
	//Helper to list all airports available
	public Hashtable<Integer, String> getAllAirports() {
		return DB.getInstance().getAllAirports();
	}
	
	//Book flight, time format is czech dd.mm.yyyy hh:mm - 22.11.2019 14:55
	public boolean bookFlight(@WebParam(name = "passengerName")String passengerName, @WebParam(name = "departureTime")String departure, 
						   @WebParam(name = "departureAirportId")int depAirportId, @WebParam(name = "arrivalTime")String arrival, 
						   @WebParam(name = "arrivalAirportId")int arrAirportId) {
		//Create time objects and parser
		Calendar departureT = Calendar.getInstance();
		Calendar arrivalT = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy hh:mm");
		//Try parsing the string input, fails if cannot be parsed
		try {
			departureT.setTime(dateFormat.parse(departure));
			arrivalT.setTime(dateFormat.parse(arrival));
		} catch (ParseException e) {
			System.out.println("Unable to parse date: " + arrival + " or " + departure);
			e.printStackTrace();
			return false;
		}
		//Save into DB
		DB.getInstance().addBooking(new Booking(passengerName, new AirportInfo(departureT, depAirportId), new AirportInfo(arrivalT, arrAirportId)));
		return true;
	}
	
	//Remove flight by id
	public boolean removeFlight(@WebParam(name = "bookingId")int id) {
		DB.getInstance().removeBooking(id);
		return true;
	}
	
	//Update flight by id, usually, only name can be changed for ongoing booked flight, otherwise, booking has to be cancelled
	public boolean updateFlight(@WebParam(name = "bookingId")int bookingId, @WebParam(name = "passengerName")String passengerName) {
		DB.getInstance().updatePassengerOfBooking(bookingId, passengerName);
		return true;
	}
}