package Model;
import java.time.LocalDate;

public class Calendar {
	private Auction[] auctions;
	
	public boolean checkDate(LocalDate theDate){
		int num = 0;
		for (Auction theAuction : auctions){
			if (theAuction.getStartDate().equals(theDate) || 
					theAuction.getEndDate().equals(theDate))
				num+=1;
		}
		if (num <=2)
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
