package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Auction {
	
	private LocalDate startDate;
	private LocalDate endDate;
	private int maxItemsPerBidder;
	private static final int DEFAULT_MAX_ITEMS = 5;
	private Organization forOrganization;
	private LocalDate creationDate;
	private String location; // make a location object?
	private ArrayList<Item> inventory;
	
	public Auction(LocalDate testStartDate, LocalDate testEndDate,
			LocalDate testCreationDate, int theMaxItemsPerBidder, Organization theOrganization) {
	
		startDate = testStartDate;
		endDate = testEndDate;
		creationDate = testCreationDate;
		maxItemsPerBidder = theMaxItemsPerBidder;
		forOrganization = theOrganization;
		inventory = new ArrayList<>();
	}
				   
	public LocalDate getStartDate() {
		return startDate;
	}
	
	public void addItem(Item item) {
		// check if legal?
		inventory.add(item);
	}
	
	public int getInventoryCount() {
		return inventory.size();
	}
	
	//Checks if the auction is at the default max capacity.
	//Returns true if inventory size equals default capacity.
	public boolean isAuctionAtMaxCapacity() {
		return getInventoryCount() == DEFAULT_MAX_ITEMS;
			
	}
	
}
