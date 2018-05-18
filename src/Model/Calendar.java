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
	private static final Integer MAX_DAYS = 2;
	private static final Integer MAX_UPCOMING_AUCTIONS = 25;
	private static final Integer MAX_UPCOMING_AUCTIONS_DAYS = 60;
	private static final Integer MIN_UPCOMING_AUCTIONS_DAYS = 14;
	private Integer maximumUpcomingAuctions;
	private Integer maximumUpcomingDays;
	private Integer minimumUpcomingDays;
	LocalDate entryPoint;
	
	/**
	 * Construct a Calendar
	 * Only one calendar used at any time throughout program
	 */
	public Calendar(){
		auctionCentralAuctions = new HashMap<Organization, ArrayList<Auction>>();
		maximumUpcomingAuctions = MAX_UPCOMING_AUCTIONS;
		maximumUpcomingDays = MAX_UPCOMING_AUCTIONS_DAYS;
		minimumUpcomingDays = MIN_UPCOMING_AUCTIONS_DAYS;
	}
	
	/**
	 * Set maximum number of allowed active upcoming auctions
	 * @param theInt
	 */
	public void setMaximumUpcomingAuctions(Integer theInt) {
		maximumUpcomingAuctions = theInt;
	}
	
	/**
	 * Set maximum number of allowed days out
	 * @param theInt
	 */
	public void setMaximumUpcomingDays(Integer theInt) {
		maximumUpcomingDays = theInt;
	}
	
	
	/**
	 * Set maximum number of allowed days out
	 * @param theInt
	 */
	public void setMinimumUpcomingDays(Integer theInt) {
		minimumUpcomingDays = theInt;
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
		LocalDate auctionDate = LocalDate.of(Integer.parseInt(auctionDateArr[2]), 
										Integer.parseInt(auctionDateArr[0]),
									    Integer.parseInt(auctionDateArr[1]));
		// Make sure one year between any two auctions by same non-profit
		// No more than two auctinos occur on same day in entire system
		// No more than maximum number auctions in entire system
		// No auction can be scheduled after set number out
		// No auction can be scheduled before set number out
		if (checkDate(auctionDate) && checkForMaxDays(auctionDate) 
				&& checkForMinDays(auctionDate) && checkForUpComingAuctionNumber() 
				&& checkBeenYearForOrg(theOrganization, auctionDate)) {
			System.out.println("\nEnter max number of items bidders can bid on (0 for default): ");
			int maxNumBidder = scanner.nextInt();
			// System.out.println("Enter the start time of your auction: ");
			System.out.println("\nEnter max number of items total allowed for sale (0 for default): ");
			int maxNumItems = scanner.nextInt();	
			//LocalTime time = LocalTime.of(1, 2, 0, 0);
			Auction auction = new Auction(auctionDate, getCurrentDate(),  maxNumBidder, maxNumItems, theOrganization);
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
				&& checkForMinDays(theAuction.getDate()) && checkForUpComingAuctionNumber() 
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
	

	public void requestAuction(Auction theAuction, Organization forOrganization) {
		if (checkDate(theAuction.getCreateDate()) && checkForUpcomingDays(theAuction) 
			&& checkForUpComingAuctionNumber()) {
			addAuction(theAuction, forOrganization);
		}
	}
	
	public boolean checkForUpcomingDays(Auction theAuction) {
		return true;
	}
	
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
		//System.out.println("Number of auctions on " + theDate + " is; " + num);
		if (num < MAX_DAYS) {
			dateAllowed = true;
		} else {
			System.out.println("We are at max capacity for that date. Please pick another.");
		}	
		//System.out.println("Calendar not at max capacity for specified date: " + dateAllowed);
		return dateAllowed;
	}
	
	
	/**
	 *  Make sure a year has passed between auctions for individual organizations
	 * @param theOganization
	 * @param theDate
	 * @return true if its been over a year since their last auction
	 */
	public boolean checkBeenYearForOrg(Organization theOrganization, LocalDate theDate) {
		boolean result = false;
		LocalDate latestAuctionDate = theOrganization.getLatestAuctionDate();
		if (latestAuctionDate != null) {
			LocalDate oneYearOut = latestAuctionDate.plusYears(1);
			if (theDate.isAfter(oneYearOut) || theDate.isEqual(oneYearOut)) {
				
				result = true;
			} else {
				System.out.println("It has not yet been a year since your last auction.");
			}
		} else { 	// hasnt been a last auction, so its true that it hasnt been a year
			result = true;
		}
		//System.out.println("It has been at least a year: " + result);
		//System.out.println("Requesed date: " + theDate + " current date: " + getCurrentDate() + " last auction date: " + theOrganization.getLatestAuctionDate());
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
		
		if (theDate.isAfter(getCurrentDate().plusDays(maximumUpcomingDays)) || 
				theDate.isEqual(getCurrentDate().plusDays(maximumUpcomingDays))) {
			System.out.println("The requested auction date is too far out to schedule.");
		} else if (theDate.isBefore(getCurrentDate().plusDays(maximumUpcomingDays)) || 
				theDate.isEqual(getCurrentDate().plusDays(maximumUpcomingDays))) {
			result = true;
		}
		//System.out.println("Request is before max days out: " + result);
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
		if (theDate.isAfter(getCurrentDate().plusDays(minimumUpcomingDays)) || 
				theDate.isEqual(getCurrentDate().plusDays(minimumUpcomingDays))) {
			return true;
		}else if (theDate.isBefore(getCurrentDate().plusDays(minimumUpcomingDays)) || 
				theDate.isEqual(getCurrentDate().plusDays(minimumUpcomingDays))) {
			System.out.println("The requested auction date is too soon to schedule.");
		}
		//System.out.println("Request is after min days out: " + result);
		//System.out.println("Request date: " + theDate + " current date: " + getCurrentDate());
		return result;	
	}
	
	
	/**
	 * Checks to make sure the requested auction is legal
	 * by making sure there are less than the maximum
	 * number of auctions on the calendar currently.
	 * @return true if less than max allowed number of auctions on calendar
	 */
	public boolean checkForUpComingAuctionNumber() {
		//System.out.println("Capacity:  " + maximumUpcomingAuctions + "upcoming auctions: " + getUpcomingAuctions().size());
		//System.out.println("Calendar less tham max capacity: " + (getUpcomingAuctions().size() < maximumUpcomingAuctions));
		return getUpcomingAuctions().size() < maximumUpcomingAuctions;
	}
	
	
	/**
	 * @return Auctions on the schedule after today.
	 */
	public ArrayList<Auction> getUpcomingAuctions(){
		ArrayList<Auction> futureAuctions = new ArrayList<Auction>();	
		for (ArrayList<Auction> auctions: auctionCentralAuctions.values()) {
			for (Auction auction: auctions) {
				if (auction.getDate().isAfter(getCurrentDate())) {
					futureAuctions.add(auction);
				}
			}
		}
		return(futureAuctions);
	}
	

	public void changeMaximumUpcomingDays(){
		Scanner scanner2 = new Scanner(System.in);
		
		System.out.print("Enter the new number of days in the future that auctions may be stored: ");
		int potentialDays = scanner2.nextInt();
		while (potentialDays < 0){
			System.out.print("The number of days entered must be positive, enter a new number of days: ");
			potentialDays = scanner2.nextInt();
		}
		setMaximumUpcomingDays(potentialDays);
	}
	
	
	/**
	 * Set current date.
	 * FOR TESTING PURPOSES ONLY
	 */
	public void setCurrentDate(LocalDate theDate){
		entryPoint = theDate;
	}
	
	
	/**
	 * Get current date.
	 */
	public LocalDate getCurrentDate(){
		
		if (entryPoint != null) {
			return entryPoint;
		} else
			return LocalDate.now();
	}
	
	
	 /**
	  * @return Maximum number of auctions allowed on a single day.
	  */
	 public Integer getMaxAuctionsOnSingleDay() {
		 return new Integer(MAX_DAYS);
	 }
	 
	 
	 /**
	  * @return Maximum number of allowed upcoming auctions
	  */
	 public Integer getMaximumUpcomingAuctions() {
		 return maximumUpcomingAuctions;
	 }
	 
	 
	 /**
	  * @return Maximum number of days cant schedule past
	  */
	 public Integer getMaximumUpcomingDays() {
		 return maximumUpcomingDays;
	 }
	 
	 
	 /**
	  * @return Maximum number of days cant schedule before
	  */
	 public Integer getMinimumUpcomingDays() {
		 return minimumUpcomingDays;
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
	 
		
	/**
	* Wipe schedule
	* TESTING PURPOSES ONLY
	*/
	public void wipeSchedule() {
		auctionCentralAuctions = new HashMap<Organization, ArrayList<Auction>>();
	}
	 
	 
	/**
	* Add directly to schedule
	* TESTING PURPOSES ONLY
	*/
	public void addToSchedule(Organization theOrganization, ArrayList<Auction> theAuctions) {	
		auctionCentralAuctions.put(theOrganization, theAuctions);	
	}
}
