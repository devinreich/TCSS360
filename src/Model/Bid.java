/*
 * Class: Bid 
 * Purpose: Used to place Bids on an Item for a specific Auction, 
 * 			tracked to a Bidder
 */

package Model;
import java.time.LocalDate;
import java.util.ArrayList;

public class Bid {
	private Item item;	// need Item object to get base price
	private Auction auction;	// need for startDate
	private Double bidAmount;
	private LocalDate startDate;
	private LocalDate timeOfBid;
	private Double basePrice;
	private User bidder; // change user to bidder when created
	private String forItem; // change string to item when created
	
	public Bid(Double bidAmount, LocalDate timeOfBid, Bidder bidder, String forItem, Item item, Auction auction) {
		this.bidAmount = bidAmount;
		this.timeOfBid = timeOfBid;
		this.bidder = bidder;
		this.forItem = forItem;
		this.item = item;
		this.basePrice = item.getBasePrice();
		this.startDate = auction.getStartDate();
	}
	
	private void PlaceBid(Double bidAmount, LocalDate timeOfBid, Bidder bidder, String forItem, Item item, Auction auction) {
		
		if (isBidAmountLegal() && isBidDateLegal()) {
			bidder.addBid(this);
		}
	}
	
	public boolean isBidDateLegal() {	
		
		if (timeOfBid.compareTo(startDate) > 0) {
			return false;
		} else if  (timeOfBid.compareTo(startDate) == 0) {
			return false;
		} else if  (timeOfBid.compareTo(startDate) < 0) {
			return true;
		}
		return false;
	}
	
	public boolean isBidAmountLegal() {	
		return bidAmount >= basePrice;	
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
	
	public String getItem() {
		return forItem;
	}
}
