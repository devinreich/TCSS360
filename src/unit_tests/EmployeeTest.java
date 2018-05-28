package unit_tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Controller.Run;
import GUI.AuctionCentral;
import Model.Employee;

class EmployeeTest {
	
	private static int negativeMaxUpcomingAuctions = -1;
	private static int positiveMaxUpcomingAuctions = 1;
	private static int positiveMaxUpcomingAuctionsGreaterThanCurrent = AuctionCentral.calendar.getMaximumUpcomingDays() + 1;

	/** 
	 * Tests if employee can change max number of upcoming auctions in system.
	 */
	@Test
	public void canChangeMaxUpcomingAuctions_NonPositiveInt_false() {
		assertFalse(AuctionCentral.calendar.canChangeMaxUpcomingAuctions(negativeMaxUpcomingAuctions));
	}
	
	/** 
	 * Tests if employee can change max number of upcoming auctions in system.
	 */
	@Test
	public void canChangeMaxUpcomingAuctions_PositiveIntGreaterThanZero_true() {
		assertTrue(AuctionCentral.calendar.canChangeMaxUpcomingAuctions(positiveMaxUpcomingAuctions));
	}
	
	/** 
	 * Tests if employee can change max number of upcoming auctions in system.
	 */
	@Test
	public void canChangeMaxUpcomingAuctions_PositiveIntGreaterThanNumberofExisitingAuctions_true() {
		assertTrue(AuctionCentral.calendar.canChangeMaxUpcomingAuctions(positiveMaxUpcomingAuctionsGreaterThanCurrent)
				&& positiveMaxUpcomingAuctionsGreaterThanCurrent > AuctionCentral.calendar.getMaximumUpcomingAuctions());
	}

}
