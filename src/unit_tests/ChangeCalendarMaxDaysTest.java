package unit_tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Model.Calendar;

public class ChangeCalendarMaxDaysTest {
	Calendar testCalendar;
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testChangeMaximumUpcomingDays() {
		testCalendar.changeMaximumUpcomingDays();
	}

}
