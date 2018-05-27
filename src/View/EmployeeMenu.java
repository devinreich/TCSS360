
package View;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import Controller.Run;
import GUI.AuctionCentral;
import Model.Auction;
import Model.Calendar;
import Model.Employee;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.converter.NumberStringConverter;

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
			System.out.println("Current System Auction Capacity: " + Run.calendar.getMaximumUpcomingAuctions());
			System.out.println("Enter New System Auction Capacity: ");
			Run.calendar.setMaximumUpcomingAuctions(theScanner.nextInt());
			break;					
		case 2: 
			ArrayList<LocalDate> auctionDates = getDatesFromUser(theScanner);
			ArrayList<Auction> auctions = Run.calendar.getAuctionsBetweenTwoDates(auctionDates.get(0), auctionDates.get(1));
			printAuctions(auctions);
			break;
		case 3: 
			ArrayList<Auction> auctions2 = Run.calendar.getAuctionsInChronologicalOrder();
			printAuctions(auctions2);
			break;
		case 4:
			Auction cancelAuction = chooseAuctionToCancel(theScanner);
			Run.calendar.cancelAuction(cancelAuction);
			break;
		}		
				
	}
	
	/**
	 * Returns a list of dates user chose view auctions between
	 * @param theScanner
	 * @return dates to check
	 */
	public ArrayList<LocalDate> getDatesFromUser(Scanner theScanner) {
		ArrayList<LocalDate> dates = new ArrayList<LocalDate>();
		System.out.println("Enter start date of interval: (mm/dd/yyyy)");
		String auctionDateString = theScanner.next();
		String[] auctionDateArr = auctionDateString.split("/");
		LocalDate theStartDate = LocalDate.of(Integer.parseInt(auctionDateArr[2]), 
										Integer.parseInt(auctionDateArr[0]),
									    Integer.parseInt(auctionDateArr[1]));
		dates.add(theStartDate);
		System.out.println("Enter end date of interval: (mm/dd/yyyy)");
		auctionDateString = theScanner.next();
		auctionDateArr = auctionDateString.split("/");
		LocalDate theEndDate = LocalDate.of(Integer.parseInt(auctionDateArr[2]), 
										Integer.parseInt(auctionDateArr[0]),
									    Integer.parseInt(auctionDateArr[1]));
		dates.add(theEndDate);
		return dates;
	}
	
	/**
	 * Returns the auction user has chosen to cancel.
	 * @param theScanner
	 * @return auction to be canceled
	 */
	public Auction chooseAuctionToCancel(Scanner theScanner) {
		ArrayList<Auction> upcomingAuctions = Run.calendar.getUpcomingAuctions();
		System.out.println("Select An Auction to Cancel.");
		int index = 1;
		for (Auction auction: upcomingAuctions) {
			System.out.println(index  + ") Auction hosted by: " + auction.getOrganization().getName()
							   + ", " + auction.getDate());
			index++;
							
		}
		System.out.println("Enter your choice: ");
		int choice = theScanner.nextInt();
		
		while (choice < 0 || choice >= upcomingAuctions.size() + 1) {
			System.out.println("Choice invalid. Enter your choice: ");
			choice = theScanner.nextInt();
		}
		return upcomingAuctions.get(choice - 1);	
	}
	
	/**
	 * Prints information about a list of auctions.
	 * @param theAuctions
	 */
	public void printAuctions(ArrayList<Auction> theAuctions) {
		for (Auction auction: theAuctions) {
			System.out.println("Auction hosted by: " + auction.getOrganization().getName());
			System.out.println("Auction date: " + auction.getDate());
		}
	}
	
	public static Scene getEmployeeMenu(Scene scene, Employee user, Calendar calendar) {
		BorderPane pane = new BorderPane();
		Text title = new Text("Welcome Employee: "  + user.getName());
		VBox layout = new VBox(20);
		layout.setPadding(new Insets(10));
		layout.getChildren().add(title);
		pane.setLeft(layout);
		scene = new Scene(pane,900,500);
		
	
		
		final Button btn = new Button("Change Maximum Number Of Upcoming Auctions System Can Hold");
		btn.setOnAction(event -> 
			changeMaxAuctions(pane)
		);
		layout.getChildren().add(btn);
			
			
		final Button btn2 = new Button("View All Auctions Between Two Dates");
		btn2.setOnAction(event -> 
			viewAuctionRange(pane, user)
		);
		layout.getChildren().add(btn2);
			
			
		final Button btn3 = new Button("View All Auctions In Chronological Order");
		btn3.setOnAction(event -> 
			viewAllAuctions(pane, user)
		);
		layout.getChildren().add(btn3);
			
			
		final Button btn4 = new Button("Cancel An Upcoming Auction");
		btn4.setOnAction(event -> 
			cancelAuction(pane, user)
		);
		layout.getChildren().add(btn4);
		
		if (layout.getChildren().size() == 1) {
			Text warning = new Text("There are no actions! Please wait for an auction to be posted");
			layout.getChildren().add(warning);
		}
		
		return scene;
	}
	
	public static void changeMaxAuctions(BorderPane pane) {
		VBox oldMenu = (VBox) pane.getLeft();
		VBox newMenu = new VBox(20);

		Text title = new Text("Current System Auction Capacity: " + 
				AuctionCentral.calendar.getMaximumUpcomingAuctions());
		newMenu.setPadding(new Insets(10));
		newMenu.getChildren().add(title);
		Button back = new Button("Back");
		Button change = new Button("Change Capacity");
		
		back.setOnAction(event -> 
			back(pane, oldMenu)
		);
		
		change.setOnAction(event -> 
			changeMax(pane, oldMenu)
		);
		newMenu.getChildren().add(change);
		newMenu.getChildren().add(back);
		pane.setLeft(newMenu);
	}
	
	public static void viewAuctionRange(BorderPane pane, Employee user) {
		final TextField userTextField1 = new TextField();
		final TextField userTextField2 = new TextField();
		VBox oldMenu = (VBox) pane.getLeft();
		VBox newMenu = new VBox(20);
		
		Text title1 = new Text("Enter start date of interval: (mm/dd/yyyy)");
		newMenu.setPadding(new Insets(10));
		newMenu.getChildren().add(title1);

		userTextField1.setMaxSize(100, 50);
		newMenu.getChildren().add(userTextField1);

		Text title2 = new Text("Enter end date of interval: (mm/dd/yyyy)");
		newMenu.getChildren().add(title2);

		userTextField2.setMaxSize(100, 50);
		newMenu.getChildren().add(userTextField2);

		Button viewBtn = new Button("View");
		Button back = new Button("Back");
		
		viewBtn.setOnAction(event -> 
			parseDates(userTextField1.getText(), userTextField2.getText(), pane, oldMenu)		
				);
		
		back.setOnAction(event -> 
			back(pane, oldMenu)
				);
		newMenu.getChildren().add(viewBtn);
		newMenu.getChildren().add(back);
		pane.setLeft(newMenu);
	}
	
	public static void parseDates(String stringDate1, String stringDate2, BorderPane oldPane, VBox oldMenu){

			String[] auctionDateArr = stringDate1.split("/");

			LocalDate theStartDate = LocalDate.of(Integer.parseInt(auctionDateArr[2]), 
										Integer.parseInt(auctionDateArr[0]),
									    Integer.parseInt(auctionDateArr[1]));
			auctionDateArr = stringDate2.split("/");
			LocalDate theEndDate = LocalDate.of(Integer.parseInt(auctionDateArr[2]), 
										Integer.parseInt(auctionDateArr[0]),
									    Integer.parseInt(auctionDateArr[1]));

		
		viewRange(theStartDate, theEndDate, oldPane, oldMenu);
	}
	public static void viewRange(LocalDate theStartDate, LocalDate theEndDate, BorderPane oldPane, VBox oldMenu){
		
			VBox lastMenu = (VBox) oldPane.getLeft();
			VBox newMenu = new VBox(20);
			ArrayList<Auction> auctionsInRange 
			= AuctionCentral.calendar.getAuctionsBetweenTwoDates(theStartDate, theEndDate);
		
			for (Auction auction: auctionsInRange) {
				Text auctionText = new Text("Auction hosted by : " + auction.getOrganization().getName() +
						"\nDate Created: " + auction.getCreateDate() + "\nDate of Auction: " +
						auction.getDate());
						
				newMenu.getChildren().add(auctionText);
			}
			Button back = new Button("Back");
			back.setOnAction(event -> 
				back(oldPane, oldMenu)
					);
			newMenu.getChildren().add(back);
			oldPane.setLeft(newMenu);
		
	}
	

	
	public static void viewAllAuctions(BorderPane pane, Employee user) {
		VBox oldMenu = (VBox) pane.getLeft();
		VBox newMenu = new VBox(20);
		
		
		newMenu.setPadding(new Insets(10));
		Button back = new Button("Back");
		ArrayList<Auction> allAuctions = AuctionCentral.calendar.getAuctionsInChronologicalOrder();
		for (Auction auction: allAuctions) {
			Text auctionText = new Text("Auction hosted by : " + auction.getOrganization().getName() +
					"\nDate Created: " + auction.getCreateDate() + "\nDate of Auction: " +
					auction.getDate());
						
			newMenu.getChildren().add(auctionText);
		}
		back.setOnAction(event -> 
			back(pane, oldMenu)
				);
		newMenu.getChildren().add(back);
		pane.setLeft(newMenu);
	}
	
	public static void cancelAuction(BorderPane pane, Employee user) {
		VBox oldMenu = (VBox) pane.getLeft();
		VBox newMenu = new VBox();
		
		ArrayList<Auction> upcomingAuctions = AuctionCentral.calendar.getUpcomingAuctions();
		Text title = new Text("All Auctions in System: ");
		newMenu.setPadding(new Insets(10));
		newMenu.getChildren().add(title);
		for (Auction auction: upcomingAuctions) {
			Text auctionText = new Text("Auction hosted by : " + auction.getOrganization().getName() +
					"\nDate Created: " + auction.getCreateDate() + "\nDate of Auction: " +
					auction.getDate());
			Button btn = new Button("Cancel This Auction");
			
			newMenu.getChildren().add(auctionText);
			newMenu.getChildren().add(btn);
		
		btn.setOnAction(event -> 
		AuctionCentral.calendar.cancelAuction(auction)
				);
							
		}
		Button back = new Button("Back");
		
		back.setOnAction(event -> 
			back(pane, oldMenu)
				);
		
		newMenu.getChildren().add(back);
		pane.setLeft(newMenu);
	}
	


	
	public static void back(BorderPane pane, VBox oldMenu) {
		pane.setLeft(oldMenu);
		pane.setCenter(new VBox());;
	}
	

	
	private static void changeMax(BorderPane oldPane, VBox oldMenu) {
		final TextField userTextField = new TextField();
	
		VBox lastMenu = (VBox) oldPane.getLeft();
		VBox newMenu = new VBox(20);

		Text title = new Text("Enter New System Auction Capacity: ");
		newMenu.setPadding(new Insets(10));
		newMenu.getChildren().add(title);
		
		userTextField.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
		userTextField.setMaxSize(100, 50);
		
		Button back = new Button("Back");
		Button change = new Button("Change");
		
		back.setOnAction(event -> 
			back(oldPane, oldMenu)
		);
		
		change.setOnAction(event -> 
			changeAndRebuild(oldPane, userTextField.getText(), oldMenu)
		);
		newMenu.getChildren().add(userTextField);
		newMenu.getChildren().add(change);
		newMenu.getChildren().add(back);
		oldPane.setLeft(newMenu);
	}
	
	private static void changeAndRebuild(BorderPane oldPane, String number, VBox oldMenu) {
		try {
			int newMax = Integer.parseInt(number);
			AuctionCentral.calendar.setMaximumUpcomingAuctions(newMax);
			
			oldPane.setLeft(oldMenu);
			oldPane.setCenter(new VBox());
			back(oldPane, oldMenu);
			
		} catch (NumberFormatException e) {
			
		}
	}


}
