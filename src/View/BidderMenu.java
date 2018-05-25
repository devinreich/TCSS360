package View;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import Controller.Run;
import Model.Auction;
import Model.Bidder;
import Model.Calendar;
import Model.Item;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Bidder menu class. User(Bidder) can place bids on valid auctions
 *  & view auctions/items on which they they have placed valid bids.
 * @author prancingponies
 */
public class BidderMenu {

	private Bidder bidder;

	public BidderMenu(Bidder theBidder) {
		bidder = theBidder;
	}

	/**
	 * Launch Bidder menu
	 */
	public void launchMenu() {

		System.out.println("Which option would you like to choose?");
		Scanner scanner = new Scanner(System.in);
		String option;
		do {

			displayBidderStartMenu(scanner);			

			System.out.println("\nEnter c to continue, q to quit.");
			option = scanner.next();
		} while (!option.equals("q"));

	}

	
	/**
	 * Display options for Bidder to choose from
	 * @param theScanner
	 */
	public void displayBidderStartMenu(Scanner theScanner) {

		System.out.println("   1) View Auctions Pertaining To Your Active Bids");
		System.out.println("   2) View All Items Pertaining To Your Active Bids");
		System.out.println("   3) View All Biddable Auctions\n");
		System.out.println("Enter Your Choice: ");

		int choice = theScanner.nextInt();

		switch(choice) {

		case 1:
			displayAuctionsWithItemBids();
			break;
		case 2:
			displayItemBids();
			break;
		case 3:
			displayBiddableAuctions(theScanner);
			break;
		}
	}

	
	/**
	 * View auctions where Bidder has placed bids
	 */
	public void displayAuctionsWithItemBids() {		
		if (bidder.getAuctionsWithBids() != null) {
			if (bidder.getAuctionsWithBids().isEmpty()) {
				System.out.println("There are currently no auctions with items you've bid on.");
			} else {
				System.out.println("~~~~~~Viewing: Auctions in which I have placed bids~~~~~~");
				ArrayList<Auction> bidderAuctions = bidder.getAuctionsWithBids();
				for (Auction a: bidderAuctions) {
					System.out.println();
					System.out.println("Auction hosted by: " + a.getOrganization().getName() + ".");
					System.out.println("Auction date: " + a.getDate());
					System.out.println("Item/s: ");
					
					for (int j = 0; j < bidder.getAllItems().size(); j++) {

						//checks auction inventory for items the user has bid on
						if (a.getInventory().contains(bidder.getAllItems().get(j))){
							Item item = (bidder.getAllItems().get(j));
							System.out.println("\tItem: " + item.getName());
							System.out.println("\tDescription: " + item.getDescription());
							System.out.println("\tBid Amount: $" + bidder.getBidAmount(item));
						}
					}
				}	
			}
		}
	}


	/**
	 * View all items bidder has bid on
	 */
	public void displayItemBids() {
		System.out.println("~~~~~~Viewing: Items on which I have placed bids~~~~~~");
		for (Item item: bidder.getAllItems()) {
			String itemName = item.getName();
			System.out.println();
			System.out.println("Item: " + itemName);		
			System.out.println("Bid Amount: $" +  bidder.getBidAmount(item));	
			System.out.println("Auction: " + bidder.getAuction(item).getOrganization().getName());
		}		
	}

	
	/**
	 * Show all auctions the bidder has bid on
	 * @param theScanner
	 */
	public void displayBiddableAuctions(Scanner theScanner) {
		System.out.println("~~~~~~Viewing: Auctions on which I can bid~~~~~~");
		if (Run.calendar.getUpcomingAuctions().isEmpty()) {
			System.out.println("There are currently no auctions accepting bids.");
		} else {

			System.out.println("These are the auctions that are accepting bids: ");
			int index = 1;
			ArrayList<Auction> biddableAuctions = bidder.getBiddableAuctions();
			for (Auction a: (ArrayList<Auction>) biddableAuctions) {
				String orgName = a.getOrganization().getName();
				System.out.println("   " + index + ") Auction hosted by: " + orgName);
				System.out.println("      Auction date: " + a.getDate());
				index++;
			}
			System.out.println("Enter the number of your auction of choice: ");
			int choice = theScanner.nextInt();
			displayAuctionBidMenu(biddableAuctions.get(choice-1), theScanner);
		}
	}

	
	/**
	 * Display bid menu for specific auction
	 * @param theAuction
	 * @param theScanner
	 */
	public void displayAuctionBidMenu(Auction theAuction, Scanner theScanner) {
		String orgName = theAuction.getOrganization().getName();
		System.out.println("You have selected the auction hosted by " + orgName);
		System.out.println("Auction Inventory: ");
		int index = 1;
		for (Item item: theAuction.getInventory()) {
			System.out.println("   " + index + ") Item Name: " + item.getName());
			System.out.println("     Starting Price: $" + item.getBasePrice());
			index++;
		}
		System.out.println("Enter the number of the item you wish to bid on: ");
		int choice = theScanner.nextInt();
		Item itemChoice = theAuction.getInventory().get(choice-1);
		System.out.println("You have selected the " + itemChoice.getName());
		System.out.println("Enter your bid (must be larger than current price):");
		double bidPrice = theScanner.nextDouble();
		theAuction.placeBid(bidPrice, LocalDate.now(), bidder, itemChoice);
	}
	
	public static Scene getBidderMenu(Scene scene, Bidder user, Calendar calendar) {
		BorderPane pane = new BorderPane();
		Text title = new Text("Welcome Bidder: " + user.getName());
		VBox layout = new VBox(20);
		layout.setPadding(new Insets(10));
		layout.getChildren().add(title);
		pane.setLeft(layout);
		scene = new Scene(pane,900,500);
		
		ArrayList<Auction> auctions = calendar.getUpcomingAuctions();
		ArrayList<Auction> biddableAuction = new ArrayList<>();
		ArrayList<Auction> biddedAuctions = new ArrayList<>();
		ArrayList<Item> bidderItems = user.getAllItems();
		for (int i = 0; i < auctions.size(); i++) {
			ArrayList<Item> invetory = auctions.get(i).getInventory();
			for (Item item : invetory) {
				if (!user.containsItem(item) && auctions.get(i).canBidderBid(user)) {
					biddableAuction.add(auctions.get(i));
					break;
				} else {
					if (!biddedAuctions.contains(auctions.get(i))) {
						biddedAuctions.add(auctions.get(i));
					}
				}
			}
		}
		if (biddableAuction.size() > 0 && user.canBid()) { 
			final Button btn = new Button("View Biddable Auctions");

			btn.setOnAction(event -> 
				viewAuctions(pane, biddableAuction, user)
			);
			layout.getChildren().add(btn);
		}
		
		if (user.getAllBids().size() > 0) {
			final Button btn = new Button("View all items you have bid on");

			btn.setOnAction(event -> 
				viewItems(user, pane)
			);
			layout.getChildren().add(btn);
			
			final Button btn2 = new Button("View all Auctions which contain items you have bid on");

			btn2.setOnAction(event -> 
				viewAuctions(pane, biddedAuctions, user)
			);
			layout.getChildren().add(btn2);
		}
		
		if (layout.getChildren().size() == 1) {
			Text warning = new Text("There are no actions! Please wait for an auction to be posted");
			layout.getChildren().add(warning);
		}
		 
		return scene;
	}
	
	public static void viewAuctions(BorderPane pane, ArrayList<Auction> auctions, Bidder user) {
		VBox oldMenu = (VBox) pane.getLeft();
		VBox newMenu = new VBox();
		
		Text title = new Text("Biddable Auctions");
		newMenu.setPadding(new Insets(10));
		newMenu.getChildren().add(title);
		Button back = new Button("Back");
		
		back.setOnAction(event -> 
			back(pane, oldMenu)
		);
		newMenu.getChildren().add(back);
		
		for (Auction acution: auctions) {
			Button btn = new Button(acution.getOrganization().getName());
			
			btn.setOnAction(event -> 
				viewAuction(pane, acution, user)
			);
			
			newMenu.getChildren().add(btn);
		}
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