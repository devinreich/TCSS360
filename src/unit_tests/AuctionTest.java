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
import Model.PhoneNumber;
import Model.User;
import java.util.Date;

/**
 * @author PrancingPonies
 * @author Brittany Byrd bbyrd227@uw.edu
 * @version 5.1.2018
 */
class AuctionTest {
	
	Auction testAuction;
	Date testStartDate;
	Date testEndDate;
	Date testCreationDate;
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
		testStartDate = new Date(7, 3, 2018);
		testEndDate = new Date(7, 4, 2018);
		organizationNumber = new PhoneNumber(253, 222, 4516);
		testCreationDate = new Date();
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
		Auction[] auctions = new Auction[1];
		testOrganization = new Organization("Goodwill", 
											organizationNumber,
										   "Contact Robert Smith", 
										   users,
										   auctions);
		testAuction = new Auction(testStartDate, testEndDate,
								 testCreationDate, 
								 testMaxItemsPerBidder, 
								 testOrganization);
										   	
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
