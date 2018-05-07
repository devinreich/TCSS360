package Model;
<<<<<<< HEAD

import java.util.ArrayList;

import java.io.Serializable;

import java.time.LocalDate;
import java.io.Serializable;




public class Calendar implements Serializable {
	private static final long serialVersionUID = 4438330929983107980L;
	private ArrayList<Auction> auctions = new ArrayList();
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
		if (checkDate(theDate) && checkForUpcomingDays(theAuction) 
			&& checkForUpComingAuctionNumber()) {
		addAuction(theAuction);
		}
	}
	
	private void addAuction(Auction theAuction){
			auctions.add(theAuction);
	}
	

	public ArrayList<Auction> getUpcomingAuctions(){
		ArrayList<Auction> futureAuctions = new ArrayList<>();
		for(Auction theAuction : auctions){
			if (theAuction.getStartDate().equals(LocalDate.now()) || 
					theAuction.getStartDate().isAfter(LocalDate.now())	){
				futureAuctions.add(theAuction);
			}
		}
		return(futureAuctions);
	}
	

	public boolean checkForUpComingAuctionNumber() {
		return auctions.size() <= 25;
	}
	public ArrayList getAllAuctions(){
		return (ArrayList) auctions.clone();
	}
}