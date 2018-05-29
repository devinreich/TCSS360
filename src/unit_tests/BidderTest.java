package unit_tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Controller.Run;
import Controller.Serializer;
import Model.Auction;
import Model.Bidder;
import Model.Calendar;
import Model.Item;
import Model.Organization;
import java.time.LocalDate;

/**
 * Tests the validation logic in the Bidder class. 
 * @author nadiapolk
 */
public class BidderTest {

	private LocalDate testAuctionDatePresent;
	private LocalDate testAuctionDateFuture;
	private Item testItem;
	private Item testItem2;
	private Item testItem3;
	private Bidder testBidder1;
	private Auction testAuctionPresent;
	private Auction testAuctionFuture;
	private Integer testMaxItemsPerBidder;
	private Integer testMaxItemsSold;
	private Organization testOrganization;
	private Calendar calendar;
	

	@BeforeEach
	void setUp() {
		calendar = (Calendar) Serializer.deserialize("calendar");
		testAuctionDatePresent = LocalDate.now();
		testAuctionDateFuture = testAuctionDatePresent.plusMonths(1);
		
		/* Test Items*/
		testItem = new Item("Antique Magic 8 Ball", "Guaruntees your future", 1000.00, LocalDate.now());
		testItem2 = new Item("Toothbrush", "Trump's toothbrush", 3000.0, LocalDate.now());
		testItem3 = new Item("Cat", "A new cat!", 250.00, LocalDate.now());
		
		testMaxItemsPerBidder = 3;
		testMaxItemsSold = 10;
		
		/* Test organizations */
		testOrganization = new Organization("Goodwill");
			
		testAuctionPresent = new Auction(testAuctionDatePresent, LocalDate.now(), testMaxItemsPerBidder, 
				testMaxItemsSold, testOrganization);		
		testAuctionFuture = new Auction(testAuctionDateFuture, LocalDate.now(), testMaxItemsPerBidder, 
				testMaxItemsSold, testOrganization);		

		/* Add Auction to schedule */
		calendar.submitAuctionRequestWithAuction(testOrganization, testAuctionFuture);

		/* Set maximum amount legal active bids for bidder to 3 */
		testBidder1 = new Bidder("Nadia Polk", "polkn");
		testBidder1.setMaxAllowedBids(3);
		
		/* Add items to legal auction */
		testAuctionFuture.addItem(testItem);
		testAuctionFuture.addItem(testItem2);
		testAuctionFuture.addItem(testItem3);
	}

	/**
	 * Tests that bidder can place a bid above the minimum
	 * accepted base price for the item.
	 */
	@Test
	public void isBidAmountLegal_BidAmountAboveMinimum_true() {
		System.out.println(testItem.getBasePrice() + "amount: " + testItem.getBasePrice() *2);
		assertTrue(testBidder1.isBidAmountLegal(testItem.getBasePrice() *2, testItem));
	}
	
	/**
	 * Tests that bidder can place a bid equal to the minimum
	 * accepted base price for the item.
	 */
	@Test
	public void isBidAmountLegal_BidAmountEqualMinimum_true() {
		assertTrue(testBidder1.isBidAmountLegal(testItem.getBasePrice(), testItem));
	}
	
	/**
	 * Tests that bidder cannot place a bid below the minimum
	 * accepted base price for the item.
	 */
	@Test
	public void isBidAmountLegal_BidAmountUnderMinimum_false() {	
		assertFalse(testBidder1.isBidAmountLegal(testItem.getBasePrice()/2, testItem));
	}
		
	/**
	 * Tests that bidder can place a bid the day before the auction.
	 */
	@Test
	public void isBidDateLegal_BidPlacedDayBefore_true() {
		assertTrue(testBidder1.isBidDateLegal(testAuctionPresent.getDate().minusDays(1), testAuctionPresent));
	}
	
	/**
	 * Tests that bidder cannot place a bid the day of the auction.
	 */
	@Test 
	public void isBidDateLegal_BidPlacedDayOf_false() {	
		assertFalse(testBidder1.isBidDateLegal(testAuctionPresent.getDate(), testAuctionPresent));
	}
	
	/**
	 * Tests that bidder cannot place a bid the day after the auction.
	 */
	@Test
	public void isBidDateLegal_BidPlacedDayAfter_false() {
		assertFalse(testBidder1.isBidDateLegal(testAuctionPresent.getDate().plusDays(1), testAuctionPresent));
	}
		
	/**
	 * Tests that bidder can place a bid on a specific auction while
	 * they are under the maximum allowed number of items bidders
	 * can bid on for that auction.
	 */
	@Test
	public void isBidderUnderMaxBidsForAuction_OneLessThanMaxBids_true() {
		testAuctionFuture.placeBid(testItem.getBasePrice()*1.5, LocalDate.now(), testBidder1, testItem);
		assertTrue(testBidder1.isAuctionLegalForBidder(testAuctionFuture));
	}
	
	/**
	 * Tests that bidder cannot place a bid on a specific auction if they have
	 * bid on the maximum number of allowed items for that auction.
	 */
	@Test
	public void isBidderUnderMaxBidsForAuction_AtMaxBidsForAuction_false() {
		// Maximum allowed items for bidders on this auction is set to 2
		testAuctionFuture.placeBid(testItem.getBasePrice()*1.5, LocalDate.now(), testBidder1, testItem);
		testAuctionFuture.placeBid(testItem2.getBasePrice()*1.5, LocalDate.now(), testBidder1, testItem2);
		testAuctionFuture.placeBid(testItem3.getBasePrice()*1.5, LocalDate.now(), testBidder1, testItem3);
		assertFalse(testBidder1.isAuctionLegalForBidder(testAuctionFuture));
	}
	
	/**
	 * Tests that bidder can bid on any auction while they are under the
	 * maximum allowed active bids. 
	 */
	@Test
	public void isBidderUnderMaxBidsForAllAuctions_OneLessThanMaxBids_true() {
		testAuctionFuture.placeBid(testItem.getBasePrice()*1.5, LocalDate.now(), testBidder1, testItem);
		testAuctionFuture.placeBid(testItem2.getBasePrice()*1.5, LocalDate.now(), testBidder1, testItem2);
		assertTrue(testBidder1.isBidderLegal());
	}
	
	/**
	 * Tests that bidder cannot place a bid on any auction while they are
	 * at maximum allowed active bids.
	 */
	@Test 
	public void isBidderUnderMaxBidsForAllAuctions_MaxBidsForAllAuctions_false() {
		// Maximum allowed active bids is set to 3
		testAuctionFuture.placeBid(testItem.getBasePrice()*1.5, LocalDate.now(), testBidder1, testItem);
		testAuctionFuture.placeBid(testItem2.getBasePrice()*1.5, LocalDate.now(), testBidder1, testItem2);
		testAuctionFuture.placeBid(testItem3.getBasePrice()*1.5, LocalDate.now(), testBidder1, testItem3);
		assertFalse(testBidder1.isBidderLegal());		
	}	
}