package unit_tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Model.Auction;
import Model.Bid;
import Model.Bidder;
import Model.Item;
import Model.Organization;
import Model.PhoneNumber;
import Model.User;
import Model.Calendar;
import java.time.LocalDate;
import java.time.LocalTime;

public class BidderTest {

	private LocalDate testAuctionStartDatePast;
	private LocalDate testAuctionStartDatePresent;
	private LocalDate testAuctionStartDateFuture;
	private LocalDate testAuctionEndDatePast;
	private LocalDate testAuctionEndDatePresent;
	private LocalDate testAuctionEndDateFuture;
	private LocalDate testAuctionCreationDate;
	private LocalDate testBidTimeDayBefore;
	private LocalDate testBidTimeDayOf;
	private LocalDate testBidTimeDayAfter;	
	private LocalTime testStartTime;
	private LocalTime testCurrentTime;
	private Item testItem;
	private Item testItem2;
	private Item testItem3;
	private Bid testBidDayBefore;
	private Bid testBidDayOf;
	private Bid testBidDayAfter;
	private Bid testBidAmountUnderMin;
	private Bid testBidAmountEqualsMin;
	private Bid testBidAmountAboveMin;
	private Bid testBidAmountAboveMin2;
	private Double testBidLessThanBasePrice;
	private Double testBidEqualToBasePrice;
	private Double testBidGreaterThanBasePrice;
	private Bidder testBidder1;
	private Bidder testBidder2;
	private Bidder testBidder3;
	private Auction testAuctionPast;
	private Auction testAuctionPresent;
	private Auction testAuctionFuture;
	private Integer testMaxItemsPerBidder;
	private PhoneNumber organizationNumber;
	private Organization testOrganization;
	private Organization testOrganization2;
	private Organization testOrganization3;
	private Calendar calendar;
	

	@BeforeEach
	void setUp() {
		testAuctionStartDatePresent = LocalDate.now();
		testAuctionStartDatePast = testAuctionStartDatePresent.minusMonths(2);
		testAuctionStartDateFuture = testAuctionStartDatePresent.plusMonths(2);
		
		testAuctionEndDatePast = testAuctionStartDatePast.plusDays(1);
		testAuctionEndDatePresent = testAuctionStartDatePresent.plusDays(1);
		testAuctionEndDateFuture= testAuctionStartDateFuture.plusDays(1);
		
		testAuctionCreationDate = LocalDate.now();
		
		testItem = new Item("Antique Magic 8 Ball", "Guaruntees your future", 1000.00, LocalDate.now());
		testItem2 = new Item("Toothbrush", "Trump's toothbrush", 3000.0, LocalDate.now());
		testItem3 = new Item("Cat", "A new cat!", 250.00, LocalDate.now());
		
		testMaxItemsPerBidder = 2;
		organizationNumber = new PhoneNumber(253, 222, 4516);
		User[] users = new User[1];

		calendar = new Calendar();
		testOrganization = new Organization("Goodwill", organizationNumber, "Contact Robert Smith", users);
		testOrganization2 = new Organization("SpaceX", organizationNumber, "Astronaut", users);
		testOrganization3 = new Organization("The Moon", organizationNumber, "Moon man", users);
		
		testAuctionPast = new Auction(testAuctionStartDatePast, testAuctionEndDatePast,
				testAuctionCreationDate, testStartTime, testMaxItemsPerBidder, testOrganization);	
		testAuctionPresent = new Auction(testAuctionStartDatePresent, testAuctionEndDatePresent,
				testAuctionCreationDate, testStartTime, testMaxItemsPerBidder, testOrganization2);	
		testAuctionFuture = new Auction(testAuctionStartDateFuture, testAuctionEndDateFuture,
				testAuctionCreationDate, testStartTime, testMaxItemsPerBidder, testOrganization3);	

		// bid amounts
		testBidLessThanBasePrice = new Double(testItem.getBasePrice()/2); 
		testBidEqualToBasePrice = new Double(testItem.getBasePrice()); 
		testBidGreaterThanBasePrice =new Double(testItem.getBasePrice() * 2);		
		// bid times (present day auction)
		testBidTimeDayBefore = testAuctionStartDatePresent.minusDays(1);
		testBidTimeDayOf = testAuctionStartDatePresent;
		testBidTimeDayAfter = testAuctionStartDatePresent.plusDays(1);
		// bidder
		testBidder1 = new Bidder("Nadia Polk", "polkn", new PhoneNumber(512, 569, 7725), "polkn@uw.edu");
		testBidder2 = new Bidder("Travis Polk", "polkt", new PhoneNumber(512, 569, 7725), "travis email");
		testBidder3 = new Bidder("Mulan Polk", "mulancat", new PhoneNumber(512, 569, 7725), "cat email");
		
		/*  Create Bids with variable bid dates - use legal bid amount */
		testBidDayBefore = new Bid(testBidGreaterThanBasePrice, testBidTimeDayBefore, testItem, testAuctionPresent, testBidder1);
		testBidDayOf = new Bid(testBidGreaterThanBasePrice, testBidTimeDayOf, testItem, testAuctionPresent, testBidder1);
		testBidDayAfter = new Bid(testBidGreaterThanBasePrice, testBidTimeDayAfter, testItem, testAuctionPresent, testBidder1);
		/* Create Bids with variable bid amounts- use legal date */
		testBidAmountUnderMin = new Bid(testBidLessThanBasePrice, testBidTimeDayBefore, testItem, testAuctionPresent, testBidder1);
		testBidAmountEqualsMin = new Bid(testBidEqualToBasePrice, testBidTimeDayBefore, testItem, testAuctionPresent, testBidder1);
		testBidAmountAboveMin2 = new Bid(testBidGreaterThanBasePrice, testBidTimeDayBefore, testItem, testAuctionPresent, testBidder1);
		testBidAmountAboveMin = new Bid(testBidGreaterThanBasePrice, testBidTimeDayBefore, testItem, testAuctionPresent, testBidder1);	
	}

	@Test
	public void isBidAmountLegal_BidAmountAboveMinimum_true() {
		assertTrue(testBidAmountAboveMin.isBidAmountLegal());
	}
	
	@Test
	public void isBidAmountLegal_BidAmountEqualMinimum_true() {
		assertTrue(testBidAmountEqualsMin.isBidAmountLegal());
	}
	
	@Test
	public void isBidAmountLegal_BidAmountUnderMinimum_false() {	
		assertFalse(testBidAmountUnderMin.isBidAmountLegal());
	}
		
	@Test
	public void isBidDateLegal_BidPlacedDayBefore_true() {
		assertTrue(testBidDayBefore.isBidDateLegal());
	}
	
	@Test 
	public void isBidDateLegal_BidPlacedDayOf_false() {	
		assertFalse(testBidDayOf.isBidDateLegal());
	}
	
	@Test
	public void isBidDateLegal_BidPlacedDayAfter_false() {
		assertFalse(testBidDayAfter.isBidDateLegal());
	}
	
	@Test
	public void isBidderAtMaxBidsForAuction_OneLessThanMaxBids_false() {
		testBidder1.placeBid(testBidAmountAboveMin);
		assertFalse(testBidder1.isBidderAtMaxBidsForAuction(testAuctionPresent));
	}
	
	@Test
	public void isBidderAtMaxBidsForAuction_MaxBidsForAuction_true() {
		testBidder1.placeBid(testBidAmountAboveMin);
		testBidder1.placeBid(testBidAmountEqualsMin);
		assertTrue(testBidder1.isBidderAtMaxBidsForAuction(testAuctionPresent));
	}
	
	@Test
	public void isBidderAtMaxBidsForAllAuctions_OneLessThanMaxBids_false() {
		testBidder1.placeBid(testBidAmountEqualsMin);
		testBidder1.placeBid(testBidAmountAboveMin);
		assertFalse(testBidder1.isBidderAtMaxBidsForAllAuctions());
		
	}
	
	@Test 
	public void isBidderAtMaxBidsForAllAuctions_MaxBidsForAllAuctions_true() {
		testBidder1.placeBid(testBidAmountEqualsMin);
		testBidder1.placeBid(testBidAmountAboveMin);
		testBidder1.placeBid(testBidAmountAboveMin2);
		assertTrue(testBidder1.isBidderAtMaxBidsForAllAuctions());
		
	}
	
	
	
}
