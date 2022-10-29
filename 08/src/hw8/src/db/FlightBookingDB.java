package db;
import java.util.ArrayList;
import java.util.Hashtable;

public class FlightBookingDB {
    private static FlightBookingDB instance = null;
    private Hashtable<Integer, FlightBooking> bt = new Hashtable();
    private int capacity = 0;

    //Fill database with some airports
    public static FlightBookingDB getInstance() {
        if (instance == null) {
            instance = new FlightBookingDB();
    	}
        return instance;
    }
    
    public boolean addBooking(FlightBooking tmp) {
    	if (capacity > 5) {
    		return false;
    	}    	
    	
    	++capacity;
    	tmp.setId(capacity);
    	bt.put(tmp.getId(), tmp);
    	return true;
    }
    
    public void removeBooking(int id) {
    	--capacity;
    	bt.remove(id);
    }
    
    public ArrayList<FlightBooking> getAllBookings() {
    	return new ArrayList<>(bt.values());
    }
}