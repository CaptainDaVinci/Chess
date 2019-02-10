package main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
		BorderPane border = new BorderPane();
		
		Background background = new Background(new BackgroundFill(Constant.BACKGROUND_IMAGE, CornerRadii.EMPTY, Insets.EMPTY));
		border.setBackground(background);

		GridPane grid = new GridPane();
		grid.setHgap(0.1);
		grid.setVgap(0.1);
		grid.setAlignment(Pos.CENTER);
		grid.setMaxHeight(8.4 * Constant.CELL_SIZE);
		grid.setMaxWidth(8.4 * Constant.CELL_SIZE);
		grid.setStyle("-fx-background-color: black;");
		border.setCenter(grid);

		Text whiteTime = createText("10:00");
		Text blackTime = createText("10:00");
		
		StackPane whitePane = createStackPane(whiteTime);
		StackPane blackPane = createStackPane(blackTime);

		VBox timerBox = new VBox(5 * Constant.CELL_SIZE, whitePane, blackPane);
		timerBox.setPadding(new Insets(50));
		timerBox.setAlignment(Pos.CENTER_LEFT);
        border.setRight(timerBox);

        GridPane whiteGrid = new GridPane();
        GridPane blackGrid = new GridPane();

        /*
        Rectangle cell2 = new Rectangle(0, 0, Constant.CELL_SIZE / 2, Constant.CELL_SIZE / 2);
        cell2.setFill(Constant.BLACK_QUEEN_IMAGE);
        blackGrid.add(cell2, 0, 0);
        */

		VBox piecesTakenBox = new VBox(5 * Constant.CELL_SIZE, whiteGrid, blackGrid);
		piecesTakenBox.setPadding(new Insets(50));
		piecesTakenBox.setAlignment(Pos.CENTER_RIGHT);
		border.setLeft(piecesTakenBox);

		Scene scene = new Scene(border);
		primaryStage.setScene(scene);
		primaryStage.setMaximized(true);
		primaryStage.show();

		Game game = new Game(whiteTime, blackTime);
		Board board = new Board(game);
		board.draw(grid);
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
