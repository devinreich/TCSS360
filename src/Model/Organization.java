package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Organization Class. Constructs an Organization and stores the date of their 
 * latest auction, information about their auctions and a list of auctions they have held. 
 * @author prancingponies
 */
public class Organization implements Serializable {
	
	private static final long serialVersionUID = -729795345542752084L;
	private String organizationName;
	private ArrayList<Auction> organizationAuctions;
	private Auction currentAuction;

	
	public Organization(String theName) {
		this.organizationAuctions = new ArrayList<Auction>();
		this.organizationName = theName;
		//this.contactInfo = theContactInfo;
		this.currentAuction = null;	
	}
	
	
	public String getName() {
		return organizationName;
	}

	
//	public String getContactInfo() {
//		return contactInfo;
//	}
//	

	public ArrayList<Auction> getAuctions() {
		return organizationAuctions;
	}
	
	//Figure out how to determine if a organization has a current auction
	public boolean hasActiveAuction() {
		return currentAuction != null;
	}
	
	
	/**
	 * Adds the given auction to the Organization's list of auctions.
	 * Only called if all valid tests on Auction are passed.
	 * Called from AuctionCentralManager in AuctionRequest method. 
	 * @param theAuction
	 */
	public void addAuctionToOrganization(Auction theAuction) {
		if (theAuction != null)
			organizationAuctions.add(theAuction);
	}
	
	
	public void displayCurrentAuctionDetails() {
		if (hasActiveAuction()) {
			System.out.println("\nYour current auction is scheduled on " + currentAuction.getDate());
			System.out.println("The auction has " + currentAuction.getInventoryCount() + " items in the inventory.");
		} else {
			System.out.println("There are no current acutions.");
		}
	}
	
	
	/**
	 * Lets user cancel current auction.
	 */
	public void cancelCurrentAuction() {	
//		// Remove auction	
//		currentAuction = null;
	}
	
	
	/** 
	 * Precondition assumes currentAuction is not null.
	 * @return Returns current auction for this Organization if active
	 */
	public Auction getCurrentAuction() {
		return currentAuction;
	}
	
	
	/** 
	 * @return Returns latest auction from this Organization
	 */
	public LocalDate getLatestAuctionDate() {
		
		if (organizationAuctions != null && !organizationAuctions.isEmpty()) {
		Auction latestAuction = organizationAuctions.get(0);	
			Auction temp = organizationAuctions.get(0);
			
			for (Auction auction: organizationAuctions) {
				if (auction.getDate().isAfter(temp.getDate())) {
					latestAuction = auction;
				}
			}
			//TODO: REMOVE
			System.out.println("Latest auction date: " + latestAuction.getDate());
			
			return latestAuction.getDate();
		} else
			return null;		
	}
	
	
	/**
	 * @param theAuction to set as current auction for this org
	 */
	public void setCurrentAuction(Auction theAuction) {
		currentAuction = theAuction;
	}
	
	
	/**
	 * Checks to see if the Organization's current auction has passed
	 * so the menu options can be appropriately updated 
	 * to let them submit a new auction request. 
	 */
	public void updateCurrentAuction() {
		if (currentAuction.getDate().isBefore(LocalDate.now())) {
			currentAuction = null;
		}
	}
	
	public boolean equals(Object obj) {
		if (obj != null) {
			if (obj instanceof Organization) {
				Organization otherOrg = (Organization) obj;
				return this.organizationName.equals(otherOrg.organizationName);
			}
		}
		return false;
	}
}