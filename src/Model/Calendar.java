package Model;
import java.time.LocalDate;

public class Calendar {
	private Auction[] auctions;
	private LocalDate currentDate;
	
	public Calendar(LocalDate theCurrentDate) {
		currentDate = theCurrentDate;
	}
=======
	private int MAX_DAYS = 2;
>>>>>>> 6cf636d16bfbe9ffa2c57c7facc79118c164af0c
	
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
	
	public void requestAuction(LocalDate theDate) {
		if (checkDate(theDate)) {
		//	makeNewAuction()
		}
	}
	public void addAuction(Auction theAuction){
		//method to add an auction into the calendar called by requestAuction.
	}
}
