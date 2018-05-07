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
import Model.PhoneNumber;

// Tests the bid 
public class BidTest {

	private Bidder bidder;
	private Item item;
	private Auction auction;
	
	private Item item2 = new Item("Toothbrush", "Trump's toothbrush", 3000.0, LocalDate.now());
	
	@Before
	public void setup() {
		bidder = new Bidder("John", "johnLoginName", new PhoneNumber(360, 555, 5555), "contact info");
		item = new Item("Toothbrush", "Trump's toothbrush", 3000.0, LocalDate.now());
		auction = new Auction();
		auction.addItem(item);
	}
	
	@Test
	public void bid_bidBelowMaximunNumberOfBids_true() {
		bidder.bid(3000, auction.getInvetory().get(0));
		ArrayList<Bid> bids = bidder.getBids();
		assertTrue(bids.size() > 0);
		assertEquals(bids.get(bids.size() - 1).getBidAmount().doubleValue(), 3000.0d);
	}
	
	@Test(expected = RuntimeException.class)
	public void bid_bidEqualMaximunNumberOfBids_false() {
		bidder.bid(3000, auction.getInvetory().get(0));
	}

}
