package View;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import Controller.Run;
import Model.Auction;
import Model.Bidder;
import Model.Calendar;
import Model.Employee;
import Model.Item;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

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
			System.out.println(index + ") Auction hosted by: " + auction.getOrganization().getName()
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
		Text title = new Text("Welcome Employee: " + user.getName());
		VBox layout = new VBox(20);
		layout.setPadding(new Insets(10));
		layout.getChildren().add(title);
		pane.setLeft(layout);
		scene = new Scene(pane,900,500);
		
		final Button btn = new Button("Change Maximum Number Of Upcoming Auctions System Can Hold");
		btn.setOnAction(event -> 
			changeMaxAuctions(pane, user)
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
	
	public static void changeMaxAuctions(BorderPane pane, Employee user) {
		VBox oldMenu = (VBox) pane.getLeft();
		VBox newMenu = new VBox();
		
		Text title = new Text("Current System Auction Capacity: " + Run.calendar.getMaximumUpcomingAuctions());
		newMenu.setPadding(new Insets(10));
		newMenu.getChildren().add(title);
		Button back = new Button("Back");
		
		back.setOnAction(event -> 
			back(pane, oldMenu)
		);
		newMenu.getChildren().add(back);
		pane.setLeft(newMenu);
	}
	
	public static void viewAuctionRange(BorderPane pane, Employee user) {
		VBox oldMenu = (VBox) pane.getLeft();
		VBox newMenu = new VBox();
		
		Text title = new Text("Current System Auction Capacity: " + Run.calendar.getMaximumUpcomingAuctions());
		newMenu.setPadding(new Insets(10));
		newMenu.getChildren().add(title);
		Button back = new Button("Back");
		
		back.setOnAction(event -> 
			back(pane, oldMenu)
		);
		newMenu.getChildren().add(back);
		pane.setLeft(newMenu);
	}
	
	public static void viewAllAuctions(BorderPane pane, Employee user) {
		VBox oldMenu = (VBox) pane.getLeft();
		VBox newMenu = new VBox();
		
		Text title = new Text("Current System Auction Capacity: " + Run.calendar.getMaximumUpcomingAuctions());
		newMenu.setPadding(new Insets(10));
		newMenu.getChildren().add(title);
		Button back = new Button("Back");
		
		back.setOnAction(event -> 
			back(pane, oldMenu)
		);
		newMenu.getChildren().add(back);
		pane.setLeft(newMenu);
	}
	
	public static void cancelAuction(BorderPane pane, Employee user) {
		VBox oldMenu = (VBox) pane.getLeft();
		VBox newMenu = new VBox();
		
		Text title = new Text("Current System Auction Capacity: " + Run.calendar.getMaximumUpcomingAuctions());
		newMenu.setPadding(new Insets(10));
		newMenu.getChildren().add(title);
		Button back = new Button("Back");
		
		back.setOnAction(event -> 
			back(pane, oldMenu)
		);
		newMenu.getChildren().add(back);
		pane.setLeft(newMenu);
	}
	
	public static void viewAuction(BorderPane pane, Auction auction, Bidder user) {
		VBox Auction = new VBox();
		
		Text title = new Text(auction.getOrganization().getName());
		title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		Auction.setPadding(new Insets(30));
		Auction.getChildren().add(title);
		
		for (Item item : auction.getInventory()) {
			Auction.getChildren().add(ItemView.makeItemView(item, user, auction));
		}
		
		pane.setCenter(Auction);
	}
	
	public static void back(BorderPane pane, VBox oldMenu) {
		pane.setLeft(oldMenu);
		pane.setCenter(new VBox());;
	}
	
	public static void viewItems(Bidder user, BorderPane pane) {
		VBox Items = new VBox();
		
		Text title = new Text("Items you have bid on:");
		title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		Items.setPadding(new Insets(30));
		Items.getChildren().add(title);
		
		for (Item item : user.getAllItems()) {
			Items.getChildren().add(ItemView.makeItemView(item, user, null));
		}
		
		pane.setCenter(Items);
	}
}
