package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class Organization implements Serializable {
	
	private static final long serialVersionUID = -729795345542752084L;
	private String name;
	private PhoneNumber phoneNumber;
	private String contactInfo;
	private User[] staff;
	private ArrayList<Auction> auctions;
	private Auction currentAuction;
	
	//These fields temporary for testing
	public static LocalDate DATE = LocalDate.now();
	public static Calendar calendar;
	
	public Organization(String theName, PhoneNumber thePhoneNumber, 
					   String theContactInfo, User[] theStaff) {
		this.auctions = new ArrayList<Auction>();
		this.name = theName;
		this.phoneNumber = thePhoneNumber;
		this.contactInfo = theContactInfo;
		this.staff = theStaff;
		this.currentAuction = null;
		
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
	
	public void addAuction(Auction theAuction) {
		if (theAuction != null)
			auctions.add(theAuction);
	}
	
	//Display details about current auction
	public void displayCurrentAuctionDetails() {
		if (hasActiveAuction()) {
			System.out.println("\nYour current auction is scheduled on " + currentAuction.getStartDate());
			System.out.println("The auction has " + currentAuction.getInventoryCount() + " items in the inventory.");
		} else {
			System.out.println("There are no current acutions.");
		}
	}
	
	//Cancel current auction
	public void cancelCurrentAuction() {
		
	}
	
	public Auction getCurrentAuction() {
		return currentAuction;
	}
	
	public void setCurrentAuction(Auction theAuction) {
		currentAuction = theAuction;
	}
}
