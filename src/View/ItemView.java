package View;

import java.time.LocalDate;
import java.util.ArrayList;

import Model.Auction;
import Model.Bid;
import Model.Bidder;
import Model.Item;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.converter.NumberStringConverter;

public class ItemView {
	public static VBox makeItemView(Item item, Bidder bidder, Auction auction) {
		VBox itemView = new VBox();
		
		addDetails(itemView, item);
	
		if (bidder.getAllItems().contains(item) || !auction.canBidderBid(bidder)) {
			addAlreadyBidField(itemView, item, bidder);
		} else {
			addBidField(itemView, item, bidder, auction);
		}
		return itemView;
	}
	
	private static void addDetails(VBox itemView, Item item) {
		Text title = new Text(item.getName());
		itemView.getChildren().add(title);
		
		Text price = new Text("Minimum Bid: " + item.getBasePrice());
		itemView.getChildren().add(price);
	}
	
	private static void addAlreadyBidField(VBox itemView, Item item, Bidder bidder) {
		ArrayList<Bid> bids = bidder.getAllBids();
		for (Bid bid : bids) {
			if (bid.getItem().equals(item)) {
				itemView.getChildren().add(new Text("Your bid: " + bid.getBidAmount()));
				break;
			}
		}
	}
	
	private static void addBidField(VBox itemView, Item item, Bidder bidder, Auction auction) {
		final TextField userTextField = new TextField();
		userTextField.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
		userTextField.setMaxSize(100, 50);
		itemView.getChildren().add(userTextField);

		final Button btn = new Button("Bid");
		
		btn.setOnAction(event -> 
			bidAndRebuild(itemView, item, bidder, auction, userTextField.getText())
		);
		itemView.getChildren().add(btn);
	}
	
	private static void rebuildItem(VBox itemView, Item item, Bidder bidder) {
		itemView.getChildren().remove(0, itemView.getChildren().size());
		addDetails(itemView, item);
		addAlreadyBidField(itemView, item, bidder);
	}
	
	private static void bidAndRebuild(VBox itemView, Item item, Bidder bidder, Auction auction, String bid) {
		try {
			double bidPrice = Double.parseDouble(bid);
			auction.placeBid(bidPrice, LocalDate.now(), bidder, item);
			rebuildItem(itemView, item, bidder);
		} catch (NumberFormatException e) {
			
		}
	}
}
