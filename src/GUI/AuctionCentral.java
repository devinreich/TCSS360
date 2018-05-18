package GUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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




public class AuctionCentral extends Application {
	static Scene scene1;
	static Stage window;
	public static void main(String[] args) { launch(args); }

	public AuctionCentral() {
		super();
	
	}

	@Override
	public void start(Stage primaryStage) {
		window = primaryStage;
		window.setTitle("Welcome to Auction Central");
		StackPane root = new StackPane();
		root.setAlignment(Pos.CENTER);
		displayLoginPane(root);
		window.setScene(new Scene(root, 900, 550));
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

		btn.setOnAction(event -> window.setScene(scene1));
		VBox layout1 = new VBox(20);
		final Text title2 = new Text("Auction Central2");
		final Button btn2 = new Button("This is the second scene");
		layout1.getChildren().addAll(title2,btn2);
		scene1 = new Scene(layout1,900,500);
		
		final HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_CENTER);
		hbBtn.getChildren().add(btn);
		grid.add(hbBtn, 1, 4);

		root.getChildren().add(grid);
		}
}
	
