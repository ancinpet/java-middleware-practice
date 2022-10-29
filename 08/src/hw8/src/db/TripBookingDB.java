package db;
import java.util.ArrayList;
import java.util.Hashtable;

public class TripBookingDB {
    private static TripBookingDB instance = null;
    private Hashtable<Integer, TripBooking> bt = new Hashtable();
    private int capacity = 0;

    //Fill database with some airports
    public static TripBookingDB getInstance() {
        if (instance == null) {
            instance = new TripBookingDB();
    	}
        return instance;
    }
    
    public boolean addBooking(TripBooking tmp) {
    	if (capacity > 8) {
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
    
    public ArrayList<TripBooking> getAllBookings() {
    	return new ArrayList<>(bt.values());
    }
}