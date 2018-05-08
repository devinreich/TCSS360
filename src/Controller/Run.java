package Controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import Model.Bidder;
import Model.User;
import Model.Calendar;
import Model.ContactPerson;
import Model.Organization;
import Model.User;
import View.AuctioneerMenu;
import View.BidderMenu;

import java.time.LocalDate;
import java.util.Scanner;

public class Run {

	private static User user;
	private static String userType;
	public static LocalDate DATE = LocalDate.now();
	public static Calendar calendar;


	public static void main(String[] args) {
		login();
	}

	private static void login() {
		System.out.println("Welcome to Auction Central");
		System.out.println("Today's date is: " + DATE.toString());
		System.out.println("Please provide your username:");
		Scanner scan = new Scanner(System.in);
		String username = scan.nextLine();
		try {
			FileInputStream fileIn = new FileInputStream("src/SerializedObjects/" + username +".ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			user = (User) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("Class not Found");
			c.printStackTrace();
			return;
		}
		if (user instanceof Bidder) {
			System.out.println("You are logged in as: " + user.getName() + " (Bidder)");
			openMenu("Bidder");
		} else if (user instanceof ContactPerson) {
			openMenu("Organization");
		}
	}

	public static void openMenu(String userType) {

		try {
			FileInputStream fileIn = new FileInputStream("src/SerializedObjects/calendar.ser");
			ObjectInputStream in2 = new ObjectInputStream(fileIn);
			calendar = (Calendar) in2.readObject();
			in2.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("Class not Found");
			c.printStackTrace();
			return;
		}

		if (userType == "Bidder") {
			BidderMenu bMenu = new BidderMenu((Bidder) user, calendar);
			bMenu.launchMenu();
		} else if (userType == "Organization" ) {
			ContactPerson cPerson = (ContactPerson) user;
			AuctioneerMenu aMenu = new AuctioneerMenu(cPerson, calendar);
			aMenu.launchMenu();
		}
	}
}
