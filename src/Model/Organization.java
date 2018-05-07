package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Organization implements Serializable {
	
	private static final long serialVersionUID = 8973718656632450810L;
	private String name;
	private PhoneNumber phoneNumber;
	private String contactInfo;
	private User[] staff;
	private ArrayList<Auction> auctions;
	private Auction currentAuction;
	
	//These fields temporary for testing
	public static LocalDate DATE = LocalDate.now();
	public static Calendar CALENDAR = new Calendar(DATE);
	
	public Organization(String theName, PhoneNumber thePhoneNumber, 
					   String theContactInfo, User[] theStaff,
					   ArrayList<Auction> theAuctions) {
		
		staff = theStaff;
		auctions = theAuctions;
		currentAuction = null;
		
	}
	
	public String getName() {
		return name;
	}
	
	public String getPhoneNumber() {
		return phoneNumber.toString();
	}
	
	public String getContactInfo() {
		return contactInfo;
	}
	
	public User[] getStaff() {
		return Arrays.copyOf(staff, staff.length);
	}
	
	public ArrayList<Auction> getAuctions() {
		return auctions;
	}
	
	//Figure out how to determine if a organization has a current auction
	public boolean hasActiveAuction() {
		return currentAuction != null;
	}
	
	//Display details about current auction
	public void displayCurrentAuctionDetails() {
		System.out.println("Your current auction is scheduled on " + currentAuction.getStartDate());
		
	}
	
	//Cancel current auction
	public void cancelCurrentAuction() {
		
	}
	
	//Create new auction, collect details, and then check calendar
	public void submitAuctionRequest() {
		
		//For testing
		User[] users = new User[1];
		ArrayList<Auction> auctions = new ArrayList<>();
		PhoneNumber organizationNumber = new PhoneNumber(253, 222, 4516);
		Organization testOrganization = new Organization("Goodwill", 
														organizationNumber,
														"Contact Robert Smith", 
														users, auctions);
		//
		
		
		Scanner scanner = new Scanner(System.in);
		Auction potentialAuction;
		String[] startDate = new String[3];
		System.out.print("Enter start date of auction (mm/dd/yyyy): ");
		String startDateString = scanner.next();
		startDate = startDateString.split("/");
		LocalDate start = LocalDate.of(Integer.parseInt(startDate[2]), 
									    Integer.parseInt(startDate[1]), 
									    Integer.parseInt(startDate[0]));
		System.out.print("\nEnter end date of auction (mm/dd/yyyy): ");
		String[] endDate = new String[3];
		String endDateString = scanner.next();
		endDate = endDateString.split("/");
		LocalDate end = LocalDate.of(Integer.parseInt(endDate[2]), 
			    						   Integer.parseInt(endDate[1]), 
			    						   Integer.parseInt(endDate[0]));
		
		
		if (CALENDAR.checkDate(start) && CALENDAR.checkDate(end)) {
			System.out.println("Enter max number of items per bidder: ");
			int maxnum = scanner.nextInt();
			System.out.println("Enter the start time of your auction: ");
			
			LocalTime time = LocalTime.of(1, 2, 0, 0);
			LocalDate date = LocalDate.now();
			
			Auction auction = new Auction(start, end, date, time,  maxnum, testOrganization);
			auctions.add(auction);
			System.out.println("Your organization is currently eligible"
							   + " to host an auction.");
			currentAuction = auction;
	
		} else {
			System.out.println("Your organization is ineligible"
							   + " to host an auction.");	
		}
		
		
		
		
	}
		
	
	
	public Auction getCurrentAuction() {
		return currentAuction;
	}
}
