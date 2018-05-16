package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Calendar. Lets you submit an auction to the schedule, and validates the legality. 
 * @author prancingponies
 */
public class Calendar implements Serializable {

	private static final long serialVersionUID = -6465404834362396239L;
	HashMap<Organization, ArrayList<Auction>> auctionCentralAuctions;
	private int MAX_DAYS = 2;
	private int MAX_UPCOMING_AUCTIONS = 25;
	private int MAX_UPCOMING_AUCTIONS_DAYS = 60;
	private int MIN_UPCOMING_AUCTIONS_DAYS = 14;
	
	/**
	 * Construct a Calendar
	 * Only one calendar used at any time throughout program
	 */
	public Calendar(){
		auctionCentralAuctions = new HashMap<Organization, ArrayList<Auction>>();
	}
	
	
	/**
	 * Performs validation on Organization and date legality
	 * and then creates and Auction.
	 * Adds auction to Organization, as well as the Calendar. 
	 * @param theOrganization
	 */
	public void submitAuctionRequest(Organization theOrganization) {
		
		Scanner scanner = new Scanner(System.in);
		String[] auctionDateArr = new String[3];
		System.out.print("Enter start date of auction (mm/dd/yyyy): ");
		String auctionDateString = scanner.next();
		auctionDateArr = auctionDateString.split("/");
		System.out.println(auctionDateArr[2]);
		System.out.println(auctionDateArr[1]);
		System.out.println(auctionDateArr[0]);
		LocalDate auctionDate = LocalDate.of(Integer.parseInt(auctionDateArr[2]), 
										Integer.parseInt(auctionDateArr[0]),
									    Integer.parseInt(auctionDateArr[1]));
		// Make sure one year between any two auctions by same non-profit
		// No more than two auctinos occur on same day in entire system
		// No more than maximum number auctions in entire system
		// No auction can be scheduled after set number out
		// No auction can be scheduled before set number out
		if (checkDate(auctionDate) && checkForMaxDays(auctionDate) 
				&& checkForMaxDays(auctionDate) && checkForUpComingAuctionNumber() 
				&& checkBeenYearForOrg(theOrganization, auctionDate)) {
			System.out.println("\nEnter max number of items bidders can bid on (0 for default): ");
			int maxNumBidder = scanner.nextInt();
			// System.out.println("Enter the start time of your auction: ");
			System.out.println("\nEnter max number of items total allowed for sale (0 for default): ");
			int maxNumItems = scanner.nextInt();	
			//LocalTime time = LocalTime.of(1, 2, 0, 0);
			Auction auction = new Auction(auctionDate, LocalDate.now(),  maxNumBidder, maxNumItems, theOrganization);
			// Add Auction to that Organization's list of Auctions
			theOrganization.addAuctionToOrganization(auction);
			// Put Auction in Manager's complete Map of Auctions
			if (auctionCentralAuctions.containsKey(theOrganization)) {
				// Organization already exists, so update Arraylist with current auction
				auctionCentralAuctions.replace(theOrganization, theOrganization.getAuctions());
				System.out.println("Organization has been updated");
			} else {
				// Add Organization to Auction Map
				auctionCentralAuctions.put(theOrganization, theOrganization.getAuctions());
			}
			System.out.println("Your organization is currently eligible to host an auction.");
			System.out.println("Your auction has been scheduled.");
			theOrganization.setCurrentAuction(auction);
		} else {
			System.out.println("Your organization is ineligible to host an auction.");	
		}
	}
	
	
	/**
	 * Accepts a specific auction to verify.
	 * Performs validation on Organization and Auction.
	 * Adds auction to Organization, as well as the Calendar. 
	 * FOR USE WITH SERIALIZABLE. 
	 * @param theOrganization
	 * @param theAuction
	 */
	public void submitAuctionRequestWithAuction(Organization theOrganization, Auction theAuction) {
		// Make sure one year between any two auctions by same non-profit
		// No more than two auctinos occur on same day in entire system
		// No more than maximum number auctions in entire system
		// No auction can be scheduled after set number out
		// No auction can be scheduled before set number out
		if (checkDate(theAuction.getDate()) && checkForMaxDays(theAuction.getDate()) 
				&& checkForMaxDays(theAuction.getDate()) && checkForUpComingAuctionNumber() 
				&& checkBeenYearForOrg(theOrganization, theAuction.getDate())) {
			// Add Auction to that Organization's list of Auctions
			theOrganization.addAuctionToOrganization(theAuction);
			// Put Auction in Manager's complete Map of Auctions
			if (auctionCentralAuctions.containsKey(theOrganization)) {
				// Organization already exists, so update Arraylist with current auction
				auctionCentralAuctions.replace(theOrganization, theOrganization.getAuctions());
				System.out.println("Organization has been updated");
			} else {
				// Add Organization to Auction Map
				auctionCentralAuctions.put(theOrganization, theOrganization.getAuctions());
			}
			System.out.println("Your organization is currently eligible to host an auction.");
			System.out.println("Your auction has been scheduled.");
			theOrganization.setCurrentAuction(theAuction);
		} else {
			System.out.println("Your organization is ineligible to host an auction.");	
		}
	}
	

//	public void requestAuction(Auction theAuction) {
//		if (checkDate(theAuction.getStartDate()) && checkForUpcomingDays(theAuction) 
//			&& checkForUpComingAuctionNumber()) {
//		addAuction(theAuction);
//		}
//	}
//	
//	public void addAuction(Auction theAuction){
//			auctions.add(theAuction);
//	}
	
	
	/**
	 * Checks to make sure that no more than two auctions
	 * fall on the requested date.
	 * number of days.
	 * @param theDate
	 * @return true if less than two auctions on requested date
	 */
	public boolean checkDate(LocalDate theDate){
		int num = 0; 
		boolean dateAllowed = false;
		for (ArrayList<Auction> theAuctions : auctionCentralAuctions.values()){
			for (Auction auction: theAuctions) {
				if (auction.getDate().equals(theDate))
					num++;
			}
		}
		if (num <= MAX_DAYS) {
			dateAllowed = true;
		} else {
			System.out.println("We are at max capacity for that date. Please pick another.");
		}	
		
		return dateAllowed;
	}
	
	
	/**
	 *  Make sure a year has passed between auctions for individual organizations
	 * @param theOganization
	 * @param theDate
	 * @return true if its been over a year since their last auction
	 */
	public boolean checkBeenYearForOrg(Organization theOganization, LocalDate theDate) {
		boolean result = false;
		LocalDate latestAuctionDate = theOganization.getLatestAuctionDate();
		
		if (latestAuctionDate != null) {
			LocalDate oneYearOut = latestAuctionDate.plusYears(1);
			if (theDate.isAfter(oneYearOut)) {
				result = true;
			} else {
				System.out.println("It has not yet been a year since your last auction.");
			}
		} else { 	// hasnt been a last auction, so its true that it hasnt been a year
			result = true;
		}
	
		return result;
	}
	
	
	/**
	 * Checks to make sure the requested auction date is legal
	 * by checking that it falls before the maximum schedule-out 
	 * number of days.
	 * @param theDate
	 * @return true if auction date falls in valid date range
	 */
	public boolean checkForMaxDays(LocalDate theDate) {
		boolean result = false;
		
		if (theDate.isAfter(LocalDate.now().plusDays(MAX_UPCOMING_AUCTIONS_DAYS))) {
			System.out.println("The requested auction date is too far out to schedule.");
		} else if (theDate.isBefore(LocalDate.now().plusDays(MAX_UPCOMING_AUCTIONS_DAYS))) {
			result = true;
		}

		return result;
	}
	
	
	/**
	 * Checks to make sure the requested auction date is legal
	 * by checking that it falls after the minimum schedule-out 
	 * number of days.
	 * @param theDate
	 * @return true if auction date falls in valid date range
	 */
	public boolean checkForMinDays(LocalDate theDate) {
		boolean result = false;
		
		if (theDate.isAfter(LocalDate.now().plusDays(MIN_UPCOMING_AUCTIONS_DAYS))) {
			return true;
		}else if (theDate.isBefore(LocalDate.now().plusDays(MIN_UPCOMING_AUCTIONS_DAYS))) {
			System.out.println("The requested auction date is too soon to schedule.");
		}
		
		return result;	
	}
	
	
	/**
	 * Checks to make sure the requested auction is legal
	 * by making sure there are less than the maximum
	 * number of auctions on the calendar currently.
	 * @return true if less than max allowed number of auctions on calendar
	 */
	public boolean checkForUpComingAuctionNumber() {
		return getUpcomingAuctions().size() <= MAX_UPCOMING_AUCTIONS;
	}
	
	
	/**
	 * @return Auctions on the schedule after today.
	 */
	public ArrayList<Auction> getUpcomingAuctions(){
		ArrayList<Auction> futureAuctions = new ArrayList<Auction>();	
		for (ArrayList<Auction> auctions: auctionCentralAuctions.values()) {
			for (Auction auction: auctions) {
				if (auction.getDate().isAfter(LocalDate.now())) {
					futureAuctions.add(auction);
				}
			}
		}
		return(futureAuctions);
	}
	
	
	/**
	 * Add Auction directly to calendar.
	 * FOR TESTING PURPOSES ONLY. 
	 * @param theAution
	 * @param theOrganization
	 */
	public void addAuction(Auction theAuction, Organization theOrganization) {
		
		ArrayList<Auction> auctions = new ArrayList<Auction>();
		
		if (auctionCentralAuctions.containsKey(theOrganization)) {
			ArrayList<Auction> currAuctionsOrg = auctionCentralAuctions.get(theOrganization);
			currAuctionsOrg.add(theAuction);
			auctionCentralAuctions.put(theOrganization, currAuctionsOrg);
		} else {
			auctions.add(theAuction);
			auctionCentralAuctions.put(theOrganization, auctions);
		}
	}
}
