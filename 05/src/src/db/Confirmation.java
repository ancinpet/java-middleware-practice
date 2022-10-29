package db;

//Class that holds customer deletion state
//0 - pending, 1 - deleted, other - invalid
public class Confirmation {
	private int id;
	private int state;

    public Confirmation(int id, int state) {
    	this.setId(id);
    	this.setState(state);
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
}