package unit_tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Controller.Run;
import Controller.Serializer;
import GUI.AuctionCentral;
import Model.Calendar;
import Model.Employee;

class EmployeeTest {
	
	private static int negativeMaxUpcomingAuctions = -1;
	private static int positiveMaxUpcomingAuctions = 1;
	private static Calendar calendar = (Calendar) Serializer.deserialize("calendar");
	private static int positiveMaxUpcomingAuctionsGreaterThanCurrent = calendar.getMaximumUpcomingDays() + 1;

	
	/** 
	 * Tests if employee can change max number of upcoming auctions in system.
	 */
	@Test
	public void canChangeMaxUpcomingAuctions_NonPositiveInt_false() {
		assertFalse(calendar.canChangeMaxUpcomingAuctions(negativeMaxUpcomingAuctions));
	}
	
	/** 
	 * Tests if employee can change max number of upcoming auctions in system.
	 */
	@Test
	public void canChangeMaxUpcomingAuctions_PositiveIntGreaterThanZero_true() {
		assertTrue(calendar.canChangeMaxUpcomingAuctions(positiveMaxUpcomingAuctions));
	}
	
	/** 
	 * Tests if employee can change max number of upcoming auctions in system.
	 */
	@Test
	public void canChangeMaxUpcomingAuctions_PositiveIntGreaterThanNumberofExisitingAuctions_true() {
		assertTrue(calendar.canChangeMaxUpcomingAuctions(positiveMaxUpcomingAuctionsGreaterThanCurrent)
				&& positiveMaxUpcomingAuctionsGreaterThanCurrent > calendar.getMaximumUpcomingAuctions());
	}

}
