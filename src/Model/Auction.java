package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import Model.Bidder;

/**
 * Auction Class. Stores inventory of Items and associated list of bids. 
 * @author prancingponies
 */
public class Auction implements Serializable {
	
	private static final long serialVersionUID = -5630295913008886033L;
	private static final Integer DEFAULT_MAX_ITEMS_SOLD = 10;
	private static final Integer DEFAULT_MAX_ITEMS_PER_BIDDER = 4;
	private LocalDate auctionDate;
	private LocalDate createDate;
	private Integer maxItemsPerBidder;
	private Integer maxItemsSold;
	private Organization forOrganization;
	private HashMap<Item, ArrayList<Bid>> inventory;
	
	/**
	 * Constructor to create an Auction.
	 * If the user inputs 0 for Max Items, then the number specifies to default 
	 * (Specified in menu)
	 * @param theAuctionDate
	 * @param theCreateDate
	 * @param theStartTime
	 * @param theMaxItemsPerBidder
	 * @param theOrganization
	 */
	public Auction(LocalDate theAuctionDate, LocalDate theCreateDate, Integer theMaxItemsPerBidder, 
			Integer theMaxItemsSold, Organization theOrganization) {
		auctionDate = theAuctionDate;
		createDate = theCreateDate;
<<<<<<< HEAD
		startTime = theStartTime;
		maxItemsPerBidder = theMaxItemsPerBidder;
		forOrganization = theOrganization;
		inventory = new ArrayList<>();
		auctionBids = new ArrayList<Bid>();
	}
	
	// no specified max items per bidder- resort to default
	public Auction(LocalDate theStartDate, LocalDate theEndDate, LocalDate theCreateDate,
			   LocalTime theStartTime, Organization theOrganization) {
	
	startDate = theStartDate;
	endDate = theEndDate;
	createDate = theCreateDate;
	startTime = theStartTime;
	maxItemsPerBidder = DEFAULT_MAX_ITEMS;
	forOrganization = theOrganization;
	inventory = new ArrayList<>();
	auctionBids = new ArrayList<Bid>();
}
				 
=======
		// Max items per bidder- either use number user gives, or default
		if (theMaxItemsPerBidder == 0) 
			maxItemsPerBidder = DEFAULT_MAX_ITEMS_PER_BIDDER;
		else maxItemsPerBidder = theMaxItemsPerBidder;
		// Max items sold- either use number user gives, or default
		if (theMaxItemsSold == 0) 
			maxItemsSold = DEFAULT_MAX_ITEMS_SOLD;
		else maxItemsSold = theMaxItemsSold;
		forOrganization = theOrganization;	
		HashMap<Item, ArrayList<Bidder>> inventory = new HashMap<Item, ArrayList<Bidder>>();
	}		
	
	/**
	 * Add an item to the auction's inventory
	 * using scanner input from console.
	 */
>>>>>>> c422dd62581a4440860889ab3e0ba930cb8021cf
	public void addItem() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter Item Name: ");
		String itemName = scanner.nextLine();
		System.out.print("Enter Item Description: ");
		String itemDescription = scanner.nextLine();
		System.out.print("Enter minimum bid price for item: $");
		double basePrice = scanner.nextDouble();
		LocalDate createDate = LocalDate.now();
		Item item = new Item(itemName, itemDescription, basePrice, createDate);
		addItem(item);
	}
	
	
	/**
	 * Add a specific item to an Auction.
	 * @param theItem
	 */
	public void addItem(Item theItem) {
		if (inventory != null) {
			if (!isAuctionAtMaxCapacity()) {
				// Check item doesn't already exist as key in map
				if (inventory.containsKey(theItem)) {
					//ERROR CODE: ITEM ALREADY EXISTS
					System.out.println("That item already exists in the inventory.");
				} else
					inventory.put(theItem, new ArrayList<Bid>());
			} else if (isAuctionAtMaxCapacity()) {
				//ERROR CODE: AUCTION AT MAX CAPACITY
				System.out.println("\nYour auction is at maximum capacity");
			} 
		} else {
			inventory = new HashMap<Item, ArrayList<Bid>>();
			inventory.put(theItem, new ArrayList<Bid>());				
		} 						
	}
	
	
	/**
	 * Places a bid and adds it to the Inventory map for 
	 * the Item & the item's associated list of bids. 
	 * @param theAmount
	 * @param theDate
	 * @param theBidder
	 * @param theItem
	 */
	public void placeBid(Double theAmount, LocalDate theDate, Bidder theBidder, Item theItem) {		
		//is bid date legal, is bid amount legal, is auction legal, is bidder legal
		if (theBidder.isAuctionLegalForBidder(this) && theBidder.isBidderLegal() 
				&& theBidder.isBidAmountLegal(theAmount, theItem) 
				&& theBidder.isBidDateLegal(theDate, this)) {
			
			Bid bid = new Bid(theAmount, theDate, theItem, this, theBidder);
			theBidder.addBidToBidderBids(bid);; 
			theBidder.addItemToBidderItems(theItem);
			theBidder.addAuctionToBidderAuctions(this);	
			ArrayList<Bid> currBids = inventory.get(theItem);
			currBids.add(bid);
			inventory.put(theItem, currBids);
			System.out.println("Your bid has been successfully placed!");
		} else {
			System.out.println("Your bid could not be placed.");
		}
	}
	
	
	/**
	 * @param theItem The Item you want details about. 
	 */
	public void viewItem(Item theItem) {
		System.out.println("Item Name: "+ theItem.getName());
		System.out.println("Item Description: " + theItem.getDescription());
		System.out.println("Item Base Price: "+theItem.getBasePrice());
		System.out.println("Item Create Date: "+ theItem.getCreationDate());
	}
		
	
	/**
	 * Remove item at a specific index in the presented menu. 
	 * @param theIndex index of the Item the user wants to remove from
	 * 	the list. 
	 */
	public void removeItemAt(int theIndex) {
		List<Item> keys = new ArrayList<Item>(inventory.keySet());	
		Item item = keys.get(theIndex);
		inventory.remove(item);		
	}
	
	
	/**
	 * Can remove Item directly from Auction inventory as 
	 * the item is a key. 
	 * @param theItem
	 */
	public void removeItem(Item theItem) {
		inventory.remove(theItem);	
	}
	
	
	/**
	 * @return A list containing Items in this auction.
	 */
	public ArrayList<Item> getInventory() {
		return new ArrayList<Item>(inventory.keySet());
	}
	
	
	/**
	 * @return Number of bids placed on this auction.
	 */
	public Integer getNumberOfBidsForAuction() {
		
		Integer numberBids = 0;
		for (ArrayList<Bid> bids: this.getBids()) {
			for (Bid bid: bids) {
				if (bid != null) {
					numberBids++;
				}
			}	
		}
		return numberBids;
	}
	
	
	/**
	 * @return A list of ArrayLists containing the bids. 
	 */
	public Collection<ArrayList<Bid>> getBids() {
		return inventory.values();
	}
	
	
	//	public void setInventory(ArrayList<Item> theItems) {
	//		inventory = theItems;
	//	}
		
		
	//	public void setMaxBiddableItems(Integer maxBiddableItems) {
	//	maxItemsPerBidder = maxBiddableItems;
	//}

	
	/**
	 * @return Auction create date
	 */
	public LocalDate getCreateDate() {
		return createDate;
	}


	/**
	 * @return Date of Auction
	 */
	public LocalDate getDate() {
		return auctionDate;
	}
	

	/**
	 * @return Organization this Auction belongs to. 
	 */
	public Organization getOrganization(){
		return forOrganization;
	}
	

	/**
	 * Make sure auction under max capacity of Items
	 * @return true if max capacity is reached
	 */
	public boolean isAuctionAtMaxCapacity() {
		return getInventoryCount() == maxItemsSold;	
	}
	

	/**
	 * Return integer inventory coutn
	 * @return
	 */
	public int getInventoryCount() {
		return inventory == null ? 0 : inventory.size();
	}
	
	
	/**
	 * @return Max number of items bidders can bid on
	 */
	public Integer getMaxBiddableItems() {
		return maxItemsPerBidder;
	}
	
	
	/**
	 * @return True if no bids currently on auction
	 */
	public boolean canAuctionBeCancelled() {
		return this.getNumberOfBidsForAuction() == 0;
	}
}
