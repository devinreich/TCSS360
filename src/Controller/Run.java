package Controller;

import Model.User;
import java.util.Scanner;

public class Run {
	
	private static User user;
	private static String userType;
	

	public static void main(String[] args) {
		login();
		openMenu(userType);
		
	}
	
	private static void login() {
		System.out.println("Welcome to Auction Central");
		System.out.println("Please provide your username:");
		Scanner scan = new Scanner(System.in);
		String username = scan.nextLine();
		
		// retrieve user from serialized object
	}
	
	public static void openMenu(String userType) {
		if (userType == "Bidder") {
			BidderMenu bMenu = new BidderMenu();
			bMenu.launchMenu();
		} else if (userType == "Organization" ) {
			AuctioneerMenu aMenu = new AuctioneerMenu((Organization) user);
			aMenu.launchMenu();
		}
	}
}
