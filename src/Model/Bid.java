package Model;
import java.time.LocalDate;

/**
 * Bid Class. 
 * Enables a Bidder to place Bids on an Item for a specific Auction.
 * @author PrancingPonies
 * @version 5/3/2018
 */
public class Bid {
	
	private Item item;	/* need Item object to get base price */
	private Auction auction;	/* need Auction object for auctionStartDate */
	private Double bidAmount;
	private LocalDate timeOfBid;
	private Bidder bidder; // change user to bidder when created
	//private String forItem; // change string to item when created -Why? -np
	
	/**
	 * Creates a Bid. 
	 * @param bidAmount	The amount the Bidder is attempting to bid
	 * @param timeOfBid	Time the bidder is attempting to place bid
	 * @param bidder	The user attached to this Bid
	 * @param item	The Item being bid on
	 * @param auction	The auction being bid on
	 * @version 5/3/2018
	 */
	public Bid(Double bidAmount, LocalDate timeOfBid, Bidder bidder, Item item, Auction auction) {
		this.bidAmount = bidAmount;
		this.timeOfBid = timeOfBid;
		this.bidder = bidder;
		this.item = item;
		this.auction = auction;
	}
	
	/**
	 * Adds Bid to Bidder's list of bids if bid is legal.
	 * @result Bidder's list of Bids grows by one Bid (this Bid).
	 * @author nadiapolk  
	 * @version 5/3/2018
	 */
	private void PlaceBid() {
		
		if (isBidAmountLegal() && isBidDateLegal()) {
			bidder.addBid(this);
		}
	}
	
	/**
	 * Method will return true (bid date is legal) if bid date is after item creation date 
	 * and before start of auction. 
	 * @author nadiapolk
	 * @version 5/3/2018
	 */
	public boolean isBidDateLegal() {	
		if ((auction.getStartDate().compareTo(timeOfBid) < 0) || 
				(auction.getCreateDate().compareTo(timeOfBid) == 0)) {
			if (timeOfBid.compareTo(auction.getStartDate()) > 0) {
				return false;
			} else if  (timeOfBid.compareTo(auction.getStartDate()) == 0) {
				return false;
			} else if  (timeOfBid.compareTo(auction.getStartDate()) < 0) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Method will return true (bid amount is legal) if bid amount is greater than or equal
	 * 	to minimum bid amount for item. 
	 * @author nadiapolk
	 * @version 5/3/2018
	 */
	public boolean isBidAmountLegal() {	
		return bidAmount >= item.getBasePrice();	
	}
	
	/**
	 * Gets bid amount. 
	 * @return	Returns the bid amount. 
	 * @version 5/3/2018
	 */
	public Double getBidAmount() {
		return bidAmount;
	}
	
	/**
	 * Gets time of bid. 
	 * @return	Returns the time of bid. 
	 * @version 5/3/2018
	 */
	public LocalDate getTimeOfBid() {
		return timeOfBid;
	}
	
	/**
	 * Gets Bidder. 
	 * @return	Returns the Bidder. 
	 * @version 5/3/2018
	 */
	public User getBidder() {
		return bidder;
	}
	
//	public String getItem() {
//		return forItem;
//	}
}
