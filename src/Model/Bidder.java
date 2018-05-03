/*
 * Class: Bidder
 * Purpose: Subclass of super User that can also place Bids on Items.
 * 			Each Bidder has their own list of Bids. 
 */

package Model;

import java.util.ArrayList;

public class Bidder extends User {
	private ArrayList<Bid> bids;
	
	public Bidder(String name, String loginName, PhoneNumber phoneNumber, String contactInfo) {
		super(name, loginName, phoneNumber, contactInfo);
	}
	
	protected void addBid(Bid bid) {
		bids.add(bid);
	}
	
	public ArrayList<Bid> getBids() {
		return bids;
	}
}
