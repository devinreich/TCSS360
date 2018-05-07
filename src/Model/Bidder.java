package Model;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Bidder extends User implements Serializable {  
	
	private static final long serialVersionUID = -1550311622050553415L;
	private ArrayList<Bid> allBids;
	private ArrayList<Auction> allAuctions;
	private ArrayList<Item> allItems;
	private Integer maxAllowedBids;
	private static final Integer DEFAULT_MAX_ALLOWED_BIDS = 10;
	
	public Bidder(String name, String loginName, PhoneNumber phoneNumber, String contactInfo) {
		super(name, loginName, phoneNumber, contactInfo);
		allBids = new ArrayList<Bid>();	// bidders list of bids
		allAuctions = new ArrayList<Auction>();	// bidders list of auction
		allItems = new ArrayList<Item>();	// bidders list of items
		maxAllowedBids = DEFAULT_MAX_ALLOWED_BIDS;
	}
	
	// default if not set
	public void setMaxAllowedBids(Integer allowedBids) {
		maxAllowedBids = allowedBids;
	}
	
	public ArrayList<Bid> getBids() {
		return allBids;
	}
	
	// all biddable auctions
	public ArrayList<Auction> getBiddableAuctions(Calendar calendar) {
		System.out.println("hit");
		ArrayList<Auction> biddableAuctions = new ArrayList<Auction>();
		ArrayList<Auction> allAuctions = calendar.getAllAuctions();
		for (Auction auction: allAuctions) {
			LocalDate today = LocalDate.now();
			System.out.println(auction.getOrganization().getName());
			if (isBidderLegal() && isAuctionLegal(auction) && (auction.getStartDate().compareTo(today.plusDays(1)) > 0)) {
				biddableAuctions.add(auction);
			}
		}
	
		return biddableAuctions;
	}
	
	// all items with bids
	public ArrayList<Item> getAllItems() {
		return allItems;
	}
	
	// all items bid on in an auction
	public ArrayList<Item> getItemsInAuction(Auction auction) {
		
		ArrayList<Item> itemsInThisAuction = new ArrayList<Item>();
		
		for (Item auctionObject: auction.getInventory()) {
		    for (Item bidderObject: allItems) {
		    	if (bidderObject.equals(auctionObject)) {
		    		itemsInThisAuction.add(auctionObject);
		    	}
		    }    
		}
			
		return itemsInThisAuction;
	}
	
	public void placeBid(Bid bid) {
		
		if (bid.isBidAmountLegal() && bid.isBidDateLegal() 
				&& isAuctionLegal(bid.getAuction()) && isBidderLegal()) {
			allBids.add(bid); // add bid to bidder's list of bids
			allAuctions.add(bid.getAuction()); // add auction to bidder's list of bid auctions
			allItems.add(bid.getItem()); // add item to bidder's list of bid items
			bid.getAuction().getBidsForAuction().add(bid); // Add bid to auction
		}		
	}
	
	// all auctions with bids
	public ArrayList<Auction> getAuctionsWithBids() {
		return allAuctions;
	}
	
	// bidder cant place more than max allowed bids for specified auction
	public boolean isAuctionLegal(Auction auction) {
		
		if (getNumberBidsAuction(auction) < auction.getMaxBiddableItems()) {
			return true;
		}
		return false;
	}
	
	// bidder can't place more than 10 bids at any given time
	public boolean isBidderLegal() {
		
		if (allBids.size() < maxAllowedBids) {
			return true;
		}
		return false;
	}
	
	public int getNumberBidsAuction(Auction auction) {
		
		int bids = 0;
		
		for (Bid bid: auction.getBidsForAuction()) {
		    if (bid.getBidder().getName().equals(this.getName())) {
		    	bids ++;
		    }
		}
		
		return bids;
	}
	
	public int getNumberTotalBids() {
		return allBids.size();
	}
}
