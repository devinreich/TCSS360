package Model;

import java.util.ArrayList;

/**
 * Enables a Bidder to place Bids on an Item for a specific Auction.
 * @see User
 * @author PrancingPonies
 * @version 5/3/2018
 */
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
