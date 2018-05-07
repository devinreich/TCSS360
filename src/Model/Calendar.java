package Model;

import java.util.ArrayList;

import java.io.Serializable;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.io.Serializable;




public class Calendar implements Serializable {
	private static final long serialVersionUID = 4438330929983107980L;
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
	

	public void requestAuction(LocalDate theDate, Auction theAuction) {
		if (checkDate(theDate) && checkForUpcomingDays(theAuction) 
			&& checkForUpComingAuctionNumber()) {
		addAuction(theAuction);
		}
	}
	
	private void addAuction(Auction theAuction){
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
			if(auctions.get(i).getStartDate().isBefore(thePioneer.getStartDate()))
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
//				}
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
		return (ArrayList) auctions.clone();
	}
}
