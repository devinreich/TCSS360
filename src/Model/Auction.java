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
	
<<<<<<< HEAD

=======
<<<<<<< HEAD
	//Checks if the auction is at the default max capacity.
	//Returns true if inventory size equals default capacity.
	public boolean isAuctionAtMaxCapacity() {
		
		return getInventoryCount() == DEFAULT_MAX_ITEMS;
			
=======
	/**
	 * Returns create date of auction.
	 * @return {@link Auction#createDate} 
	 * @version 5/3/2018
	 */
>>>>>>> 399f5355cffc70f16924caace2a4dd0cf4a60015
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
>>>>>>> d31e68e36c5c5146c26de57c7add69e039b2b323
	}
	

	public boolean isAuctionAtMaxCapacity() {
		return getInventoryCount() == DEFAULT_MAX_ITEMS;	
	}	
}
