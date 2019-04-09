package main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import main.Board;
import util.Constant;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Background background = new Background(new BackgroundFill(Constant.BACKGROUND_IMAGE, CornerRadii.EMPTY, Insets.EMPTY));

		StackPane mainSceneRoot = new StackPane();
		mainSceneRoot.setBackground(background);

		Image image = new Image("/res/chess-logo.png"); 
		ImageView imageView = new ImageView();
        imageView.setImage(image);

        Button button = new Button("Start");
        button.setMinWidth(200);
        button.setMinHeight(50);
        button.setStyle(
        			"-fx-text-fill: white;" + 
        			"-fx-font-family: Arial Narrow;" + 
        			"-fx-font-weight: bold;" + 
        			"-fx-background-color: linear-gradient(#d38e57, #aa6e3f);" 
        		);

		VBox box = new VBox(85, imageView, button);
		box.setAlignment(Pos.CENTER);
		mainSceneRoot.getChildren().add(box);
	
		Scene mainScene = new Scene(mainSceneRoot);
		primaryStage.setScene(mainScene);
		primaryStage.setMaximized(true);
		primaryStage.show();
		
		BorderPane root = new BorderPane();
		root.setBackground(background);

		GridPane grid = new GridPane();
		grid.setHgap(0.1);
		grid.setVgap(0.1);
		grid.setAlignment(Pos.CENTER);
		grid.setMaxHeight(8.4 * Constant.CELL_SIZE);
		grid.setMaxWidth(8.4 * Constant.CELL_SIZE);
		grid.setStyle("-fx-background-color: #512c0e;");
		root.setCenter(grid);

		String time = Constant.TIME / 60 + ":" + Constant.TIME % 60;
		Text whiteTime = createText(time);
		Text blackTime = createText(time);
		Text result = createText("");
		result.setFill(Color.RED);
		
		StackPane whitePane = createStackPane(whiteTime);
		StackPane blackPane = createStackPane(blackTime);
		StackPane resultPane = createStackPane(result);

		VBox timerBox = new VBox(2.5 * Constant.CELL_SIZE, blackPane, resultPane, whitePane);
		timerBox.setPadding(new Insets(50));
		timerBox.setAlignment(Pos.CENTER_LEFT);
		root.setRight(timerBox);

		GridPane whiteGrid = new GridPane();
		GridPane blackGrid = new GridPane();
		
		whiteGrid.setMinSize(4 * Constant.CELL_SIZE, Constant.CELL_SIZE);
		blackGrid.setMinSize(4 * Constant.CELL_SIZE, Constant.CELL_SIZE);

		VBox piecesTakenBox = new VBox(5 * Constant.CELL_SIZE, whiteGrid, blackGrid);
		piecesTakenBox.setPadding(new Insets(50));
		piecesTakenBox.setAlignment(Pos.CENTER_RIGHT);
		root.setLeft(piecesTakenBox);

		button.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				primaryStage.getScene().setRoot(root);
				Game game = new Game(whiteTime, blackTime, whiteGrid, blackGrid, result);
				Board board = new Board(game);
				board.draw(grid);
			}
		});
	}
	
	private StackPane createStackPane(Text timeText) {
		StackPane pane = new StackPane();
		
		Rectangle cell = new Rectangle(0, 0, 300, 100);
		cell.setFill(Color.WHITE);
		cell.setArcWidth(20);
		cell.setArcHeight(20);

		pane.getChildren().addAll(cell, timeText);

		return pane;
	}

	private Text createText(String time) {
		Text t = new Text(time);
		t.setStyle(
				"-fx-font: 32 arial;"
				);
		return t;
	}
	
	public static void main(String args[]) {
		launch(args);
	}

}
