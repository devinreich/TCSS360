package Controller;

import java.util.ArrayList;
import java.util.Scanner;

import Model.Auction;
import Model.Organization;
import Model.User;

public class AuctioneerMenu {
	
	private Organization auctioneer;
	
	public AuctioneerMenu(Organization theAuctioneer) {
		auctioneer = theAuctioneer;
	}
	
	public void launchMenu() {
		
		System.out.println("Which option would you like to choose?");
		
		if (auctioneer.hasActiveAuction()) {
			printActiveAuctionMenu();
			
			switch(choice) {
			
			case 1:
				auctioneer.displayCurrentAuctionDetails();
				break;
			case 2:
				auctioneer.displayAllAuctions();
				
				
		} else {
			printInactiveAuctionMenu();
		}
		System.out.println("Enter your choice: ");
		Scanner scanner = new Scanner(System.in);
		int choice;
		choice = scanner.nextInt();
		// Should check for error input?
		
		 
			
		
		
		}
		
		
	}
	
	public void printActiveAuctionMenu() {
		
		System.out.println("   1) View Current Auction");
		System.out.println("   2) View All Auctions");
		System.out.println("   3) Cancel Active Auction");
	}

	public void printInactiveAuctionMenu() {
		
		System.out.println("   1) Submit New Auction Request");
		System.out.println("   2) View All Auctions");

	}
}
