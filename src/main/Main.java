package main;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import main.Board;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		GridPane grid = new GridPane();
		grid.setHgap(0.1);
		grid.setVgap(0.1);
		grid.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(grid);
		primaryStage.setScene(scene);
		primaryStage.setMaximized(true);
		primaryStage.show();

		Game game = new Game();
		Board board = new Board(game);
		board.draw(grid);
	}
	
	public static void main(String args[]) {
		launch(args);
	}

}
