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
		
		//Only print out options the user can access 
		if (auctioneer.hasActiveAuction()) {
			
			int choice = printActiveAuctionMenu();
			
			switch(choice) {
			
			case 1:
				//add method to organization
				auctioneer.displayCurrentAuctionDetails();
				break;
			case 2:
				//format output for method within organization class
				auctioneer.getAuctions();
				break;
			case 3:
				//add method to organization
				auctioneer.cancelCurrentAuction();
				break;
			}
				
				
		} else {
			printInactiveAuctionMenu();
		}

	}
	
	public int printActiveAuctionMenu() {
		
		System.out.println("   1) View Current Auction");
		System.out.println("   2) View All Auctions");
		System.out.println("   3) Cancel Active Auction");
		System.out.println("Enter your choice: ");
		Scanner scanner = new Scanner(System.in);
		int choice;
		choice = scanner.nextInt();
		return choice;
		// Should check for error input?
	}

	public void printInactiveAuctionMenu() {
		
		System.out.println("   1) Submit New Auction Request");
		System.out.println("   2) View All Auctions");

	}
}
