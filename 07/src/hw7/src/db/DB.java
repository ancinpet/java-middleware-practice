package db;
import java.util.ArrayList;
import java.util.Hashtable;

public class DB {
    private static DB instance = null;
    private Hashtable<Integer, Booking> bt = new Hashtable();
    private Hashtable<Integer, String> at = new Hashtable();

    //Fill database with some airports
    public static DB getInstance() {
        if (instance == null) {
            instance = new DB();
            instance.at.put(0, "Prague Airport");
            instance.at.put(1, "Brno Airport");
            instance.at.put(2, "Bratislava Airport");
            instance.at.put(3, "Paris Airport");
            instance.at.put(4, "Berlin Airport");
            instance.at.put(5, "Rome Airport");
            instance.at.put(6, "Barcelona Airport");
            instance.at.put(7, "Moscow Airport");
            instance.at.put(8, "Washington Airport");
            instance.at.put(9, "Tokyo Airport");
    	}
        return instance;
    }
    
    public void addBooking(Booking tmp) {
    	tmp.setId(bt.size());
    	bt.put(tmp.getId(), tmp);
    }
    
    public void removeBooking(int id) {
    	bt.remove(id);
    }
    
    public void updatePassengerOfBooking(int bookingId, String newPassenger) {
    	Booking booking = bt.get(bookingId);
    	if (booking != null) {
        	booking.setPassenger(newPassenger);
    	}
    }
    
    public ArrayList<Booking> getAllBookings() {
    	return new ArrayList<>(bt.values());
    }
    
    public Hashtable<Integer, String> getAllAirports() {
    	return at;
    }
    
    public String getAirportName(int id) {
    	return at.get(id);
    }
}