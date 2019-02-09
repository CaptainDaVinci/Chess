package main;
import java.util.HashSet;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import util.Constant;
import util.Pair;

public class Board {
	
	Game gameRef;
	private Pair activatedCell;
	private StackPane stackPanes[][];

	public Board(Game g) {
		activatedCell = new Pair(-1, -1);
		stackPanes = new StackPane[8][8];
		gameRef = g;
	}
	
	public void draw(GridPane grid) {
		for (int i = 0; i < 8; ++i) {
			for (int j = 0; j < 8; ++j) {
				Rectangle cell = new Rectangle(j * Constant.CELL_SIZE, i * Constant.CELL_SIZE, Constant.CELL_SIZE, Constant.CELL_SIZE);
				cell.setFill(Color.TRANSPARENT);

				Color cellColor = Color.web(((i + j) & 1) == 0 ? Constant.WHITE_CELL: Constant.BLACK_CELL);
				Background background = new Background(new BackgroundFill(cellColor, CornerRadii.EMPTY, Insets.EMPTY));

				stackPanes[i][j] = new StackPane();
				stackPanes[i][j].setBackground(background);
				stackPanes[i][j].getChildren().add(cell);

				grid.add(stackPanes[i][j], j, i);

				Pair selectedCell = new Pair(i, j);
				stackPanes[i][j].setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent arg0) {
						boardClicked(grid, selectedCell);
					}
				});
			}
		}
		
		drawPieces(grid);
	}
	
	private boolean sameSideMove(Pair selectedCell, byte color, boolean turn) {
		if (!gameRef.isEmpty(selectedCell.x, selectedCell.y) && 
				gameRef.getColor(selectedCell.x, selectedCell.y) == color&& 
				turn) {

			if (gameRef.getMoveList().contains(selectedCell)) {
				return false;
			}
			
			return true;
		}
		
		return false;
	}
	
	private void boardClicked(GridPane grid, Pair selectedCell) {
		if (sameSideMove(selectedCell, Constant.WHITE, !gameRef.getTurn())) {
			return ;
		}
		
		if (sameSideMove(selectedCell, Constant.BLACK, gameRef.getTurn())) {
			return ;
		}

		if (activatedCell.equals(-1, -1)) {
			activatedCell.copy(selectedCell);
			drawPossibleMoves();
			return ;
		} 

		for (Pair p : gameRef.getMoveList()) {
			int nodeIndex = stackPanes[p.x][p.y].getChildren().size() - 1;
			stackPanes[p.x][p.y].getChildren().remove(nodeIndex);
		}

		if (gameRef.validMove(selectedCell)) {
			gameRef.makeMove(activatedCell, selectedCell);
			activatedCell.set(-1, -1);
			drawPieces(grid);
		} else {
			activatedCell.copy(selectedCell);
			drawPossibleMoves();
		}
	}
	
	public void drawPossibleMoves() {
		HashSet<Pair> moves = gameRef.generateMoves(activatedCell.x, activatedCell.y);

		for (Pair p : moves) {
			Circle circle = new Circle(Constant.CELL_MARKER_RADIUS);
			circle.setFill(Color.rgb(215, 191, 147, 0.45));
			stackPanes[p.x][p.y].getChildren().add(circle);
		}
	}
	
	private void drawPieces(GridPane grid) {
		for (int i = 0; i < 8; ++i) {
			for (int j = 0; j < 8; ++j) {
				if (stackPanes[i][j].getChildren().size() == 2) {
					stackPanes[i][j].getChildren().remove(1);
				}

				if (!gameRef.isEmpty(i, j)) {
					Rectangle cell = new Rectangle(j * Constant.CELL_SIZE, i * Constant.CELL_SIZE, Constant.CELL_SIZE, Constant.CELL_SIZE);
					
					ImagePattern imagePattern;
					if (gameRef.isKing(i, j)) {
						if (gameRef.isWhite(i, j)) {
							imagePattern = Constant.WHITE_KING_IMAGE;
						} else {
							imagePattern = Constant.BLACK_KING_IMAGE;
						}
					} else if (gameRef.isQueen(i, j)) {
						if (gameRef.isWhite(i, j)) {
							imagePattern = Constant.WHITE_QUEEN_IMAGE;
						} else {
							imagePattern = Constant.BLACK_QUEEN_IMAGE;
						}
					} else if (gameRef.isBishop(i, j)) {
						if (gameRef.isWhite(i, j)) {
							imagePattern = Constant.WHITE_BISHOP_IMAGE;
						} else {
							imagePattern = Constant.BLACK_BISHOP_IMAGE;
						}
					} else if (gameRef.isKnight(i, j)) {
						if (gameRef.isWhite(i, j)) {
							imagePattern = Constant.WHITE_KNIGHT_IMAGE;
						} else {
							imagePattern = Constant.BLACK_KNIGHT_IMAGE;
						}
					} else if (gameRef.isRook(i, j)) {
						if (gameRef.isWhite(i, j)) {
							imagePattern = Constant.WHITE_ROOK_IMAGE;
						} else {
							imagePattern = Constant.BLACK_ROOK_IMAGE;
						}
					} else {
						if (gameRef.isWhite(i, j)) {
							imagePattern = Constant.WHITE_PAWN_IMAGE;
						} else {
							imagePattern = Constant.BLACK_PAWN_IMAGE;
						}
					}
					
					cell.setFill(imagePattern);
					stackPanes[i][j].getChildren().add(cell);
				}
			}
		}
	}
	
}
