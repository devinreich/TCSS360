package Controller;

//import Model.Bidder;
//import Model.User;
//import Model.Calendar;
//import Model.ContactPerson;
//import View.AuctioneerMenu;
//import View.BidderMenu;

import java.time.LocalDate;
import java.util.Scanner;

import Model.Bidder;
import Model.Calendar;
import Model.ContactPerson;
import Model.User;
import View.AuctioneerMenu;
import View.BidderMenu;

public class Run {

	private static User user;
	public static LocalDate DATE = LocalDate.now();
	//public static Calendar calendar;
	public static Serializer SERIALIZER = new Serializer();
	public static Calendar calendar = new Calendar();

	// Main
	public static void main(String[] args) {
		login();
	}

	// Login
	private static void login() {		
		System.out.println("Welcome to Auction Central");
		System.out.println("Today's date is: " + DATE.toString());
		System.out.println("Please provide your username:");
		Scanner scan = new Scanner(System.in);
		String username = scan.nextLine();
		user = (User) Serializer.deserialize(username);
		calendar =  (Calendar) Serializer.deserializeCalendar(calendar);	
		if (user instanceof Bidder) {
			System.out.println("You are logged in as: " + user.getName() + " (Bidder)");
			openMenu("Bidder");
		} else if (user instanceof ContactPerson) {
		System.out.println("You are logged in as: " + user.getName() + " (Contact Person)");
			openMenu("Organization");
		}
	}

	
	/**
	 * Opens the appropriate menu for either a bidder or a user. 
	 * @param userType
	 */
	//public static void openMenu(String userType) {
	public static void openMenu(String userType) {

		//calendar = (Calendar) Serializer.deserialize("calendar");
		
		if (userType == "Bidder") {
			BidderMenu bMenu = new BidderMenu((Bidder) user);
			bMenu.launchMenu();
		} else if (userType == "Organization" ) {
			ContactPerson cPerson = (ContactPerson) user;
			AuctioneerMenu aMenu = new AuctioneerMenu(cPerson);
			aMenu.launchMenu();
		}
		Serializer.serialize(user, user.getLoginName());
		Serializer.serialize(calendar, "calendar");
	}
}
