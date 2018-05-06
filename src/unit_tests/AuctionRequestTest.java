/* Prancing Ponies (Group 2)
 * David Monaghan Unit Test for Auction Redundancy in calendar.
 */
package unit_tests;

import static org.junit.Assert.*;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import Model.Auction;
import Model.Calendar;
import Model.Organization;
import Model.PhoneNumber;
import Model.User;

/**
 * Test Class to request an Auction from the Calendar
 * These tests will ensure that scheduling conflicts will not 
 * occur while requesting to add a new auction.
 * @author DavidMonaghan
 * @Version 5/3/2018
 */
public class AuctionRequestTest {
	
	Auction testAuction;
	LocalDate testInitialDate;
	PhoneNumber testOrganizationNumber;
	Organization testOrganization;
	int testMaxItemsPerBidder;
	
	Calendar testCalendarNoAuctionsAdded;
	Calendar testCalendarOneAuctionAdded;
	Calendar testCalendarTwoAuctionsSameDay;
	
	
	/**
	 * Sets up initial parameters needed to complete each test.
	 * Creates an Auction and a User to add into the Calendar being 
	 * tested.
	 */
	@Before
	public void setUp() {
		testInitialDate = LocalDate.of(2007, 3, 17);
		testMaxItemsPerBidder = 0;		
		
		User[] users = new User[1];
		Auction[] auctions = new Auction[1];
		testOrganizationNumber = new PhoneNumber(555, 867, 5309);
		testOrganization = new Organization("Salvation Army", testOrganizationNumber, 
				"Contact Jane Doe", users, auctions);
		
		testAuction = new Auction(testInitialDate, testInitialDate,
								 testInitialDate, testMaxItemsPerBidder, 
								 testOrganization);
		
		
		
	}
	
	/**
	 * Test to ensure Auction can be added to an empty day.
	 */
	@Test 
	public void isAuctionRequestDateAllowed_noAuctionsOnDate_true() {
		assertTrue(testCalendarNoAuctionsAdded.checkDate(testInitialDate));
		
		
	}
	
	/**
	 * Test to ensure auction can be added to a day with only 1 entry.
	 */
	@Test
	public void isAuctionRequestDateAllowed_oneAuctionsOnDate_true() {
		testCalendarOneAuctionAdded.addAuction(testAuction);
		assertTrue(testCalendarOneAuctionAdded.checkDate(testInitialDate));
		
	}
	
	/**
	 * Test to ensure no auction can be added to a day with 2 entries.
	 */
	@Test
	public void isAuctionRequestDateAllowed_twoAuctionsOnDate_false() {
		testCalendarTwoAuctionsSameDay.addAuction(testAuction);
		testCalendarTwoAuctionsSameDay.addAuction(testAuction);
		assertFalse(testCalendarTwoAuctionsSameDay.checkDate(testInitialDate));
	}
}
