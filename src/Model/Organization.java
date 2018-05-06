package Model;

import java.util.Arrays;

public class Organization extends User{
	private String name;
	private PhoneNumber phoneNumber;
	private String contactInfo;
	private User[] staff;
	private Auction[] auctions;
	
	public Organization(String theName, PhoneNumber thePhoneNumber, 
					   String theContactInfo, User[] theStaff,
					   Auction[] theAuctions) {
		
		super(theName, thePhoneNumber, theContactInfo);
		staff = theStaff;
		auctions = theAuctions;
		
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
	
	public boolean hasActiveAuction() {
		return true;
	}
	
	public void displayCurrentAuctionDetails() {
		
	}
}
