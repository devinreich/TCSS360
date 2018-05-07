package View;

import java.util.ArrayList;
import java.util.Scanner;

import Model.Auction;
import Model.Bidder;
import Model.Item;

public class BidderMenu {
	
	private Bidder bidder;
	
	public BidderMenu(Bidder theBidder) {
		bidder = theBidder;
	}
	
	public void launchMenu() {
		System.out.println("Which option would you like to choose?");
		Scanner scanner = new Scanner(System.in);
		String option;
		do {
			
			displayBidderStartMenu(scanner);
			
			
			System.out.println("\nEnter c to continue, q to quit.");
			option = scanner.next();
		} while (option != "q");
		
	}
	
	public void displayBidderStartMenu(Scanner theScanner) {
		
		System.out.println("   1) View Auctions Pertaining To Your Active Bids");
		System.out.println("   2) View All Items Pertaining To Your Active Bids");
		System.out.println("   3) View All Biddable Auctions");
		System.out.println("Enter Your Choice: ");
		
		int choice = theScanner.nextInt();
		
		switch(choice) {
		
		case 1:
			displayAuctionsWithItemBids();
			
		case 2:
			
			
			
		}
		
		
		
	}
	
	public void displayAuctionsWithItemBids() {
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
