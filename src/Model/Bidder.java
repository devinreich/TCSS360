package Model;

import java.util.ArrayList;

/**
 * Enables a Bidder to place Bids on an Item for a specific Auction.
 * @see User
 * @author PrancingPonies
 * @version 5/3/2018
 */
public class Bidder extends User {
	/**
	 * The {@link ArrayList<Bid>} instance representing the list of Bids for a Bidder. 
	 */
	private ArrayList<Bid> bids;
	
	/**
	 * Adds a Bid to the list of Bids belonging to this Bidder. 
	 * @param bid	the instance of the Bid class being added to the list of Bids- 
	 * 				see PlaceBid method in Bid class
	 * @author NadiaPolk
	 * @Version 5/3/2018
	 */
	public Bidder(String name, String loginName, PhoneNumber phoneNumber, String contactInfo) {
		super(name, loginName, phoneNumber, contactInfo);
	}
	
	/**
	 * Adds a Bid to the list of Bids belonging to this Bidder. 
	 * @param bid	the instance of the Bid class being added to the list of Bids.
	 * @see  {@link Bid#placeBid()}
	 * @author NadiaPolk
	 * @Version 5/3/2018
	 */
	protected void addBid(Bid bid) {
		bids.add(bid);
	}
	
	/**
	 * Gets the {@link ArrayList<Bid>} instance representing the list of Bids for a Bidder. 
	 * @return {@link Bidder#bids} The list of bids. 
	 */
	public ArrayList<Bid> getBids() {
		return bids;
	}
}
