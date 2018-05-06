package Model;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Bid Class. 
 * Enables a Bidder to place Bids on an Item for a specific Auction.
 * @author PrancingPonies
 * @version 5/3/2018
 */
public class Bid implements Serializable {
	
	private static final long serialVersionUID = 8819633818230048886L;
	private Item item;	/* need Item object to get base price */
	private Auction auction;	/* need Auction object for auctionStartDate */
	private Double bidAmount;
	private LocalDate timeOfBid;
	private Bidder bidder; // change user to bidder when created
	private Item forItem;
	
	public Bid(Double bidAmount, LocalDate timeOfBid, Bidder bidder, Item item, Auction auction) {
		this.bidAmount = bidAmount;
		this.timeOfBid = timeOfBid;
		this.bidder = bidder;
		this.item = item;
		this.auction = auction;
	}
	
	private void PlaceBid() {
		
		if (isBidAmountLegal() && isBidDateLegal()) {
			bidder.addBid(this);
		}
	}
	
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
	
	public boolean isBidAmountLegal() {	
		return bidAmount >= item.getBasePrice();	
	}
	
	public Double getBidAmount() {
		return bidAmount;
	}
	
	public LocalDate getTimeOfBid() {
		return timeOfBid;
	}
	
	public User getBidder() {
		return bidder;
	}
	
	public Item getItem() {
		return forItem;
	}
}
