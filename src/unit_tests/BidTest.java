package unit_tests;

import static org.junit.Assert.*;
import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import Model.Auction;
import Model.Bid;
import Model.Bidder;
import Model.Item;
import Model.Organization;

// Tests the bid 
public class BidTest {

	private Bidder bidder;
	private Item item;
	private Auction auction;
	
	private Item item2;
	
	@Before
	public void setup() {
		
	
		item2 =  new Item("Toothbrush", "Trump's toothbrush", new Double(3000.0), LocalDate.now());
	
		bidder = new Bidder("John", "johnLoginName");
		item = new Item("Toothbrush", "Trump's toothbrush", 3000.0, LocalDate.now());
		auction = new Auction(LocalDate.now().plusMonths(1), LocalDate.now(), 2, 2, new Organization("The White House"));
		auction.addItem(item);
	}
	

	@Test
	public void bid_bidBelowMaximunNumberOfBids_true() {
		auction.placeBid(new Double(3000), LocalDate.now(), bidder, auction.getInventory().get(0));
		ArrayList<Bid> bids = bidder.getAllBids();
		assertTrue(bids.size() > 0);
		assertEquals(bids.get(bids.size() - 1).getBidAmount().doubleValue(), 3000.0d);
	}
	
	@Test(expected = RuntimeException.class)
	public void bid_bidEqualMaximunNumberOfBids_false() {
		auction.placeBid(new Double(3000), LocalDate.now(), bidder, auction.getInventory().get(0));
	}
}
