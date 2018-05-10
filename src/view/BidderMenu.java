package View;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import Model.Auction;
import Model.Bid;
import Model.Bidder;
import Model.Calendar;
import Model.Item;

public class BidderMenu {

	private Bidder bidder;
	private Calendar calendar;

	public BidderMenu(Bidder theBidder, Calendar theCalendar) {
		bidder = theBidder;
		calendar = theCalendar;
	}

	public void launchMenu() {
		
		System.out.println("Which option would you like to choose?");
		Scanner scanner = new Scanner(System.in);
		String option;
		do {
			
			displayBidderStartMenu(scanner);			

			System.out.println("\nEnter c to continue, q to quit.");
			option = scanner.next();
		} while (!option.equals("q"));

	}

	public void displayBidderStartMenu(Scanner theScanner) {

		System.out.println("   1) View Auctions Pertaining To Your Active Bids");
		System.out.println("   2) View All Items Pertaining To Your Active Bids");
		System.out.println("   3) View All Biddable Auctions\n");
		System.out.println("Enter Your Choice: ");

		int choice = theScanner.nextInt();

		switch(choice) {

		case 1:
			displayAuctionsWithItemBids();
			break;
		case 2:
			displayItemBids();
			break;
		case 3:
			displayBiddableAuctions(theScanner);
			break;

		}

	}

	//View all auctions in which bidder has placed bids
	public void displayAuctionsWithItemBids() {
		if (bidder.getAuctionsWithBids().isEmpty()) {
			System.out.println("There are currently no auctions with items you've bid on.");
		} else {

			ArrayList<Auction> bidderAuctions = bidder.getAuctionsWithBids();
			for (Auction a: bidderAuctions) {
				System.out.println("Auction hosted by: " + a.getOrganization() + ".");
				System.out.println("Auction date: " + a.getStartDate());

				System.out.println("Item/s: ");
				for (int j = 0; j < bidderAuctions.size(); j++) {

					//checks auction inventory for items the user has bid on
					if (a.getInventory().contains(bidder.getBids().get(j))){
						Item item = bidder.getBids().get(j).getItem();
						System.out.println(item.getName());
					}
				}
			}	
		}
	}

	//View all items bidder has bid on
	public void displayItemBids() {
		System.out.println("Displaying Items You Have Bid On: ");
		for (Bid bid: bidder.getBids()) {
			String itemName = bid.getItem().getName();
			System.out.println("Item: " + itemName);			
		}		
	}

	//show all auctions that have bidder can bid on
	public void displayBiddableAuctions(Scanner theScanner) {
		if (calendar.getUpcomingAuctions().isEmpty()) {
			System.out.println("There are currently no auctions accepting bids.");
		} else {

			System.out.println("These are the auctions that are accepting bids: ");
			int index = 1;
			ArrayList<Auction> biddableAuctions = calendar.getUpcomingAuctions();
			for (Auction a: (ArrayList<Auction>) biddableAuctions) {
				String orgName = a.getOrganization().getName();
				System.out.println("   " + index + ") Auction hosted by: " + orgName);
				System.out.println("      Auction date: " + a.getStartDate());
				index++;
			}
			System.out.println("Enter the number of your auction of choice: ");
			int choice = theScanner.nextInt();

			displayAuctionBidMenu(biddableAuctions.get(choice-1), theScanner);
		}

	}

	public void displayAuctionBidMenu(Auction theAuction, Scanner theScanner) {
		String orgName = theAuction.getOrganization().getName();
		System.out.println("You have selected the auction hosted by" + orgName);
		System.out.println("Auction Inventory: ");
		int index = 1;
		for (Item item: theAuction.getInventory()) {
			System.out.println("   " + index + ") Item Name: " + item.getName());
			System.out.println("     Starting Price: $" + item.getBasePrice());
		}

		System.out.println("Enter the number of the item you wish to bid on: ");
		int choice = theScanner.nextInt();
		Item itemChoice = theAuction.getInventory().get(choice-1);
		System.out.println("You have selected the " + itemChoice.getName());
		System.out.println("Enter your bid (must be larger than current price):");
		double bidPrice = theScanner.nextDouble();
		LocalDate date = LocalDate.now();
		Bid itemChoiceBid = new Bid(new Double(bidPrice), date, itemChoice, theAuction, bidder);
		bidder.placeBid(itemChoiceBid);
	}
}
