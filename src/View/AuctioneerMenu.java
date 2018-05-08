package View;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import Controller.Serializer;
import Model.Auction;
import Model.Calendar;
import Model.ContactPerson;
import Model.Item;
import Model.Organization;
import Model.User;

public class AuctioneerMenu {

	private ContactPerson contact;
	private Organization auctioneer;
	private Calendar calendar;
	public static Serializer SERIALIZER;

	public AuctioneerMenu(ContactPerson theAuctioneer, Calendar theCalendar, Serializer theSerializer) {
		SERIALIZER = theSerializer;
		contact = theAuctioneer;
		auctioneer = theAuctioneer.getAffiliatedOrganization();
		calendar = theCalendar;
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

				case 1:
					//add method to organization
					int auctionChoice;
					
					do {
					auctioneer.displayCurrentAuctionDetails();
					auctionChoice = displayAuctionDetailMenu(scanner);
					auctionMenuOptions(auctionChoice, scanner);
					} while (auctionChoice != 4);
					break;
				case 2:
					//format output for method within organization class
					ArrayList<Auction> auctioneerAuctions = auctioneer.getAuctions();
					int j = 1;
					for (int i = 0; i < auctioneerAuctions.size(); i++) {
						Auction auction = auctioneerAuctions.get(i);
						System.out.println("   " + j + ") Start Date: " + auction.getStartDate() 
						+ ", End Date: " + auction.getEndDate());
						j++;

					}
					break;
				case 3:
					//add method to organization
					auctioneer.cancelCurrentAuction();
					break;
				}

			} else {
				int choice = displayInactiveAuctionMenu(scanner);

				switch(choice) {
				case 1: 
					//add method to organization?
					calendar.submitAuctionRequest(auctioneer);

					break;
				case 2: 
					auctioneer.getAuctions();
				}			
			}
			if (scanner.hasNextLine()) {
				scanner.nextLine();
			}
			System.out.println("\nEnter c to continue, q to quit.");
			SERIALIZER.serializeCalendar(calendar);
			SERIALIZER.serializeContactPerson(contact);
			option = scanner.next();
			
			
		} while (!option.equals("q"));

	}

	//Display menu for organization that has an active auction
	public int displayActiveAuctionMenu(Scanner theScanner) {

		System.out.println("   1) View Current Auction");
		System.out.println("   2) View All Auctions");
		System.out.println("   3) Cancel Active Auction");
		System.out.println("Enter your choice: ");

		int choice = theScanner.nextInt();
		return choice;
		// Should check for error input?
	}

	//Display menu for organization that has no active auction
	public int displayInactiveAuctionMenu(Scanner theScanner) {

		System.out.println("   1) Submit New Auction Request");
		System.out.println("   2) View All Auctions");
		System.out.println("Enter your choice: ");

		int choice = theScanner.nextInt();
		return choice;

	}

	//Display current auction menu options
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

	//complete actions based on auction menu option
	public void auctionMenuOptions(int theOption, Scanner theScanner) {

		Auction currentAuction = auctioneer.getCurrentAuction();

		while (theOption > 4 || theOption < 1) {
			System.out.println("Not a valid option, try again.");
			theOption = theScanner.nextInt();
		}
		ArrayList<Item> items = currentAuction.getInventory();

		switch(theOption) {

		//Add item
		case 1:
			//currentAuction.addItem();
			
		
				Scanner scanner = new Scanner(System.in);
				System.out.print("Enter Item Name: ");
				String itemName = scanner.nextLine();
				System.out.print("\nEnter Item Description: ");
				String itemDescription = scanner.nextLine();
				System.out.print("\nEnter minimum bid price for item: $");
				double basePrice = scanner.nextDouble();
				LocalDate createDate = LocalDate.now();
				Item item = new Item(itemName, itemDescription, basePrice, createDate);
				items.add(item);	
	
			break;
			//Remove item
		case 2:
			for (int i = 0; i < items.size(); i++) {
				System.out.println("Item " + ++i + ": " 
						+ items.get(i));
			}
			System.out.print("Enter index of item to remove: ");
			int itemIndex = theScanner.nextInt();
			currentAuction.removeItem(items.remove(itemIndex));
			currentAuction.setInventory(items);
			break;
			//View all items
		case 3:		
			for (int i = 0; i < items.size(); i++) {
				int index = i + 1;
				System.out.println("Item " + index + ": " 
						+ items.get(i).getName() + ", " + items.get(i).getDescription()
						+ ", $" + items.get(i).getBasePrice());
			}

		case 4:
			//Return to previous menu

		}


	}
}
