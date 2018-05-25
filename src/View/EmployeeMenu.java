<<<<<<< HEAD
=======

>>>>>>> 47cee6788bf17e2ed1850a5f8fae86db00612a87
package View;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import Controller.Run;
import Model.Auction;
import Model.Employee;

public class EmployeeMenu {

	private Employee employee;

	public EmployeeMenu(Employee theEmployee) {
		employee = theEmployee;
	}

	public void launchMenu() {
		System.out.println("Which option would you like to choose?");
		Scanner scanner = new Scanner(System.in);
		String option;

		do {
			displayEmployeeStartMenu(scanner);
			
			System.out.println("\nEnter c to continue, q to quit.");
			option = scanner.next();
		} while (!option.equals("q"));

	}

	public void displayEmployeeStartMenu(Scanner theScanner) {

		System.out.println("1)  Change Maximum Number Of Upcoming Auctions System Can Hold");
		System.out.println("2)  View All Auctions Between Two Dates");
		System.out.println("3)  View All Auctions In Chronological Order");
		System.out.println("4)  Cancel An Upcoming Auction");
		System.out.println("Enter your choice: ");
		
		switch(theScanner.nextInt()) {
		
		case 1:
			System.out.println("Current System Auction Capacity: " + Run.calendar.getMaximumUpcomingAuctions());
			System.out.println("Enter New System Auction Capacity: ");
			Run.calendar.setMaximumUpcomingAuctions(theScanner.nextInt());
			break;					
		case 2: 
			ArrayList<LocalDate> auctionDates = getDatesFromUser(theScanner);
			ArrayList<Auction> auctions = Run.calendar.getAuctionsBetweenTwoDates(auctionDates.get(0), auctionDates.get(1));
			printAuctions(auctions);
			break;
		case 3: 
			ArrayList<Auction> auctions2 = Run.calendar.getAuctionsInChronologicalOrder();
			printAuctions(auctions2);
			break;
		case 4:
			Auction cancelAuction = chooseAuctionToCancel(theScanner);
			Run.calendar.cancelAuction(cancelAuction);
			break;
		}		
				
	}
	
	/**
	 * Returns a list of dates user chose view auctions between
	 * @param theScanner
	 * @return dates to check
	 */
	public ArrayList<LocalDate> getDatesFromUser(Scanner theScanner) {
		ArrayList<LocalDate> dates = new ArrayList<LocalDate>();
		System.out.println("Enter start date of interval: (mm/dd/yyyy)");
		String auctionDateString = theScanner.next();
		String[] auctionDateArr = auctionDateString.split("/");
		LocalDate theStartDate = LocalDate.of(Integer.parseInt(auctionDateArr[2]), 
										Integer.parseInt(auctionDateArr[0]),
									    Integer.parseInt(auctionDateArr[1]));
		dates.add(theStartDate);
		System.out.println("Enter end date of interval: (mm/dd/yyyy)");
		auctionDateString = theScanner.next();
		auctionDateArr = auctionDateString.split("/");
		LocalDate theEndDate = LocalDate.of(Integer.parseInt(auctionDateArr[2]), 
										Integer.parseInt(auctionDateArr[0]),
									    Integer.parseInt(auctionDateArr[1]));
		dates.add(theEndDate);
		return dates;
	}
	
	/**
	 * Returns the auction user has chosen to cancel.
	 * @param theScanner
	 * @return auction to be canceled
	 */
	public Auction chooseAuctionToCancel(Scanner theScanner) {
		ArrayList<Auction> upcomingAuctions = Run.calendar.getUpcomingAuctions();
		System.out.println("Select An Auction to Cancel.");
		int index = 1;
		for (Auction auction: upcomingAuctions) {
			System.out.println(index + ") Auction hosted by: " + auction.getOrganization().getName()
							   + ", " + auction.getDate());
			index++;
							
		}
		System.out.println("Enter your choice: ");
		int choice = theScanner.nextInt();
		
		while (choice < 1 || choice > upcomingAuctions.size()) {
			System.out.println("Choice invalid. Enter your choice: ");
			choice = theScanner.nextInt();
		}
		return upcomingAuctions.get(choice-1);	
	}
	
	/**
	 * Prints information about a list of auctions.
	 * @param theAuctions
	 */
	public void printAuctions(ArrayList<Auction> theAuctions) {
		for (Auction auction: theAuctions) {
			System.out.println("Auction hosted by: " + auction.getOrganization().getName());
			System.out.println("Auction date: " + auction.getDate());
		}
	}

}
