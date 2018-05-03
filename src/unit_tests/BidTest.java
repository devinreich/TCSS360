/**
 * Class: BidTest
 * Author: Nadia Polk
 * Purpose: (Test Business Rule 5a/b) Test class for placing Bids on Items in Auctions. 
 */
package unit_tests;

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
import java.time.LocalDate;

/**
 * Test class for placing Bids on Items.
 * @author nadiapolk
 * @version 5/3/2018
 */
public class BidTest {
	
	LocalDate testAuctionStartDate;
	LocalDate testAuctionEndDate;
	LocalDate testAuctionCreationDate;
	LocalDate testBidTimeDayBefore;
	LocalDate testBidTimeDayOf;
	LocalDate testBidTimeDayAfter;
	Item testItem;
	Bid testBidDayBefore;
	Bid testBidDayOf;
	Bid testBidDayAfter;
	Bid testBidAmountUnderMin;
	Bid testBidAmountEqualsMin;
	Bid testBidAmountAboveMin;
	Double testBidLessThanBasePrice;
	Double testBidEqualToBasePrice;
	Double testBidGreaterThanBasePrice;
	User testBidder;
	Auction testAuction;
	Integer testMaxItemsPerBidder;
	PhoneNumber organizationNumber;
	Organization testOrganization;
	
	/**
	 * Sets up test bid amounts for placing bids under minimum required bid, 
	 * equal to required bid, and above required bid for item.
	 * Also sets up test bid dates for 1 day before auction start, day of auction start, 
	 * and one day after auction start.
	 * @author nadiapolk
	 * @version 5/3/2018
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() {
		
		LocalDate testAuctionStartDate = LocalDate.of(2018,7,03);
		LocalDate testAuctionEndDate = LocalDate.of(2018,7,04);
		LocalDate testAuctionCreationDate = LocalDate.now();
		testItem = new Item("Antique Magic 8 Ball", "Guaruntees your future",
				  		 1000.00, testAuctionCreationDate);
		testMaxItemsPerBidder = 2;
		organizationNumber = new PhoneNumber(253, 222, 4516);
		User[] users = new User[1];
		Auction[] auctions = new Auction[1];
		testOrganization = new Organization("Goodwill", organizationNumber,
				   "Contact Robert Smith", users, auctions);
		testAuction = new Auction(testAuctionStartDate, testAuctionEndDate,
				testAuctionCreationDate, testMaxItemsPerBidder, testOrganization);		
		testBidLessThanBasePrice = new Double(testItem.getBasePrice()/2); 
		testBidEqualToBasePrice = new Double(testItem.getBasePrice()); 
		testBidGreaterThanBasePrice =new Double(testItem.getBasePrice() * 2);		
		testBidTimeDayBefore = testAuctionStartDate.minusDays(1);
		testBidTimeDayOf = testAuctionStartDate;
		testBidTimeDayAfter = testAuctionStartDate.plusDays(1);
		Bidder testBidder = new Bidder("Nadia Polk", "polkn", new PhoneNumber(512, 569, 7725), "polkn@uw.edu");
		/*  Create Bids with variable bid dates - use legal bid amount */
		testBidDayBefore = new Bid(testBidGreaterThanBasePrice, testBidTimeDayBefore, testBidder, testItem, testAuction);
		testBidDayOf = new Bid(testBidGreaterThanBasePrice, testBidTimeDayOf, testBidder, testItem, testAuction);
		testBidDayAfter = new Bid(testBidGreaterThanBasePrice, testBidTimeDayAfter, testBidder, testItem, testAuction);
		/* Create Bids with variable bid amounts- use legal date */
		testBidAmountUnderMin = new Bid(testBidLessThanBasePrice, testBidTimeDayBefore, testBidder, testItem, testAuction);
		testBidAmountEqualsMin = new Bid(testBidEqualToBasePrice, testBidTimeDayBefore, testBidder, testItem, testAuction);
		testBidAmountAboveMin = new Bid(testBidGreaterThanBasePrice, testBidTimeDayBefore, testBidder, testItem, testAuction);	
	}

	/**
	 * Tests that the bid amount validity is being correctly assessed.
	 * Method will return true if the bid amount is legal (greater than or equal to 
	 * base price of item).
	 * @author nadiapolk
	 * @version 5/3/2018
	 */
	@Test
	public void isBidAmountLegalTest() {
		
		assertFalse(testBidAmountUnderMin.isBidAmountLegal());
		assertTrue(testBidAmountEqualsMin.isBidAmountLegal());
		assertTrue(testBidAmountAboveMin.isBidAmountLegal());
	}
	
	 /** Tests that the bid date validity is being correctly assessed.
	  * Method will return true if the bid date is legal (before midnight on start date).
	  * @author nadiapolk
	  * @version 5/3/2018
	  */
	@Test
	public void isBidDateLegalTest() {
		
		assertTrue(testBidDayBefore.isBidDateLegal());
		assertFalse(testBidDayOf.isBidDateLegal());
		assertFalse(testBidDayAfter.isBidDateLegal());
	}
}
