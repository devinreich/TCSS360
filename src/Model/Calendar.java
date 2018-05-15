package Model;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.Serializable;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.time.LocalTime;




public class Calendar implements Serializable {

	private static final long serialVersionUID = -6465404834362396239L;
	private ArrayList<Auction> auctions;
	private int MAX_DAYS = 2;
	private int MAX_UPCOMING_AUCTIONS = 25;
	private int MAX_UPCOMING_AUCTIONS_DAYS = 60;
	private int MIN_UPCOMING_AUCTIONS_DAYS = 14;

	private LocalDate currentDate;
	
	public Calendar(){
		auctions = new ArrayList<Auction>();
	}
	
	public Calendar(LocalDate theCurrentDate) {
		currentDate = theCurrentDate;
	}

	
	public boolean checkDate(LocalDate theDate){
		int num = 0; 
		for (Auction theAuction : auctions){
			if (theAuction.getStartDate().equals(theDate) || 
					theAuction.getEndDate().equals(theDate))
				num++;
		}
		if (num <= MAX_DAYS)
			return true;
		return false;
	}
	
	//Create new auction, collect details, and then check calendar
		public void submitAuctionRequest(Organization theOrganization) {
			
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
			
			
			if (this.checkDate(start) && this.checkDate(end)) {
				System.out.println("\nEnter max number of items per bidder: ");
				int maxnum = scanner.nextInt();
		//		System.out.println("Enter the start time of your auction: ");
				
				LocalTime time = LocalTime.of(1, 2, 0, 0);
				LocalDate date = LocalDate.now();

				Auction auction = new Auction(start, end, date, time,  maxnum, theOrganization);

				theOrganization.addAuction(auction);
				this.auctions.add(auction);
				System.out.println("Your organization is currently eligible"
								   + " to host an auction.");
				System.out.println("Your auction has been scheduled.");
				theOrganization.setCurrentAuction(auction);
				
		
			} else {
				System.out.println("Your organization is ineligible"
								   + " to host an auction.");	
			}
					
		}
	

//	public void requestAuction(Auction theAuction) {
//		if (checkDate(theAuction.getStartDate()) && checkForUpcomingDays(theAuction) 
//			&& checkForUpComingAuctionNumber()) {
//		addAuction(theAuction);
//		}
//	}
	
	public void addAuction(Auction theAuction){
			auctions.add(theAuction);
	}
	

	public ArrayList getUpcomingAuctions(){
		ArrayList<Auction> futureAuctions = new ArrayList();
		for(Auction theAuction : auctions){
			if (theAuction.getStartDate().equals(LocalDate.now()) || 
					theAuction.getStartDate().isAfter(LocalDate.now())	){
				futureAuctions.add(theAuction);
			}
		}
		return(futureAuctions);
	}
	
	public boolean checkForUpcomingDays(Auction theAuction) {
		boolean result = false;
		

			Auction thePioneer = auctions.get(0);
			for(int i = 0; i < auctions.size()-1; i++) {
				if(auctions.get(i).getStartDate().isBefore(thePioneer.getStartDate()) 
					&& auctions.get(i).getStartDate().equals(LocalDate.now()))
					thePioneer = auctions.get(i);
			}
//		if(thePioneer.getStartDate().getYear() - theAuction.getStartDate().getYear() == 0) {
//			if (thePioneer.getStartDate().getMonthValue() - theAuction.getStartDate().getMonthValue() < 2 ){
//				result = true;
//			}
//			else if (thePioneer.getStartDate().getMonthValue() - theAuction.getStartDate().getMonthValue() == 2) {
					long DaysBetween = ChronoUnit.DAYS.between(thePioneer.getStartDate(), theAuction.getStartDate());
					if(DaysBetween <= MAX_UPCOMING_AUCTIONS_DAYS)
						result = true;
//					}
				
//			}
//		}
		return result;
	}
	
	public boolean checkForMinDays(Auction theAuction) {
		boolean result = false;
		long DaysBetween = ChronoUnit.DAYS.between(LocalDate.now(), theAuction.getStartDate());
		if(DaysBetween <= MIN_UPCOMING_AUCTIONS_DAYS)
			result = true;
		return result;
		
	}
	
	public boolean checkForUpComingAuctionNumber() {
		return auctions.size() <= MAX_UPCOMING_AUCTIONS;
	}
	
	public ArrayList getAllAuctions(){
		return auctions;
	}
	
	public void changeMaximumUpcomingDays(){
		Scanner scanner2 = new Scanner(System.in);
		
		System.out.print("Enter the new number of days in the future that auctions may be stored: ");
		int potentialDays = scanner2.nextInt();
		while (potentialDays < 0){
			System.out.print("The number of days entered must be positive, enter a new number of days: ");
			potentialDays = scanner2.nextInt();
		}
		MAX_UPCOMING_AUCTIONS_DAYS = potentialDays;
	}
	
	
	

}
