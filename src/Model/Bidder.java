package Model;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Enables a Bidder to place Bids on an Item for a specific Auction.
 * @see User
 * @author PrancingPonies
 * @version 5/3/2018
 */
public class Bidder extends User implements Serializable {  
	
	private static final long serialVersionUID = -1550311622050553415L;
	private ArrayList<Bid> bids;
	
	public Bidder(String name, String loginName, PhoneNumber phoneNumber, String contactInfo) {
		super(name, loginName, phoneNumber, contactInfo);
		bids = new ArrayList<Bid>();
	}
	
	// need to create our own exception
	public void bid(double price, Item item) throws RuntimeException {
		
	}
	
	public ArrayList<Bid> getBids() {
		return bids;
	}
	
	public ArrayList<Auction> getAuctionsWithBids() {
		ArrayList<Auction> auctions = new ArrayList<Auction>();
		for (int i = 0; i < bids.size(); i++) {
			auctions.add(bids.get(i).getAuction());
		}
		return auctions;
		
	}
	
	public void addBid(Bid bid) {
		bids.add(bid);
	}
}
