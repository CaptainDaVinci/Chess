package main;

import java.awt.Color;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import main.Board;

public class Main extends Application {
	private int width, height;

	@Override
	public void start(Stage primaryStage) throws Exception {
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		width = (int) primaryScreenBounds.getWidth();
		height = (int) primaryScreenBounds.getWidth();
		
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
