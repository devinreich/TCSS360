package Model;
import java.io.Serializable;
import java.time.LocalDate;

public class Calendar implements Serializable {
	private static final long serialVersionUID = 7055729347303320715L;
	private Auction[] auctions;
	private int MAX_DAYS = 2;
	private LocalDate currentDate;
	
	public Calendar(LocalDate theCurrentDate) {
		currentDate = theCurrentDate;
	}
	
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
