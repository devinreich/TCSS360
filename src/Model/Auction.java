package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

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
	private LocalTime startTime;
	private Integer maxItemsPerBidder;
	private Organization forOrganization;
	private static ArrayList<Item> inventory;

	public Auction() {
		inventory = new ArrayList<Item>();
	}
	
	public Auction(LocalDate theStartDate, LocalDate theEndDate, LocalDate theCreateDate,
				   LocalTime theStartTime, Integer theMaxItemsPerBidder, 
				   Organization theOrganization) {
		
		startDate = theStartDate;
		endDate = theEndDate;
		createDate = theCreateDate;
		startTime = theStartTime;
		maxItemsPerBidder = theMaxItemsPerBidder;
		forOrganization = theOrganization;
		inventory = new ArrayList<>();
	}
				 

	public static void addItem() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter Item Name: ");
		String itemName = scanner.nextLine();
		System.out.print("\nEnter Item Description: ");
		String itemDescription = scanner.nextLine();
		System.out.print("\nEnter minimum bid price for item: $");
		double basePrice = scanner.nextDouble();
		LocalDate createDate = LocalDate.now();
		Item item = new Item(itemName, itemDescription, basePrice, createDate);
		inventory.add(item);		
	}
	
	public void removeItem(Item theItem) {
		int index = 0;
		for(Item myItem : inventory) {
			if( !myItem.equals(theItem)) {
				index++;
			}
		}
		inventory.remove(index);
		
		
		
	}
	
	public void addItem(Item theItem) {
		inventory.add(theItem);
	}
	
	public void viewItem(Item theItem) {
		System.out.println("Item Name: "+ theItem.getName());
		System.out.println("Item Description: " + theItem.getDescription());
		System.out.println("Item Base Price: "+theItem.getBasePrice());
		System.out.println("Item Create Date: "+ theItem.getCreationDate());
	}
	
	public ArrayList<Item> getInventory() {
		return inventory;
	}
	
	public void setInventory(ArrayList<Item> theItems) {
		inventory = theItems;
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
