/**
 * Nadia Polk
 * Test Business Rule 5a/b
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
 * @author PrancingPonies
 * @version 1.0
 */
public class BidTest {
	
	LocalDate testAuctionStartDate;
	LocalDate testAuctionEndDate;
	LocalDate testItemCreationDate;
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
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() {
		
		LocalDate testStartDate = LocalDate.of(2018,7,03);
		LocalDate testEndDate = LocalDate.of(2018,7,04);
		LocalDate testCreationDate = LocalDate.now();
		testItem = new Item("Antique Magic 8 Ball", "Guaruntees your future",
				  		 1000.00, testCreationDate);
		testMaxItemsPerBidder = 2;
		organizationNumber = new PhoneNumber(253, 222, 4516);
		User[] users = new User[1];
		Auction[] auctions = new Auction[1];
		testOrganization = new Organization("Goodwill", organizationNumber,
				   "Contact Robert Smith", users, auctions);
		testAuction = new Auction(testStartDate, testEndDate,
				 testCreationDate, testMaxItemsPerBidder, testOrganization);		
		testAuctionStartDate = testAuction.getStartDate();
		testBidLessThanBasePrice = new Double(testItem.getBasePrice()/2); 
		testBidEqualToBasePrice = new Double(testItem.getBasePrice()); 
		testBidGreaterThanBasePrice =new Double(testItem.getBasePrice() * 2);		
		testBidTimeDayBefore = testAuctionStartDate.minusDays(1);
		testBidTimeDayOf = testAuctionStartDate;
		testBidTimeDayAfter = testAuctionStartDate.plusDays(1);
		
		Bidder testBidder = new Bidder("Nadia Polk", "polkn", new PhoneNumber(512, 569, 7725),
				"polkn@uw.edu");
		/*  Create Bids with variable bid dates - use legal bid amount */
		testBidDayBefore = new Bid(testBidGreaterThanBasePrice, testBidTimeDayBefore, testBidder, testItem.getDescription(), testItem, testAuction);
		testBidDayOf = new Bid(testBidGreaterThanBasePrice, testBidTimeDayOf, testBidder, testItem.getDescription(), testItem, testAuction);
		testBidDayAfter = new Bid(testBidGreaterThanBasePrice, testBidTimeDayAfter, testBidder, testItem.getDescription(), testItem, testAuction);
		/* Create Bids with variable bid amounts- use legal date */
		testBidAmountUnderMin = new Bid(testBidLessThanBasePrice, testBidTimeDayBefore, testBidder, testItem.getDescription(), testItem, testAuction);
		testBidAmountEqualsMin = new Bid(testBidEqualToBasePrice, testBidTimeDayBefore, testBidder, testItem.getDescription(), testItem, testAuction);
		testBidAmountAboveMin = new Bid(testBidGreaterThanBasePrice, testBidTimeDayBefore, testBidder, testItem.getDescription(), testItem, testAuction);	
	}

	/*
	 * Make sure bid amount validity is being correctly assessed.
	 * @result Method will return true if the bid amount is legal (greater than base price).
	 */
	@Test
	public void isBidAmountLegalTest() {
		
		assertFalse(testBidAmountUnderMin.isBidAmountLegal());
		assertTrue(testBidAmountEqualsMin.isBidAmountLegal());
		assertTrue(testBidAmountAboveMin.isBidAmountLegal());
	}
	
	 /* Make sure bid date validity is being correctly assessed.
	 * @result Method will return true if the bid date is legal (before midnight on start date).
	 */
	@Test
	public void isBidDateLegalTest() {
		
		assertTrue(testBidDayBefore.isBidDateLegal());
		assertFalse(testBidDayOf.isBidDateLegal());
		assertFalse(testBidDayAfter.isBidDateLegal());
	}
}
