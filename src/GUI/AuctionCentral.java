package GUI;

import Controller.Serializer;
import Model.Bidder;
import Model.Calendar;
import Model.ContactPerson;
import Model.Employee;
import Model.User;
import View.AuctioneerMenu;
import View.BidderMenu;
import View.EmployeeMenu;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;


public class AuctionCentral extends Application {
	static Scene scene1;
	static Stage window;
	static User user;
	public static Calendar calendar;
	public static void main(String[] args) { launch(args); }

	public AuctionCentral() {
		super();
	
	}

	@Override
	public void start(Stage primaryStage) {
		calendar = (Calendar) Serializer.deserialize("calendar");
		window = primaryStage;
		window.setTitle("Welcome to Auction Central");
		StackPane root = new StackPane();
		root.setAlignment(Pos.CENTER);
		displayLoginPane(root);
		window.setScene(new Scene(root, 900, 550));
		window.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override
		    public void handle(WindowEvent event) {
		        try {
		        		if (user != null) {
		        			Serializer.serialize(user, user.getLoginName());
		        		}
		        		if (calendar != null) {
		        			Serializer.serialize(calendar, "calendar");
		        		}
					stop();
					Platform.exit();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		});
		window.show();
	}
	
	protected final static void displayLoginPane(StackPane root) {
		final GridPane grid = new GridPane();

		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		final Text title = new Text("Auction Central");
		title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(title, 0, 0, 2, 1);

		final Label userName = new Label("User Name:");
		grid.add(userName, 0, 1);

		final TextField userTextField = new TextField();
		grid.add(userTextField, 1, 1);

		final Button btn = new Button("Sign in");
		btn.setDefaultButton(true);

		btn.setOnAction(event -> 
			setScene(userTextField.getText())
		);
		
		final HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_CENTER);
		hbBtn.getChildren().add(btn);
		grid.add(hbBtn, 1, 4);

		root.getChildren().add(grid);
	}
	
	public static void setScene(String text) {
		user = (User) Serializer.deserialize(text);
		Text title;
		if (user instanceof Bidder) {
			window.setScene(BidderMenu.getBidderMenu(scene1, (Bidder) user, calendar));
		} else if (user instanceof ContactPerson) {
			window.setScene(AuctioneerMenu.getContactMenu(scene1, (ContactPerson) user, calendar));
//			title = new Text("Welcome Contact Person: " + user.getName());
//			VBox layout1 = new VBox(20);
//			layout1.getChildren().addAll(title);
//			scene1 = new Scene(layout1,900,500);
//			window.setScene(scene1);
		} else {
			window.setScene(EmployeeMenu.getEmployeeMenu(scene1, (Employee) user, calendar));
//			title = new Text("Welcome Employee: " + user.getName());
//			VBox layout1 = new VBox(20);
//			layout1.getChildren().addAll(title);
//			scene1 = new Scene(layout1,900,500);
//			window.setScene(scene1);
		}
	}
	

}