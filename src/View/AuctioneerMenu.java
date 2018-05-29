package View;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import Controller.Run;
import GUI.AuctionCentral;
import Model.Auction;
import Model.Calendar;
import Model.ContactPerson;
import Model.Item;
import Model.Organization;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;



public class AuctioneerMenu {

	private ContactPerson contact;
	private Organization auctioneer;
	//private Calendar calendar;

	/** 
	 * Auctioneer (Organization/Contact person) menu constructor
	 * @param theAuctioneer
	 * @param theCalendar
	 */
	public AuctioneerMenu(ContactPerson theAuctioneer) {
		contact = theAuctioneer;
		System.out.println("Auctioneer: " + contact.getName());
		auctioneer = theAuctioneer.getAffiliatedOrganization();
		Auction auction = Run.calendar.syncCurrentAuction(auctioneer, auctioneer.getCurrentAuction());
		if (auction != null) {
			auctioneer.setCurrentAuction(auction);
		}
	}

	public void launchMenu() {

		System.out.println("Which option would you like to choose?");
		Scanner scanner = new Scanner(System.in);
		String option;

		do {
			//Only print out options the user can access 
			if (auctioneer.hasActiveAuction()) {

				int choice = displayActiveAuctionMenu(scanner);
				switch(choice) {

				case 1: // View Active Auction

					int auctionChoice;			
					do {
					auctioneer.displayCurrentAuctionDetails();
					auctionChoice = displayAuctionDetailMenu(scanner);
					auctionMenuOptions(auctionChoice, scanner);
					} while (auctionChoice != 4);
					break;
				case 2:
					// format output for method within organization class
					// TODO: Look back over this
					ArrayList<Auction> auctioneerAuctions = auctioneer.getAuctions();
					int j = 1;
					for (int i = 0; i < auctioneerAuctions.size(); i++) {
						Auction auction = auctioneerAuctions.get(i);
						System.out.println(j + ") Auction Date: " + auction.getDate()); 
							for (int x = 0; x < auction.getInventory().size(); x++) {
								int index = x + 1;
								System.out.println("Item " + index + ": " + getItemDescription(auction.getInventory().get(x)));
							}
						j++;
						System.out.println();
					}
					break; 
				case 3:
					//add method to organization
					//auctioneer.cancelCurrentAuction();
					break;
				}

			} else {
				int choice = displayInactiveAuctionMenu(scanner);

				switch(choice) {
				case 1: 
					Run.calendar.submitAuctionRequest(auctioneer);
					break;
				case 2: 
					ArrayList<Auction> auctioneerAuctions = auctioneer.getAuctions();
					int j = 1;
					for (int i = 0; i < auctioneerAuctions.size(); i++) {
						Auction auction = auctioneerAuctions.get(i);
						System.out.println(j + ") Auction Date: " + auction.getDate()); 
							for (int x = 0; x < auction.getInventory().size(); x++) {
								int index = x + 1;
								System.out.println("Item " + index + ": " + getItemDescription(auction.getInventory().get(x)));
							}
						j++;
						System.out.println();
					}
					
//					auctioneer.getAuctions();
//					for (Auction auction: auctioneer.getAuctions()) {
//						System.out.println("Date: " + auction.getDate());
//					}
				}			
			}
			if (scanner.hasNextLine()) {
				scanner.nextLine();
			}
			System.out.println("\nEnter c to continue, q to quit.");
			option = scanner.next();
		} while (!option.equals("q"));

	}

	
	/**
	 * Displays menu for an Organization with an Active Auction set
	 * @param theScanner
	 * @return
	 */
	public int displayActiveAuctionMenu(Scanner theScanner) {

		System.out.println("   1) View Current Auction");
		System.out.println("   2) View All Auctions");
		System.out.println("   3) Cancel Active Auction");
		System.out.println("Enter your choice: ");

		int choice = theScanner.nextInt();
		return choice;
		// Should check for error input?
	}

	
	/**
	 * Display menu for Organization with no Active Auction set
	 * @param theScanner
	 * @return
	 */
	public int displayInactiveAuctionMenu(Scanner theScanner) {

		System.out.println("   1) Submit New Auction Request");
		System.out.println("   2) View All Auctions");
		System.out.println("Enter your choice: ");

		int choice = theScanner.nextInt();
		return choice;
	}

	
	/**
	 * Options for the current (active) auction for an Organization.
	 * @param theScanner
	 * @return
	 */
	public int displayAuctionDetailMenu(Scanner theScanner) {
		
		/** MUST REPEAT THIS MENU UNTIL USER SELECTS 4*/
		//!!!!!!!!!!!!

		System.out.println("Which option would you like to choose?");
		System.out.println("   1) Add Item To Inventory");
		System.out.println("   2) Remove Item From Inventory");
		System.out.println("   3) View Current Inventory");
		System.out.println("   4) Return To Previous Menu");

		int choice = theScanner.nextInt();
		return choice;
	}

	
	/**
	 * Lets the organization add item to inventory,
	 * remove item from inventory,
	 * view current inventory,
	 * or return to previous menu.
	 * @param theOption
	 * @param theScanner
	 */
	public void auctionMenuOptions(int theOption, Scanner theScanner) {

		Auction currentAuction = auctioneer.getCurrentAuction();

		while (theOption > 4 || theOption < 1) {
			System.out.println("Not a valid option, try again.");
			theOption = theScanner.nextInt();
		}

		switch(theOption) {
	
			case 1:	//Add item
				Scanner scanner = new Scanner(System.in);
				System.out.print("Enter Item Name: ");
				String itemName = scanner.nextLine();
				System.out.print("\nEnter Item Description: ");
				String itemDescription = scanner.nextLine();
				System.out.print("\nEnter minimum bid price for item: $");
				double basePrice = scanner.nextDouble();
				LocalDate createDate = LocalDate.now();
				Item item = new Item(itemName, itemDescription, basePrice, createDate);
				currentAuction.addItem(item);	
				break;
			case 2:	//Remove item
				for (int i = 0; i < currentAuction.getInventory().size(); i++) {
					System.out.println("Item " + (i + 1) + ": " 
							+ getItemDescription(currentAuction.getInventory().get(i)));
				}
				System.out.print("Enter index of item to remove: ");
				int itemIndex = theScanner.nextInt();
				if (itemIndex <= currentAuction.getInventoryCount() && itemIndex >= 1) {
					currentAuction.removeItemAt(itemIndex - 1);
				}
				break;	
			case 3:	//View all items		
				for (int i = 0; i < currentAuction.getInventory().size(); i++) {
					int index = i + 1;
					System.out.println("Item " + index + ": " 
							+ getItemDescription(currentAuction.getInventory().get(i)));
				}
			case 4:
				//Return to previous menu
			}
	}
	
	
	/**
	 * Returns details about a given Item object. 
	 * @param item
	 * @return
	 */
	public String getItemDescription(Item item) {
		return item.getName() + ", " + item.getDescription() 
			+ ", $" + item.getBasePrice();
	}
	
	/**
	 * Return the Scene of the welcoming for the user
	 * @param scene
	 * @param user
	 * @param calendar
	 * @return scene
	 */
	public static  Scene getContactMenu(Scene scene, ContactPerson user, Calendar calendar) {
		BorderPane pane = new BorderPane();
		ArrayList<Auction> Allauctions = calendar.getAuction();
		LocalDate today = LocalDate.now();
		if(Allauctions.size() != 0 && today.isBefore(Allauctions.get(0).getDate())) {
			Text title = new Text("Welcome Contact Person : " + user.getName());
			VBox layout = new VBox(20);
			final Label cannotSub = new Label("Since your auction has not occurred "
					+ "you can not submit an auction at this time.");
			layout.setPadding(new Insets(10));
			final Button ViewBtn = new Button("View My Auction");
			layout.getChildren().addAll(title,ViewBtn,cannotSub);
			pane.setLeft(layout);
			scene = new Scene(pane,1300,500);
			ViewBtn.setOnAction(event ->
			pane.setCenter(getmyAuction(pane,Allauctions,calendar,user)));
		}
		else if(Allauctions.size() != 0 && today.isAfter(Allauctions.get(0).getDate())) {
			Text title = new Text("Welcome Contact Person : " + user.getName());
			VBox layout = new VBox(20);
			layout.setPadding(new Insets(10));
			final Button ViewBtn = new Button("View My Auction");
			final Button SubmitBtn = new Button("Submit an Auction");
			layout.getChildren().addAll(title,ViewBtn,SubmitBtn);
			pane.setLeft(layout);
			scene = new Scene(pane,1300,500);
			ViewBtn.setOnAction(event ->
			pane.setCenter(getmyAuction(pane,Allauctions,calendar,user)));
	
			SubmitBtn.setOnAction(event ->
			pane.setCenter(submiteAuction(pane,Allauctions,calendar,user)));
		
		}
		else {
			Text title = new Text("Welcome Contact Person : " + user.getName());
			final Label noAuctionWaring = new Label("There is no auction under "
					+ "the organzation right now");
			VBox layout = new VBox(20);
			layout.setPadding(new Insets(10));
			final Button SubmitBtn = new Button("Submit an Auction");
			layout.getChildren().addAll(title,noAuctionWaring,SubmitBtn);
			pane.setLeft(layout);
			scene = new Scene(pane,1300,500);
			SubmitBtn.setOnAction(event ->
			pane.setCenter(submiteAuction(pane,Allauctions,calendar,user)));
		}
		
		return scene;
	}
	
	/**
	 * return the scroll pane for viewing the auction.
	 * @param pane
	 * @param Allauctions
	 * @param calendar
	 * @return the scroll pane
	 */
	public static  ScrollPane getmyAuction(BorderPane pane, ArrayList<Auction> Allauctions, 
			Calendar calendar,ContactPerson user ) {
		final VBox myAuction = new VBox();
		final Label label = new Label("My Auction");
		myAuction.setSpacing(15);
		myAuction.getChildren().add(label);
		
		for (final Auction theauction : Allauctions ) {
			final HBox Auction = new HBox();
			final Label name = new Label(theauction.getOrganization().getName());
			final Label creatdate = new Label("The Auction created date " +
					theauction.getCreateDate().getMonth() + " " + theauction
					.getCreateDate().getDayOfMonth() + ", " + theauction
					.getCreateDate().getYear());
			final Label dateForAuction =new Label("The Date For the auction" + 
					theauction.getDate().getMonth() + " " + theauction
					.getDate().getDayOfMonth() + ", " + theauction
					.getDate().getYear());
			
			final Button viewItemInAuction = new Button("View Item");
			final Button cancleAuction = new Button("cancle auction");
			final Button addItem = new Button ("Add Item");
			Scanner theScanner = new Scanner(System.in);
			addItem.setOnAction(event -> {
				pane.setCenter(addItem(pane,theauction, theScanner));
				
			});
			viewItemInAuction.setOnAction(event -> pane.setCenter(viewItem(pane,theauction)));
			cancleAuction.setOnAction(event -> {
				calendar.cancelAuction(theauction);
				AuctionCentral.setScene(user.getLoginName());
			});
			if(theauction.getInventoryCount() != 0) {
				Auction.getChildren().addAll(name, creatdate, dateForAuction,
						viewItemInAuction,addItem);
			}
			else {
				Auction.getChildren().addAll(name, creatdate, dateForAuction,
						addItem);
			}
			Auction.setSpacing(10);
			myAuction.getChildren().add(Auction);
		}
		
		 
		 
		final ScrollPane sp = new ScrollPane();
		sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
		sp.setContent(myAuction);
		return sp;
		
	}


	/**
	 * display the item in the auction.
	 * @param pane
	 * @param theAuction
	 * @return sp the scroll pane
	 */
	public static  ScrollPane viewItem(BorderPane pane,Auction theAuction) {
		final VBox myuItem = new VBox();
		myuItem.setSpacing(15);
		final Label label = new Label("Item list");
		for (final Item item : theAuction.getInventory() ) {
			final HBox Items = new HBox();
			final Label itemName = new Label("Name: " + item.getName());
			final Label itemDescription = new Label("Item Description: " + item.getDescription());
			final Label itemBasePrice = new Label("Item Base Price: "+item.getBasePrice());
			final Label itemCreatDate = new Label("Item Create Date: " + item.getCreationDate());
			Items.getChildren().addAll(label,itemName, itemDescription, itemBasePrice,
					itemCreatDate);
			Items.setSpacing(10);
			myuItem.getChildren().add(Items);
		}

		final ScrollPane sp = new ScrollPane();
		sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
		sp.setContent(myuItem);

		return sp;
	}
	/**
	 * display the scroll pane for add item in to the auction.
	 * @param pane
	 * @param theAuction
	 * @param theScanner
	 * @return sp the scroll pane
	 */
	public static  ScrollPane addItem(BorderPane pane,Auction theAuction, Scanner theScanner) {
		final VBox myItem = new VBox();
		final Label Name = new Label("Enter Item Name: ");
		TextField NameofItem= new TextField();
		final Label Description = new Label("Enter Item Description: ");
		TextField ItemDescription= new TextField();
		final Label minimum = new Label("Enter minimum bid price for item: ($) ");
		TextField minimumBid= new TextField();
		final Button submit = new Button("Submit");
		submit.setOnAction(event -> {
			theAuction.addItem(NameofItem.getText(),
			ItemDescription.getText(),minimumBid.getText());
			pane.setCenter(viewItem(pane,theAuction));
		}); 
		myItem.getChildren().addAll(Name,NameofItem,Description,ItemDescription,minimum,minimumBid,submit);
		final ScrollPane sp = new ScrollPane();
		sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
		sp.setContent(myItem);
 
		return sp; 
		
	}
	
	/**
	 * display the scroll pane for submit an auction.
	 * @param pane
	 * @param allauctions
	 * @param thecalendar
	 * @param user
	 * @return sp the scroll pane
	 */
	public static ScrollPane submiteAuction(BorderPane pane,ArrayList<Auction> allauctions,
			Calendar thecalendar, ContactPerson user) {
		final VBox myAuction = new VBox();
		final Label dateofMonth = new Label("Please Enter the Month for the auction: ");
		TextField month= new TextField();
		final Label dateofdays = new Label("Please Enter the day for the auction: ");
		TextField day= new TextField();
		final Label dateofYear = new Label("Please Enter the year for the auction: ");
		TextField year= new TextField();
		final Label maxIBbtn = new Label("Enter max number of items bidders can bid on (0 for default)");
		TextField maxitembid= new TextField();
		final Label maxIbtn = new Label("Enter max number of items total allowed for sale (0 for default): ");
		TextField maxitem= new TextField();
		final Button submit = new Button("Submit");
		
		myAuction.getChildren().addAll(dateofMonth,month,dateofdays,
				day,dateofYear,year,maxIBbtn,maxitembid,maxIbtn,maxitem,submit);
	
		submit.setOnAction(event -> {
			int Months = Integer.parseInt(month.getText());
			int Days = Integer.parseInt(day.getText());
			int Years = Integer.parseInt(year.getText());
			int MaxperBid= Integer.parseInt(maxitembid.getText());
			int MaxItemSell=Integer.parseInt(maxitem.getText());
			LocalDate auctionDate = LocalDate.of(Years,Months,Days);
			LocalDate createDate = LocalDate.now();
			if(allauctions.size()== 0) {
				Organization theOrg = new Organization(user.getName());
				Auction theauction = new Auction(auctionDate,createDate,MaxperBid,MaxItemSell,theOrg);
				alertForSubmit(theauction,thecalendar,theOrg);
				thecalendar.submitAuctionRequestWithAuction(theOrg, theauction);
			}
			else {
				Organization theOrg = allauctions.get(0).getOrganization();
				Auction theauction = new Auction(auctionDate,createDate,MaxperBid,MaxItemSell,theOrg);
				alertForSubmit(theauction,thecalendar,theOrg);
				thecalendar.submitAuctionRequestWithAuction(theOrg, theauction);
			}
			AuctionCentral.setScene(user.getLoginName());
		});
		final ScrollPane sp = new ScrollPane();
		sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
		sp.setContent(myAuction);

		return sp;
		
	}
	
	/**
	 * check the business  story for submit auction if not reach the.
	 * requirements pop the alert.
	 * @param theAuction
	 * @param thecalendar
	 * @param theOrg
	 */
	public static void alertForSubmit(Auction theAuction,Calendar thecalendar,Organization theOrg) {
//		LocalDate createDate = LocalDate.now();
		if (!thecalendar.checkDate(theAuction.getDate())) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Message");
			alert.setContentText("Invalid no more than two auctions for the day");

			alert.showAndWait();
		}
		else if(!thecalendar.checkForMaxDays(theAuction.getDate())){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Message");
			alert.setContentText("Invalid the auction can not be scheduled "
					+ "more than 60 days from now");

			alert.showAndWait();
		}
		else if(!thecalendar.checkForMinDays(theAuction.getDate())){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Message");
			alert.setContentText("Invalid the auction has to be at least 14 days from now ");

			alert.showAndWait();
		}
		else if(!thecalendar.checkForUpComingAuctionNumber() ) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Message");
			alert.setContentText("Invalid reach the max number(25) of auction on calendar"
					+ "for the upcoming day ");

			alert.showAndWait();
		}
		else if(!thecalendar.checkBeenYearForOrg(theOrg, theAuction.getDate())){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Message");
			alert.setContentText("Invalid you have to wait for a year for your next auction ");

			alert.showAndWait();
		}
		else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Congratulations");
			alert.setContentText("Your auction has been scheduled ");

			alert.showAndWait();
		}
	}
}