package View;

import java.util.Scanner;

import Controller.Run;
import Model.Employee;

public class EmployeeMenu {

	private Employee employee;

	public EmployeeMenu(Employee theEmployee) {
		employee = theEmployee;
	}

	public void launchMenu() {
		System.out.println("Which option would you like to choose?");
		Scanner scanner = new Scanner(System.in);
		String option;

		do {
			displayEmployeeStartMenu(scanner);
	
			
			System.out.println("\nEnter c to continue, q to quit.");
			option = scanner.next();
		} while (!option.equals("q"));

	}

	public void displayEmployeeStartMenu(Scanner theScanner) {

		System.out.println("1)  Change Maximum Number Of Upcoming Auctions System Can Hold");
		System.out.println("2)  View All Auctions Between Two Dates");
		System.out.println("3)  View All Auctions In Chronological Order");
		System.out.println("4)  Cancel An Upcoming Auction");
		System.out.println("Enter your choice: ");
		
		switch(theScanner.nextInt()) {
		
		case 1:
			Run.calendar.changeMaximumUpcomingDays();
			break;
			
		case 2: 
		}
				
	}

}
