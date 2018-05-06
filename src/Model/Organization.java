package Model;

import java.util.Arrays;

public class Organization {
	private String name;
	private PhoneNumber phoneNumber;
	private String contactInfo;
	private User[] staff;
	private Auction[] auctions;
	private Auction currentAuction;
	
	public Organization(String theName, PhoneNumber thePhoneNumber, 
					   String theContactInfo, User[] theStaff,
					   Auction[] theAuctions) {
		
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
	
	public Auction[] getAuctions() {
		return Arrays.copyOf(auctions, auctions.length);
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
}
