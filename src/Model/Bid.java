package Model;

import java.util.Date;

public class Bid {
	private Double bidAmount;
	private Date timeOfBid;
	private User bidder; // change user to bidder when created
	private String forItem; // change string to item when created
	
	public Bid(Double bidAmount, Date timeOfBid, User bidder, String forItem) {
		this.bidAmount = bidAmount;
		this.timeOfBid = new Date(timeOfBid.getTime());
		this.bidder = bidder;
		this.forItem = forItem;
	}
	
	public Double getBidAmount() {
		return bidAmount;
	}
	
	public Date getTimeOfBid() {
		return new Date(timeOfBid.getTime());
	}
	
	public User getBidder() {
		return bidder;
	}
	
	public String getItem() {
		return forItem;
	}
}
