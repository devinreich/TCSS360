package Model;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import Controller.Run;
import GUI.AuctionCentral;

/**
 * Bidder class. Creates a Bidder, stores related information
 * regarding their bids, items and auctions ever interacted with. 
 * @author prancingponies
 */
public class Bidder extends User implements Serializable {  

	private static final long serialVersionUID = -5785811298050496048L;
	private ArrayList<Bid> allBidsForBidder;
	private ArrayList<Auction> allAuctionsForBidder;
	private ArrayList<Item> allItemsForBidder;
	private Integer maxAllowedBids;
	private static final Integer DEFAULT_MAX_ALLOWED_BIDS = 12;
	/**
	 * Construct a Bidder
	 * @param name
	 * @param loginName
	 */
	public Bidder(String name, String loginName) {
		super(name, loginName);
		allBidsForBidder = new ArrayList<Bid>();	// bidders list of bids
		allAuctionsForBidder = new ArrayList<Auction>();	// bidders list of auction
		allItemsForBidder = new ArrayList<Item>();	// bidders list of items
		maxAllowedBids = DEFAULT_MAX_ALLOWED_BIDS;
	}

	/**
	 * User can change max allowed active total bids for a bidder. 
	 * @param allowedBids
	 */
	public void setMaxAllowedBids(Integer allowedBids) {
		maxAllowedBids = allowedBids;
	}


	/**
	 * @return the upcoming auctions available to be bid on 
	 */
	public ArrayList<Auction> getBiddableAuctions() {
		ArrayList<Auction> biddableAuctions = new ArrayList<Auction>();
		if (isBidderLegal()) {
			for (Auction auction: Run.calendar.getUpcomingAuctions()) {
				if (isAuctionLegalForBidder(auction)) {
					biddableAuctions.add(auction);	
				}
			}			
		}
		return biddableAuctions;
	}


	// all items bid on in an auction
	public ArrayList<Item> getItemsInAuction(Auction auction) {

		ArrayList<Item> itemsInThisAuction = new ArrayList<Item>();

		for (Item auctionObject: auction.getInventory()) {
			for (Item bidderObject: allItemsForBidder) {
				if (bidderObject.equals(auctionObject)) {
					itemsInThisAuction.add(auctionObject);
				}
			}    
		}		
		return itemsInThisAuction;
	}


	/** 
	 * Bidder cant place more than specified amount of total bids on any auction
	 * @param auction
	 * @return true if the number of bids bidder placed on this 
	 * auction is less than max allowed amount
	 */
	public boolean isAuctionLegalForBidder(Auction theAuction) {
		//System.out.println("Auction is legal: " + (getNumberBidsAuction(theAuction) < theAuction.getMaxBiddableItems()));
		return  (getNumberBidsAuction(theAuction) < theAuction.getMaxBiddableItems());
	}


	/**
	 * Bidder cant place any more than maximum allowed number
	 *  of bids for future (upcoming) auctions at any given time. 
	 * @return true if active bid number less than max allowed bids. 
	 */
	public boolean isBidderLegal() {
		//System.out.println("Bidder is legal: " + (getActiveBids().size() < maxAllowedBids));
		//System.out.println("Active Bids: " + getActiveBids().size());
		return (getActiveBids().size() < maxAllowedBids);
	}


	/**
	 * No bids can be placed after 12am on starting date of auction.
	 * @return true if date is before auction starting date
	 */
	public boolean isBidDateLegal(LocalDate theDate, Auction theAuction) {	
		//System.out.println("Bid date is legal: " + theDate.isBefore(theAuction.getDate()));
		return theDate.isBefore(theAuction.getDate());
	}


	/**
	 * Bid amount must be greater than minimum bid amount
	 * @return true if bid amount is greater than or equal to minimum accepted amount
	 */
	public boolean isBidAmountLegal(Double theAmount, Item theItem) {	
		//System.out.println("Bid amount is legal: " + (theAmount >= theItem.getBasePrice()));
		return theAmount >= theItem.getBasePrice();	
	}


	/**
	 * @return ArrayList of Bids of this bidder's active bids. 
	 */
	public ArrayList<Bid> getActiveBids() {
		ArrayList<Bid> activeBids = new ArrayList<Bid>();
		Calendar calendar = Run.calendar;
		if (calendar == null) {
			calendar = AuctionCentral.calendar;
		}
		if (!calendar.getUpcomingAuctions().isEmpty() && calendar.getUpcomingAuctions() != null) {
			if (allBidsForBidder != null) {
				for (Bid theBid: allBidsForBidder) {
					if (theBid.getAuction().getDate().isAfter(LocalDate.now().plusDays(1))) {
						activeBids.add(theBid);
					}
				}
			}
		} else {
			// TODO: Error code, no active auctions
		}
		return activeBids;
	}


	/**
	 * Finding out how many bids this user has active in an auction.
	 * @param theAuction
	 * @return integer representing the number of bids user has in an auction
	 */
	public int getNumberBidsAuction(Auction theAuction) {
		int numBids = 0;
		for (ArrayList<Bid> bids: theAuction.getBids()) {
			for (Bid theBid: bids) {
				if (theBid.getBidder().equals(this)) {
					numBids++;
				}
			}
		}

		return numBids;
	}


	/**
	 * @return total number of bids user has ever placed. 
	 */
	public int getNumberTotalBids() {
		return allBidsForBidder.size();
	}


	/**
	 * Add given auction to list of auctions this bidder has interacted with. 
	 * @param theAuction
	 */
	public void addAuctionToBidderAuctions(Auction theAuction) {
		if (allAuctionsForBidder == null)
			allAuctionsForBidder = new ArrayList<Auction>();
		allAuctionsForBidder.add(theAuction);
	}


	/**
	 * @return All auctions this bidder has ever participated in. 
	 */
	public ArrayList<Auction> getAuctionsWithBids() {
		return allAuctionsForBidder;
	}


	/**
	 * Add given item to list of items this bidder has interacted with. 
	 * @param theItem
	 */
	public void addItemToBidderItems(Item theItem) {
		if (allItemsForBidder == null)
			allItemsForBidder = new ArrayList<Item>();
		allItemsForBidder.add(theItem);
	}


	/**
	 * @return All items this bidder has ever bid on. 
	 */
	public ArrayList<Item> getAllItems() {
		return allItemsForBidder;
	}


	/**
	 * Add given bid to list of bids this bidder has interacted with. 
	 * @param theBid
	 */
	public void addBidToBidderBids(Bid theBid) {
		if (allBidsForBidder == null) 
			allBidsForBidder = new ArrayList<Bid>();
		allBidsForBidder.add(theBid);
	}


	/**
	 * @return All bids this bidder has ever placed
	 */
	public ArrayList<Bid> getAllBids() {
		return allBidsForBidder;
	}


	/**
	 * @return Amount the user bid on that item
	 */
	public Double getBidAmount(Item theItem) {
		Double bidAmt = new Double(0);	
		for (Bid bid: allBidsForBidder) {
			if (bid.getItem().equals(theItem)) {
				bidAmt = bid.getBidAmount();
			}
		}

		return bidAmt;
	}


	/**
	 * @return Auction an item belonged to.  
	 */
	public Auction getAuction(Item theItem) {
		Auction itemAuction = null;
		for (Auction auction: allAuctionsForBidder) {
			if (auction.getInventory().contains(theItem)) {
				itemAuction = auction;
			}
		}
		return itemAuction;
	}
	
	public Boolean containsItem(Item item) {
		return getAllItems().contains(item);
	}

	/**
	 * Removes a bid from the list of bids.
	 * @param theBid
	 */
	public void removeBid(Bid theBid) {
		allBidsForBidder.remove(theBid);
	}
	
	public Boolean canBid() {
		return Integer.compare(getNumberTotalBids(), maxAllowedBids) < 0;
	}
}