package Model;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Bid Class. Stores information about a Bid. 
 * @author prancingponies
 */
public class Bid implements Serializable {
	
	private static final long serialVersionUID = -1147412376153360349L;
	private Item item;	
	private Auction auction;	
	private Double bidAmount;
	private LocalDate bidDate;
	private Bidder bidder; 
	
	/**
	 * Constructs a Bid
	 * @param theBidAmount
	 * @param theBidDate
	 * @param theItem
	 * @param theAuction
	 * @param theBidder
	 */
	public Bid(Double theBidAmount, LocalDate theBidDate, Item theItem, Auction theAuction, Bidder theBidder) {
		this.bidAmount = theBidAmount;
		this.bidDate = theBidDate;
		this.item = theItem;
		this.auction = theAuction;
		this.bidder = theBidder;
	}
	
	public Double getBidAmount() {
		return bidAmount;
	}
	
	public LocalDate getBidDate() {
		return bidDate;
	}
	
	public User getBidder() {
		return bidder;
	}
	
	public Item getItem() {
		return item;
	}
	
	public Auction getAuction() {
		return auction;
	}
}
