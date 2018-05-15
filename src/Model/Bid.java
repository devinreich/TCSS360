package Model;
import java.io.Serializable;
import java.time.LocalDate;

public class Bid implements Serializable {
	
	private static final long serialVersionUID = -1147412376153360349L;
	private Item item;	
	private Auction auction;	
	private Double bidAmount;
	private LocalDate timeOfBid;
	private Bidder bidder; 
	private Item forItem;
	private int MAX_BID = 4;
	
	public Bid(Double bidAmount, LocalDate timeOfBid, Item item, Auction auction, Bidder bidder) {
		this.bidAmount = bidAmount;
		this.timeOfBid = timeOfBid;
		this.item = item;
		this.auction = auction;
		this.bidder = bidder;
	}
	
	
	public boolean isBidDateLegal() {	
		if ((auction.getStartDate().compareTo(timeOfBid) > 0) || 
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
	
	public boolean isBidNumberLegal() {
		return bidder.getBids().size() <= MAX_BID;
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
	
	public Auction getAuction() {
		return auction;
	}
	
	
}
