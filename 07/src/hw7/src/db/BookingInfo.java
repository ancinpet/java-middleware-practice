package db;

//Helper class for prettier display of Booking
public class BookingInfo {
	private String bookingId;
    private String passenger;
    private String departureAirport;
    private String departureTime;
    private String arrivalAirport;
    private String arrivalTime;

    public BookingInfo() {
    	this.bookingId = "";
    	this.passenger = "";
    	this.departureAirport = "";
    	this.departureTime = "";
    	this.arrivalAirport = "";
    	this.arrivalTime = "";
    }
    
    public BookingInfo(Booking booking) {
    	this.bookingId = "" + booking.getId();
    	this.passenger = booking.getPassenger();
    	this.departureAirport = DB.getInstance().getAirportName(booking.getDeparture().getId());
    	this.arrivalAirport = DB.getInstance().getAirportName(booking.getArrival().getId());
    	this.departureTime = booking.getDeparture().getTime().getTime().toString();
    	this.arrivalTime = booking.getArrival().getTime().getTime().toString();
    }

	public String getPassenger() {
		return passenger;
	}

	public void setPassenger(String passenger) {
		this.passenger = passenger;
	}

	public String getDepartureAirport() {
		return departureAirport;
	}

	public void setDepartureAirport(String departureAirport) {
		this.departureAirport = departureAirport;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public String getArrivalAirport() {
		return arrivalAirport;
	}

	public void setArrivalAirport(String arrivalAirport) {
		this.arrivalAirport = arrivalAirport;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}
}