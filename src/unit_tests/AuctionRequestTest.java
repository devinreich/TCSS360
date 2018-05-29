/* Prancing Ponies (Group 2)
 * David Monaghan Unit Test for Auction Redundancy in calendar.
 */
package unit_tests;

import static org.junit.Assert.*;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import Controller.Run;
import Controller.Serializer;
import GUI.AuctionCentral;
import Model.Auction;
import Model.Calendar;
import Model.Organization;

/**
 * Test Class to request an Auction from the Calendar
 * These tests will ensure that scheduling conflicts will not 
 * occur while requesting to add a new auction.
 * @author DavidMonaghan
 * @Version 5/3/2018
 */
public class AuctionRequestTest {
	
	private Organization testOrganization;
	private Organization testOrganization2;
	private Integer testMaxItemsPerBidder;
	private Integer testMaxItemsSold;
	private LocalDate testAuctionDate;
	private Calendar calendar;
	
	@Before
	public void setUp() {
		testMaxItemsPerBidder = new Integer(5);		
		testMaxItemsSold = new Integer (10);		
		calendar = (Calendar) Serializer.deserialize("calendar");
		testAuctionDate = LocalDate.now().plusDays(calendar.getMinimumUpcomingDays());
		testOrganization = new Organization("Salvation Army");
		testOrganization2 = new Organization("Goodwill");
	}
		
	
	/**
	 * Tests that an organization can book an auction when they have no prior auctions
	 * on the calendar.
	 */
	@Test 
	public void hasItBeenAYearSinceLastAuction_noPriorAuctions_true() {
		calendar.wipeSchedule(); /* Reset calendar entry point to today */
		assertTrue(calendar.checkBeenYearForOrg(testOrganization, testAuctionDate));	
	}
	
	
	/**
	 * Tests that an organization can book an auction when they had an auction
	 * exactly one year ago.
	 */
	@Test 
	public void hasItBeenAYearSinceLastAuction_requestOneYearAhead_true() {
		calendar.wipeSchedule();
		/* Book auction on today's date  (plus minimum schedule out days) */
		Auction testAuction = new Auction(testAuctionDate, LocalDate.now(), testMaxItemsPerBidder,
				testMaxItemsSold, testOrganization);
		calendar.submitAuctionRequestWithAuction(testOrganization, testAuction);
		/* Move forward one year */
		calendar.setCurrentDate(LocalDate.now().plusYears(1).plusDays(calendar.getMinimumUpcomingDays()));
		/* Request a date exactly one year out from last auction */
		LocalDate yearOutDate = LocalDate.now().plusYears(1).plusDays(calendar.getMinimumUpcomingDays());
		assertTrue(calendar.checkBeenYearForOrg(testOrganization, yearOutDate));	
	}
		
	
	/**
	 * Tests that an organization cannot book an auction one day before a year out 
	 * from their last auction.
	 */
	@Test 
	public void hasItBeenAYearSinceLastAuction_requestOneDayLessThanYearAhead_false() {

		calendar.wipeSchedule(); 
		calendar.setCurrentDate(LocalDate.now()); /* Reset calendar entry point to today */
		/* Book auction on today's date (plus minimum schedule out days) */
		Auction testAuction = new Auction(testAuctionDate, LocalDate.now(), testMaxItemsPerBidder,
				testMaxItemsSold, testOrganization2);
		calendar.submitAuctionRequestWithAuction(testOrganization, testAuction);		
		calendar.setCurrentDate(LocalDate.now().plusYears(1)); /* Move forward one year  */
		/* Request a date exactly one year minus one day out from last auction */
		LocalDate yearOutDate = LocalDate.now().plusYears(1).plusDays(calendar.getMinimumUpcomingDays()-1);
		assertFalse(calendar.checkBeenYearForOrg(testOrganization, yearOutDate));	
	}
	
	
	/**
	 * Tests to ensure an auction can be requested on a date when there are no auctions.
	 */
	@Test 
	public void isAuctionRequestDateAllowed_noAuctionsOnDate_true() {		
		calendar.wipeSchedule();
		calendar.setCurrentDate(LocalDate.now()); /* Reset calendar entry point to today */
		assertTrue(calendar.checkDate(LocalDate.now().plusDays(25)));	
	}
	
	
	/**
	 * Test to ensure auction can be added to a day with only 1 entry.
	 */
	@Test
	public void isAuctionRequestDateAllowed_oneAuctionOnDate_true() {
		calendar.wipeSchedule();
		calendar.setCurrentDate(LocalDate.now()); /* Reset calendar entry point to today */
		Auction testAuction = new Auction(testAuctionDate, LocalDate.now(), testMaxItemsPerBidder,
				testMaxItemsSold, testOrganization);
		calendar.submitAuctionRequestWithAuction(testOrganization, testAuction);
		assertTrue(calendar.checkDate(testAuctionDate));		
	}
		
	
	/**
	 * Test to ensure auction cannot be added to a day with 2 entries.
	 */
	@Test
	public void isAuctionRequestDateAllowed_twoAuctionsOnDate_false() {
		calendar.wipeSchedule();
		calendar.setCurrentDate(LocalDate.now()); /* Reset calendar entry point to today */
		Auction testAuction = new Auction(testAuctionDate, LocalDate.now(), testMaxItemsPerBidder,
				testMaxItemsSold, testOrganization);
		Auction testAuction2 = new Auction(testAuctionDate, LocalDate.now(), testMaxItemsPerBidder,
				testMaxItemsSold, testOrganization2);
		calendar.submitAuctionRequestWithAuction(testOrganization, testAuction);
		calendar.submitAuctionRequestWithAuction(testOrganization2, testAuction2);
		assertFalse(calendar.checkDate(testAuctionDate));		
	}
	
	
	/** 
	 * Tests contact people can book an auction when there is one less
	 * auction on the schedule than the max allowed amount.
	 */
	@Test
	public void isAuctionAmountLegal_oneLessThanMaximumAllowedAutions_true() {
		
		calendar.wipeSchedule();
		calendar.setCurrentDate(LocalDate.now()); /* Reset calendar entry point to today */
		calendar.setMaximumUpcomingAuctions(3); /* Set max allowed upcoming auctions to 3 */	
		/* Make two auctions on the calendar on same date */
		Auction testAuction = new Auction(testAuctionDate, LocalDate.now(), testMaxItemsPerBidder,
				testMaxItemsSold, testOrganization);
		Auction testAuction2 = new Auction(testAuctionDate, LocalDate.now(), testMaxItemsPerBidder,
				testMaxItemsSold, testOrganization2);
		calendar.submitAuctionRequestWithAuction(testOrganization, testAuction);
		calendar.submitAuctionRequestWithAuction(testOrganization2, testAuction2);
		assertTrue(calendar.checkForUpComingAuctionNumber());
	}
	
	
	/** 
	 * Tests contact people cannot book an auction when there is exactly
	 * the maximum number of allowed upcoming auctions in system.
	 */
	@Test
	public void isAuctionAmountLegal_exactlyMaximumAllowedAutions_false() {		
		calendar.wipeSchedule();
		calendar.setCurrentDate(LocalDate.now()); 	/* Reset calendar entry point to today */
		calendar.setMaximumUpcomingAuctions(2); /* Set max allowed upcoming auctions to 2 */		
		/* Make two auctions on the calendar on same date */
		Auction testAuction = new Auction(testAuctionDate, LocalDate.now(), testMaxItemsPerBidder,
				testMaxItemsSold, testOrganization);
		Auction testAuction2 = new Auction(testAuctionDate, LocalDate.now(), testMaxItemsPerBidder,
				testMaxItemsSold, testOrganization2);
		calendar.submitAuctionRequestWithAuction(testOrganization, testAuction);
		calendar.submitAuctionRequestWithAuction(testOrganization2, testAuction2);
		assertFalse(calendar.checkForUpComingAuctionNumber());
	}
	
	
	/**
	 * Tests that auctions can be booked if the request date is less
	 * than the maximum number of days specified out in advance.
	 */
	public void isRequestFarEnoughOut_RequestIsLessThanMaxDaysOut_true() {
		calendar.wipeSchedule();
		calendar.setCurrentDate(LocalDate.now()); 	/* Reset calendar entry point to today */
		assertTrue(calendar.checkForMaxDays(testAuctionDate));
	}
	
	
	/**
	 * Tests that auctions can be booked if the request date is exactly
	 * than the maximum number of days specified out in advance.
	 */
	public void isRequestFarEnoughOut_RequestIsExactlyMaxDaysOut_true() {
		calendar.wipeSchedule();
		calendar.setCurrentDate(LocalDate.now()); 	/* Reset calendar entry point to today */
		LocalDate maxDaysOut = LocalDate.now().plusDays(calendar.getMaximumUpcomingDays());
		assertTrue(calendar.checkForMaxDays(maxDaysOut));
	}
	
	
	/**
	 * Tests that auctions cannot be booked if the request date is 
	 * one day more than the maximum number of days out.
	 */
	public void isRequestFarEnoughOut_RequestIsOneDayMoreThanMaxDaysOut_false() {
		calendar.wipeSchedule();
		calendar.setCurrentDate(LocalDate.now()); 	/* Reset calendar entry point to today */
		LocalDate maxDaysOut = LocalDate.now().plusDays(calendar.getMaximumUpcomingDays()).plusDays(1);
		assertFalse(calendar.checkForMaxDays(maxDaysOut));
	}
	
	
	/**
	 * Tests that auctions can be booked if the request date is more
	 * than the minimum number of days specified out in advance.
	 */
	public void isRequestFarEnoughOut_RequestIsMoreThanMinDaysOut_true() {
		calendar.wipeSchedule();
		calendar.setCurrentDate(LocalDate.now()); 	/* Reset calendar entry point to today */
		assertTrue(calendar.checkForMaxDays(testAuctionDate));
	}
	
	
	/**
	 * Tests that auctions can be booked if the request date is exactly
	 *  the minimum number of days specified out in advance.
	 */
	public void isRequestFarEnoughOut_RequestIsExactlyMinDaysOut_true() {
		calendar.wipeSchedule();
		calendar.setCurrentDate(LocalDate.now()); 	/* Reset calendar entry point to today */
		LocalDate minDaysOut = LocalDate.now().plusDays(calendar.getMinimumUpcomingDays());
		assertTrue(calendar.checkForMaxDays(minDaysOut));
	}
	
	
	/**
	 * Tests that auctions cannot be booked if the request date is 
	 * one day less than the minimum number of days out.
	 */
	public void isRequestFarEnoughOut_RequestIsOneDayLessThanMinDaysOut_false() {
		calendar.wipeSchedule();
		calendar.setCurrentDate(LocalDate.now()); 	/* Reset calendar entry point to today */
		LocalDate minDaysOut = LocalDate.now().plusDays(calendar.getMinimumUpcomingDays()).minusDays(1);
		assertFalse(calendar.checkForMaxDays(minDaysOut));
	}	
}