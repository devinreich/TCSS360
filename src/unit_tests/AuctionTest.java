/**
 * 
 */
package unit_tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Model.Auction;
import Model.Item;
import Model.Organization;
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
	LocalDate testStartDate;
	LocalDate testEndDate;
	LocalDate testCreationDate;
	LocalTime testTime;
	int testMaxItemsPerBidder;
	int testMaxItemsTotal;
	Item item1;
	Item item2;
	Item item3;
	Item item4;
	Item item5;
	Organization testOrganization;
	
	/**
	 * Sets up new auction and items for each test.
	 */
	@BeforeEach
	void setUp() {
		testStartDate = LocalDate.of(2018, 7, 3);
		testEndDate = LocalDate.of(2018, 7, 4);
		testCreationDate = LocalDate.now();
		testTime = LocalTime.now();
		testMaxItemsPerBidder = 2;
		testMaxItemsTotal = 10;
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
		
		ArrayList<Auction> auctions = new ArrayList<>();
		testOrganization = new Organization("Goodwill");
		testAuction = new Auction(testStartDate, testCreationDate, testMaxItemsPerBidder, testMaxItemsTotal, testOrganization);
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
		testAuction.addItem(item5);;
		
		assertTrue(testAuction.isAuctionAtMaxCapacity());
	}
}
