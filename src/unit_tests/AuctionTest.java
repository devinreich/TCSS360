/**
 * 
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
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author PrancingPonies
 * @author Brittany Byrd bbyrd227@uw.edu
 * @version 5.1.2018
 */
class AuctionTest {
	
	Auction testAuction;
	Bid bidForItem1;
	Bid bidForItem2;
	Bid bidForItem3;
	Bidder testBidder;
	double legalBidAmountForItem1 = 105.00;
	double legalBidAmountForItem2 = 250.00;
	double legalBidAmountForItem3 = 700.00;
	LocalDate testDateBeforeAuction;
	LocalDate testStartDate;
	LocalDate testEndDate;
	LocalDate testCreationDate;
	LocalTime testTime;
	int testMaxItemsPerBidder;
	Item item1;
	Item item2;
	Item item3;
	Item item4;
	Item item5;
	PhoneNumber organizationNumber;
	Organization testOrganization;
	
	/**
	 * Sets up new auction and items for each test.
	 */
	@BeforeEach
	void setUp() {
		testDateBeforeAuction = LocalDate.of(2018, 7, 2);
		testStartDate = LocalDate.of(2018, 7, 3);
		testEndDate = LocalDate.of(2018, 7, 4);
		organizationNumber = new PhoneNumber(253, 222, 4516);
		testCreationDate = LocalDate.now();
		testTime = LocalTime.now();
		testMaxItemsPerBidder = 2;
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
		
		
		User[] users = new User[1];
		ArrayList<Auction> auctions = new ArrayList<>();
		testOrganization = new Organization("Goodwill", 
											organizationNumber,
										   "Contact Robert Smith", 
										   users);
		testAuction = new Auction(testStartDate, testEndDate,
								 testCreationDate, testTime, testMaxItemsPerBidder, 
								 testOrganization);
		
		testBidder = new Bidder("Bob", "bobby-from-KOH", organizationNumber, "110 10 Ave. S");
		
		bidForItem1 = new Bid(legalBidAmountForItem1, testDateBeforeAuction, item1, testAuction, testBidder);
		bidForItem2 = new Bid(legalBidAmountForItem2, testDateBeforeAuction, item2, testAuction, testBidder);
		bidForItem3 = new Bid(legalBidAmountForItem3, testDateBeforeAuction, item3, testAuction, testBidder);
	}

	/**
	 * Tests if the max capacity is not full when there are many
	 * fewer items than the max item count for auctions.
	 */
	@Test
	public void isAuctionAtMaxCapacity_ManyFewerThanMaxItemCount_false() {
		
		testAuction.addItem(item1);
		
		assertFalse(testAuction.isAuctionAtMaxCapacity());	
	}
	
	/**
	 * Tests is the max capacity is not full when there is
	 * one less than the max item count for auctions.
	 */
	@Test
	public void isAuctionAtMaxCapacity_OneLessFewerThanMaxItemCount_false() {
		
		testAuction.addItem(item1);
		testAuction.addItem(item2);
		testAuction.addItem(item3);
		testAuction.addItem(item4);
		
		assertFalse(testAuction.isAuctionAtMaxCapacity());
	}
	
	/**
	 * Tests is the max capacity is full when there are the max
	 * number of items for auctions.
	 */
	@Test
	public void isAuctionAtMaxCapacity_AuctionExactlyAtMaxCapacity_true() {
		
		testAuction.addItem(item1);
		testAuction.addItem(item2);
		testAuction.addItem(item3);
		testAuction.addItem(item4);
		testAuction.addItem(item5);
		
		assertTrue(testAuction.isAuctionAtMaxCapacity());
	}
	
	/**
	 * Tests if an auction can be cancelled when it has no bids.
	 */
	@Test
	public void canAuctionBeCancelled_AuctionHasNoBids_true() {
		
		testAuction.addItem(item1);
		
		assertTrue(testAuction.canAuctionBeCancelled());
	}
	
	/**
	 * Tests if an auction can be cancelled when the auction has one bid.
	 */
	@Test
	public void canAuctionBeCancelled_AuctionHasOneBid_false() {
		
		testAuction.addItem(item1);
		testBidder.placeBid(bidForItem1);
		
		assertFalse(testAuction.canAuctionBeCancelled());	
		
	}
	
	/** 
	 * Tests if an auction can be cancelled when it has many bids.
	 */
	@Test 
	public void canAuctionBeCancelled_AuctionHasManyBids_false() {
		
		testAuction.addItem(item1);
		testAuction.addItem(item2);
		testAuction.addItem(item3);
		
		testBidder.placeBid(bidForItem1);
		testBidder.placeBid(bidForItem2);
		testBidder.placeBid(bidForItem3);

		assertFalse(testAuction.canAuctionBeCancelled());	
	}
}
