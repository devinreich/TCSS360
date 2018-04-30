package Model;

import java.util.ArrayList;

public class Bidder extends User {
	private ArrayList<Bid> bids;
	
	
	protected Bidder(String name, String loginName, PhoneNumber phoneNumber, String contactInfo) {
		super(name, loginName, phoneNumber, contactInfo);
	}
}
