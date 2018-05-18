package View;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import Controller.Run;
import Model.Auction;
import Model.ContactPerson;
import Model.Item;
import Model.Organization;

public class AuctioneerMenu {

	private ContactPerson contact;
	private Organization auctioneer;
	//private Calendar calendar;

	/** 
	 * Auctioneer (Organization/Contact person) menu constructor
	 * @param theAuctioneer
	 * @param theCalendar
	 */
	//public AuctioneerMenu(ContactPerson theAuctioneer, Calendar theCalendar) {
	public AuctioneerMenu(ContactPerson theAuctioneer) {
		contact = theAuctioneer;
		System.out.println("Auctioneer: " + contact.getName());
		auctioneer = theAuctioneer.getAffiliatedOrganization();
		//calendar = theCalendar;
	}

	public void launchMenu() {

		System.out.println("Which option would you like to choose?");
		Scanner scanner = new Scanner(System.in);
		String option;

		do {
			//Only print out options the user can access 
			if (auctioneer.hasActiveAuction()) {

				int choice = displayActiveAuctionMenu(scanner);
				switch(choice) {

				case 1: // View Active Auction

					int auctionChoice;			
					do {
					auctioneer.displayCurrentAuctionDetails();
					auctionChoice = displayAuctionDetailMenu(scanner);
					auctionMenuOptions(auctionChoice, scanner);
					} while (auctionChoice != 4);
					break;
				case 2:
					// format output for method within organization class
					// TODO: Look back over this
					ArrayList<Auction> auctioneerAuctions = auctioneer.getAuctions();
					int j = 1;
					for (int i = 0; i < auctioneerAuctions.size(); i++) {
						Auction auction = auctioneerAuctions.get(i);
						System.out.println(j + ") Auction Date: " + auction.getDate()); 
							for (int x = 0; x < auction.getInventory().size(); x++) {
								int index = x + 1;
								System.out.println("Item " + index + ": " + getItemDescription(auction.getInventory().get(x)));
							}
						j++;
						System.out.println();
					}
					break;
				case 3:
					//add method to organization
					//auctioneer.cancelCurrentAuction();
					break;
				}

			} else {
				int choice = displayInactiveAuctionMenu(scanner);

				switch(choice) {
				case 1: 
					Run.calendar.submitAuctionRequest(auctioneer);
					break;
				case 2: 
					ArrayList<Auction> auctioneerAuctions = auctioneer.getAuctions();
					int j = 1;
					for (int i = 0; i < auctioneerAuctions.size(); i++) {
						Auction auction = auctioneerAuctions.get(i);
						System.out.println(j + ") Auction Date: " + auction.getDate()); 
							for (int x = 0; x < auction.getInventory().size(); x++) {
								int index = x + 1;
								System.out.println("Item " + index + ": " + getItemDescription(auction.getInventory().get(x)));
							}
						j++;
						System.out.println();
					}
					
//					auctioneer.getAuctions();
//					for (Auction auction: auctioneer.getAuctions()) {
//						System.out.println("Date: " + auction.getDate());
//					}
				}			
			}
			if (scanner.hasNextLine()) {
				scanner.nextLine();
			}
			System.out.println("\nEnter c to continue, q to quit.");
			option = scanner.next();
		} while (!option.equals("q"));

	}

	
	/**
	 * Displays menu for an Organization with an Active Auction set
	 * @param theScanner
	 * @return
	 */
	public int displayActiveAuctionMenu(Scanner theScanner) {

		System.out.println("   1) View Current Auction");
		System.out.println("   2) View All Auctions");
		System.out.println("   3) Cancel Active Auction");
		System.out.println("Enter your choice: ");

		int choice = theScanner.nextInt();
		return choice;
		// Should check for error input?
	}

	
	/**
	 * Display menu for Organization with no Active Auction set
	 * @param theScanner
	 * @return
	 */
	public int displayInactiveAuctionMenu(Scanner theScanner) {

		System.out.println("   1) Submit New Auction Request");
		System.out.println("   2) View All Auctions");
		System.out.println("Enter your choice: ");

		int choice = theScanner.nextInt();
		return choice;
	}

	
	/**
	 * Options for the current (active) auction for an Organization.
	 * @param theScanner
	 * @return
	 */
	public int displayAuctionDetailMenu(Scanner theScanner) {
		
		/** MUST REPEAT THIS MENU UNTIL USER SELECTS 4*/
		//!!!!!!!!!!!!

		System.out.println("Which option would you like to choose?");
		System.out.println("   1) Add Item To Inventory");
		System.out.println("   2) Remove Item From Inventory");
		System.out.println("   3) View Current Inventory");
		System.out.println("   4) Return To Previous Menu");

		int choice = theScanner.nextInt();
		return choice;
	}

	
	/**
	 * Lets the organization add item to inventory,
	 * remove item from inventory,
	 * view current inventory,
	 * or return to previous menu.
	 * @param theOption
	 * @param theScanner
	 */
	public void auctionMenuOptions(int theOption, Scanner theScanner) {

		Auction currentAuction = auctioneer.getCurrentAuction();

		while (theOption > 4 || theOption < 1) {
			System.out.println("Not a valid option, try again.");
			theOption = theScanner.nextInt();
		}

		switch(theOption) {
	
			case 1:	//Add item
				Scanner scanner = new Scanner(System.in);
				System.out.print("Enter Item Name: ");
				String itemName = scanner.nextLine();
				System.out.print("\nEnter Item Description: ");
				String itemDescription = scanner.nextLine();
				System.out.print("\nEnter minimum bid price for item: $");
				double basePrice = scanner.nextDouble();
				LocalDate createDate = LocalDate.now();
				Item item = new Item(itemName, itemDescription, basePrice, createDate);
				currentAuction.addItem(item);	
				break;
			case 2:	//Remove item
				for (int i = 0; i < currentAuction.getInventory().size(); i++) {
					System.out.println("Item " + (i + 1) + ": " 
							+ getItemDescription(currentAuction.getInventory().get(i)));
				}
				System.out.print("Enter index of item to remove: ");
				int itemIndex = theScanner.nextInt();
				if (itemIndex <= currentAuction.getInventoryCount() && itemIndex >= 1) {
					currentAuction.removeItemAt(itemIndex - 1);
				}
				break;	
			case 3:	//View all items		
				for (int i = 0; i < currentAuction.getInventory().size(); i++) {
					int index = i + 1;
					System.out.println("Item " + index + ": " 
							+ getItemDescription(currentAuction.getInventory().get(i)));
				}
			case 4:
				//Return to previous menu
			}
	}
	
	
	/**
	 * Returns details about a given Item object. 
	 * @param item
	 * @return
	 */
	public String getItemDescription(Item item) {
		return item.getName() + ", " + item.getDescription() 
			+ ", $" + item.getBasePrice();
	}
}