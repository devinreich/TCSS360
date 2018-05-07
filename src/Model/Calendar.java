package Model;
import java.time.LocalDate;

public class Calendar {
	private Auction[] auctions;
<<<<<<< HEAD
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
	private int MAX_DAYS = 2;
>>>>>>> 69b0a646b4d91da43893fdc92b8627c3be1b66a4
	private LocalDate currentDate;
	
	public Calendar(LocalDate theCurrentDate) {
		currentDate = theCurrentDate;
	}
<<<<<<< HEAD
=======
	private int MAX_DAYS = 2;
>>>>>>> 6cf636d16bfbe9ffa2c57c7facc79118c164af0c
>>>>>>> d496bafb3769c4563980f5bb00984ba05d878699
=======
>>>>>>> 69b0a646b4d91da43893fdc92b8627c3be1b66a4
	
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
