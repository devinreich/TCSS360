package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

/**
 * Auction Class. 
 * Contains an inventory of Items. 
 * @author PrancingPonies
 * @version 5/3/2018
 */
public class Auction {
	
	private static final Integer DEFAULT_MAX_ITEMS = 5;
	/**
	 * The {@link LocalDate} instance representing the start date of an auction. 
	 */
	private LocalDate startDate;
	/**
	 * The {@link LocalDate} instance representing the end date of an auction. 
	 */
	private LocalDate endDate;
	/**
	 * The {@link LocalDate} instance representing the create date of an auction. 
	 */
	private LocalDate createDate;
	/**
	 * The {@link Integer} instance representing the maximum number of items bidders can bid on for an auction.
	 */
	private Integer maxItemsPerBidder;
	/**
	 * The {@link Organization} instance representing the organization holding an auction.
	 */
	private Organization forOrganization;
//	/**
//	 * The {@link String} instance representing the location of an auction.
//	 */
//	private String location; // make a location object?
	/**
	 * The {@link ArrayList<Item>} instance representing the list of Items for an Auction.
	 */
	private ArrayList<Item> inventory;
	
	/**
	 * Creates an Auction and initializes start date, end date, creation date, max items per bidder
	 * 	and the organization it's for. Creates an inventory for the Auction. 
	 * @param thestartDate	The start date of the auction
	 * @param theendDate	The end date of the auction
	 * @param thecreateDate	The date the auction was created
	 * @param theMaxItemsPerBidder	The maximum number of items per bidder for this auction
	 * @param theOrganization	The organization this auction is for
	 * @version 5/3/2018
	 */
	public Auction(LocalDate thestartDate, LocalDate theendDate, LocalDate thecreateDate,
			Integer theMaxItemsPerBidder, Organization theOrganization) {
	
		startDate = thestartDate;
		endDate = theendDate;
		createDate = thecreateDate;
		maxItemsPerBidder = theMaxItemsPerBidder;
		forOrganization = theOrganization;
		inventory = new ArrayList<>();
	}
				 
	/**
	 * Adds Item to Auction's inventory if Item is legal. 
	 * @param item	The item being added to auction inventory. 
	 * @result Auction's inventory grows by one (the item passed in). 
	 * @author brittanybyrd  
	 * @version 5/3/2018
	 */
	public void addItem(Item item) {
		// check if legal?
		inventory.add(item);
	}
	
	/**
	 * Returns size of inventory. 
	 * @return Size of {@link Auction#inventory}. 
	 * @version 5/3/2018
	 */
	public int getInventoryCount() {
		return inventory.size();
	}
	
	/**
	 * Returns create date of auction.
	 * @return {@link Auction#createDate} 
	 * @version 5/3/2018
	 */
	public LocalDate getCreateDate() {
		return createDate;
	}

	/**
	 * Returns start date of auction.
	 * @return {@link Auction#startDate} 
	 * @version 5/3/2018
	 */
	public LocalDate getStartDate() {
		return startDate;
	}
	
	/**
	 * Returns end date of auction.
	 * @return {@link Auction#endDate} 
	 * @version 5/3/2018
	 */
	public LocalDate getEndDate(){
		return endDate;
	}
	
	/**
	 * Returns the Organization holding an auction
	 * @return {@link Auction#forOrganization} 
	 * @version 5/3/2018
	 */
	public Organization getOrganization(){
		return forOrganization;
	}
	
	/**
	 * Checks if the auction is at the default max capacity.
	 * @return Returns true if inventory size equals default capacity.
	 * @version 5/3/2018
	 */
	public boolean isAuctionAtMaxCapacity() {
		return getInventoryCount() == DEFAULT_MAX_ITEMS;	
	}	
}
