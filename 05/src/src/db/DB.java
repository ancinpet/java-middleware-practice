package db;
import java.util.ArrayList;
import java.util.Hashtable;

public class DB {
    private static DB instance = null;
    private Hashtable<Integer, Customer> bt = new Hashtable<Integer, Customer>();
    private Hashtable<Integer, Confirmation> ct = new Hashtable<Integer, Confirmation>();

    public static DB getInstance() {
        if (instance == null) {
            instance = new DB();
            //Add some customers to database at start
            for (int i = 0; i < 25; ++i) {
        		instance.addObject(new Customer(i, "Customer #" + i));            	
            }
    	}
        return instance;
    }

    public void addObject(Customer tmp) {
    	bt.put(bt.size(), tmp);
    }

    public void deleteObject(Integer id) {
    	bt.remove(id);
    }

    public void addConfirmation(Confirmation tmp) {
    	ct.put(tmp.getId(), tmp);
    }

    public void deleteConfirmation(Integer id) {
    	ct.remove(id);
    }
    
    //Get next unused id (fast implementation)
    public int getNextConfirm() {
    	return ct.size();
    }

    public Confirmation getConfirmation(int id) {
    	return ct.get(id);
    }
    
    public void updateConfirmationState(int id, int state) {
    	Confirmation conf = ct.get(id);
    	conf.setState(state);
    }
    
    //Helper to get all customers
    public ArrayList<Customer> getCustomers() {
    	return new ArrayList<>(bt.values());
    }
}