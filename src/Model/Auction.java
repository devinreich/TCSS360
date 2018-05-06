package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Auction Class. 
 * Contains an inventory of Items. 
 * @author PrancingPonies
 * @version 5/3/2018
 */
public class Auction implements Serializable {
	
	private static final long serialVersionUID = -657206927328048783L;
	private static final Integer DEFAULT_MAX_ITEMS = 5;
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalDate createDate;
	private Integer maxItemsPerBidder;
	private Organization forOrganization;
	private ArrayList<Item> inventory;

	public Auction() {
		inventory = new ArrayList<Item>();
	}
	
	public Auction(LocalDate thestartDate, LocalDate theendDate, LocalDate thecreateDate,
			Integer theMaxItemsPerBidder, Organization theOrganization) {
	
<<<<<<< HEAD
		startDate = thestartDate;
		endDate = theendDate;
		createDate = thecreateDate;
=======
	public Auction(Date theStartDate, Date theEndDate,
				   Date theCreationDate,
				   int theMaxItemsPerBidder, Organization theOrganization) {
		
		startDate = theStartDate;
		endDate = theEndDate;
		creationDate = theCreationDate;
>>>>>>> B-Branch
		maxItemsPerBidder = theMaxItemsPerBidder;
		forOrganization = theOrganization;
		inventory = new ArrayList<>();
	}
				 

	public void addItem(Item item) {
		// check if legal?
		inventory.add(item);
	}
	
	
	public ArrayList<Item> getInvetory() {
		return inventory;
	}

	
	public int getInventoryCount() {
		return inventory.size();
	}
	
<<<<<<< HEAD
	
=======
<<<<<<< HEAD
	/**
	 * Returns create date of auction.
	 * @return {@link Auction#createDate} 
	 * @version 5/3/2018
	 */
>>>>>>> e5e7233f9df2f695a20dc0b5acbd9383cd9f4124
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
=======
	
	
	//Checks if the auction is at the default max capacity.
	//Returns true if inventory size equals default capacity.
	public boolean isAuctionAtMaxCapacity() {
		
		return getInventoryCount() == DEFAULT_MAX_ITEMS;
			
	}
	

	
>>>>>>> B-Branch
}
