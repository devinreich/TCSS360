package Model;

import java.util.ArrayList;
import java.util.Date;

public class Auction {
	
	private Date startDate;
	private Date endDate;
	private int maxItemsPerBidder;
	private static final int DEFAULT_MAX_ITEMS = 5;
	private Organization forOrganization;
	private Date creationDate;
	private String location; // make a location object?
	private ArrayList<Item> inventory;
	
	public Auction(Date testStartDate, Date testEndDate,
				   Date testCreationDate,
				   int theMaxItemsPerBidder, Organization theOrganization) {
		
		startDate = testStartDate;
		endDate = testEndDate;
		creationDate = testCreationDate;
		maxItemsPerBidder = theMaxItemsPerBidder;
		forOrganization = theOrganization;
		inventory = new ArrayList<>();
		
		
	}
				   
	
	
	public void addItem(Item item) {
		// check if legal?
		inventory.add(item);
	}
	
	public int getInventoryCount() {
		return inventory.size();
	}
	
	public Date getStartDate(){
		return startDate;
	}
	public Date getEndDate(){
		return endDate;
	}
	//Checks if the auction is at the default max capacity.
	//Returns true if inventory size equals default capacity.
	public boolean isAuctionAtMaxCapacity() {
		
		return getInventoryCount() == DEFAULT_MAX_ITEMS;
			
	}
	
}
