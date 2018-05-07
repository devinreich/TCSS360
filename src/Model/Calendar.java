package Model;
<<<<<<< HEAD
import java.util.ArrayList;
=======
import java.io.Serializable;
>>>>>>> 31a8e073fe97aab63950523703274b55b2bd1d93
import java.time.LocalDate;
import java.io.Serializable;

<<<<<<< HEAD


public class Calendar implements Serializable {
	private static final long serialVersionUID = 4438330929983107980L;
	private ArrayList<Auction> auctions;
	private int MAX_DAYS = 2;

=======
public class Calendar implements Serializable {
	private static final long serialVersionUID = 7055729347303320715L;
	private Auction[] auctions;
	private int MAX_DAYS = 2;
>>>>>>> 31a8e073fe97aab63950523703274b55b2bd1d93
	private LocalDate currentDate;
	
	public Calendar(LocalDate theCurrentDate) {
		currentDate = theCurrentDate;
	}
<<<<<<< HEAD


=======
>>>>>>> 31a8e073fe97aab63950523703274b55b2bd1d93
	
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
	
	public ArrayList getAllAuctions(){
		return (ArrayList) auctions.clone();
	}
}
