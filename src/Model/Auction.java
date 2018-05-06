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
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalDate createDate;
	private Integer maxItemsPerBidder;
	private Organization forOrganization;
	private ArrayList<Item> inventory;
	

	public Auction(LocalDate thestartDate, LocalDate theendDate, LocalDate thecreateDate,
			Integer theMaxItemsPerBidder, Organization theOrganization) {
	
		startDate = thestartDate;
		endDate = theendDate;
		createDate = thecreateDate;
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
	

	public LocalDate getCreateDate() {
		return createDate;
	}


	public LocalDate getStartDate() {
		return startDate;
	}
	

	public LocalDate getEndDate(){
		return endDate;
	}
	

	public Organization getOrganization(){
		return forOrganization;
	}
	

	public boolean isAuctionAtMaxCapacity() {
		return getInventoryCount() == DEFAULT_MAX_ITEMS;	
	}	
}
