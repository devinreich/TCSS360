package unit_tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Controller.Run;
import Model.Auction;
import Model.Bid;
import Model.Bidder;
import Model.Item;
import Model.Organization;





class EmployeeTest {
	
	Auction testAuctionPresent;
	Auction testAuctionPast;
	Auction testAuctionFuture;
	ArrayList<Auction> auctionList;
	Bid bidForItem1;
	Bid bidForItem2;
	Bid bidForItem3;
	Bidder testBidder;
	double legalBidAmountForItem1 = 105.00;
	double legalBidAmountForItem2 = 250.00;
	double legalBidAmountForItem3 = 700.00;
	LocalDate testDateBeforeAuction;
	LocalDate testDate;
	LocalDate testCreationDate;
	LocalDate testDateFuture;
	LocalDate testDatePast;
	int testMaxItemsPerBidder;
	int testMaxItemsTotal;
	Item item1;
	Item item2;
	Item item3;
	Item item4;
	Item item5;
	Organization testOrganization;
	Organization testOrganization2;
	Organization testOrganization3;
	

	@BeforeEach
	void setUp() throws Exception {
		testDateBeforeAuction = LocalDate.of(2018, 7, 2);
		testDate = LocalDate.of(2018, 7, 3);
		testDateFuture = LocalDate.of(2018, 7, 22);
		testCreationDate = LocalDate.now();
		testDatePast = LocalDate.of(2018, 6, 10);
		testMaxItemsPerBidder = 2;
		testMaxItemsTotal = 5;
		item1 = new Item("Antique Chair", "An antique chair from France",
				  		 100.00, testCreationDate);
		item2 = new Item("Antique Sofa", "An antique sofa from France",
			      		 200.00, testCreationDate);
		item3 = new Item("Tiffany Lamp", "Vintage Tiffany Lamp",
						600.00, testCreationDate);
		item4 = new Item("Vintage Coffee Table", "A vintage coffee table",
						 150.00, testCreationDate);
		item5 = new Item("Antique Dining Set", 
						"Antique Table with set of 4 chairs",
						1000.00, testCreationDate);
		testOrganization = new Organization("Goodwill");
		testOrganization2 = new Organization("Ehli Auctions");
		testOrganization3 = new Organization("AuctioneerPros");
		testAuctionPresent = new Auction(testDate, testCreationDate, testMaxItemsPerBidder, testMaxItemsTotal, testOrganization);
		testAuctionPast = new Auction(testDatePast, testCreationDate, testMaxItemsPerBidder, testMaxItemsTotal, testOrganization2);
		testAuctionFuture = new Auction(testDateFuture, testCreationDate, testMaxItemsPerBidder, testMaxItemsTotal, testOrganization3);

		testBidder = new Bidder("Bob", "bobby-from-KOH");
		auctionList = new ArrayList<Auction>();
		auctionList.add(testAuctionPresent);
	}

	/**
	 * Tests if an auction that has no bids can be canceled.
	 */
	@Test
	public void cancelAuction_auctionCanBeCanceled_true() {
		testAuctionPresent.addItem(item1);
		testAuctionPresent.addItem(item2);
		Run.calendar.cancelAuction(testAuctionPresent);
		
		assertTrue(!auctionList.contains(testAuctionPresent));
	
	}
	
	/**
	 * Tests if an auction that has bids can be canceled.
	 */
	@Test
	public void cancelAuction_auctionCannotBeCanceled_false() {
		testAuctionPresent.placeBid(legalBidAmountForItem1, testDate, testBidder, item1);
		testAuctionPresent.addItem(item1);
		Run.calendar.cancelAuction(testAuctionPresent);
		
		assertFalse(auctionList.contains(testAuctionPresent));
		
	}
	
	/** 
	 * Tests if employee can view auctions between two dates.
	 */
	@Test
	public void canGetAuctionsBetweenTwoDates_secondDateEarlierThanFirst_false() {
		
		assertFalse(Run.calendar.canGetAuctionsBetweenTwoDates(testDate, testDateBeforeAuction));
		
	}
	
	/**
	 * Tests if employee can view auctions between two dates.
	 */
	@Test
	public void canGetAuctionsBetweenTwoDates_firstAndSecondDateSame_true() {
		
		assertTrue(Run.calendar.canGetAuctionsBetweenTwoDates(testDate, testDate));
		
	}
	
	/**
	 * Tests if employee can view auctions between two dates.
	 */
	@Test
	public void canGetAuctionsBetweenTwoDates_secondDateLaterThanFirst_true() {
		
		assertTrue(Run.calendar.canGetAuctionsBetweenTwoDates(testDateBeforeAuction, testDate));
		
	}
	
//	public void

}
