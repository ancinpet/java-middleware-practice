package db;

import java.util.Calendar;
import java.util.Random;

//Contains time (departure/arrival) and the associated airport
public class AirportInfo {
    private Calendar time;
    private int airportId;

    public AirportInfo(){
    	this.time = Calendar.getInstance();
    	this.airportId = (new Random().nextInt(10));
    }

    public AirportInfo(Calendar time, int airportId){
    	this.time = time;
    	this.airportId = airportId;
    }
    
    public Calendar getTime() {
    	return this.time;
    }
    
    public int getId() {
    	return this.airportId;
    }
    
    public void setTime(Calendar time) {
    	this.time = time;
    }
}