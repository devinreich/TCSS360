package Model;

import java.util.ArrayList;

import java.io.Serializable;

import java.time.LocalDate;
import java.io.Serializable;




public class Calendar implements Serializable {
	private static final long serialVersionUID = 4438330929983107980L;
	private ArrayList<Auction> auctions;
	private int MAX_DAYS = 2;
	private int MAX_UPCOMING_AUCTIONS = 25;
	private int MAX_UPCOMING_AUCTIONS_DAYS = 60;

	private LocalDate currentDate;
	
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
		if (checkDate(theDate)) {
		addAuction(theAuction);
		}
	}
	
	private void addAuction(Auction theAuction){
		auctions.add(theAuction);
	}
	
	public boolean chechForUpcomingDays(Auction theAuction) {
		int theNextOne;
		boolean result = false;
		Auction thePioneer = auiction[0];
		for(int i = 0; i < auctions.size()-1; i++) {
			if(auctions.getStartDate().isBefore(thePioneer.getStartDate()))
				thePioneer = aicton[i];
		}
		if(thePioneer.year - theauction.year = 0) {
			if (thePioneer.month - theauction.month < 2 ){
				result = true;
			}
			else if (thePioneer.month - theauction.month == 2) {

				if(thePioneer.day - theauction.day){
					
					result = true;
				}
			}
		}
		return result;
	}

	public boolean checkForUpComingAuctionNumber() {
		return Auction.size() <= 25;
	}
	public ArrayList getAllAuctions(){
		return (ArrayList) auctions.clone();
	}
}
