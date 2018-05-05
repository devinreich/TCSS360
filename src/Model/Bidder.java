package Model;

import java.util.ArrayList;


public class Bidder extends User {
	private ArrayList<Bid> bids;
	
	
	public Bidder(String name, String loginName, PhoneNumber phoneNumber, String contactInfo) {
		super(name, loginName, phoneNumber, contactInfo);
		bids = new ArrayList<Bid>();
	}
	
	// need to create our own exception
	public void bid(double price, Item item) throws RuntimeException {
		
	}
	
	public ArrayList<Bid> getBids() {
		return (ArrayList<Bid>) bids.clone();
	}
}
