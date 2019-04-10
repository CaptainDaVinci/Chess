package util;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class Constant {
	public static final int CELL_SIZE = 100;
	public static final int CELL_MARKER_RADIUS = 20;
	public static final String CELL_MARKER_COLOR = "#d7bf93";
	public static final String WHITE_CELL = "#ffce9e"; 
	public static final String BLACK_CELL = "#d18b47";
	
	public static final byte BLACK = 0x00; 
	public static final byte WHITE = 0x40; 
	public static final byte KING = 0x20;
	public static final byte QUEEN = 0x10;
	public static final byte ROOK = 0x08;
	public static final byte BISHOP = 0x04;
	public static final byte KNIGHT = 0x02;
	public static final byte PAWN = 0x01;

	public static final byte KING_VALUE = 4;
	public static final byte QUEEN_VALUE = 9;
	public static final byte ROOK_VALUE = 5;
	public static final byte BISHOP_VALUE = 3;
	public static final byte KNIGHT_VALUE = 3;
	public static final byte PAWN_VALUE = 1;

	public static final ImagePattern BLACK_KING_IMAGE = new ImagePattern(new Image("/res/black/king.png"));
	public static final ImagePattern BLACK_QUEEN_IMAGE = new ImagePattern(new Image("/res/black/queen.png"));
	public static final ImagePattern BLACK_ROOK_IMAGE = new ImagePattern(new Image("/res/black/rook.png"));
	public static final ImagePattern BLACK_KNIGHT_IMAGE = new ImagePattern(new Image("/res/black/knight.png"));
	public static final ImagePattern BLACK_BISHOP_IMAGE = new ImagePattern(new Image("/res/black/bishop.png"));
	public static final ImagePattern BLACK_PAWN_IMAGE = new ImagePattern(new Image("/res/black/pawn.png"));

	public static final ImagePattern WHITE_KING_IMAGE = new ImagePattern(new Image("/res/white/king.png"));
	public static final ImagePattern WHITE_QUEEN_IMAGE = new ImagePattern(new Image("/res/white/queen.png"));
	public static final ImagePattern WHITE_ROOK_IMAGE = new ImagePattern(new Image("/res/white/rook.png"));
	public static final ImagePattern WHITE_KNIGHT_IMAGE = new ImagePattern(new Image("/res/white/knight.png"));
	public static final ImagePattern WHITE_BISHOP_IMAGE = new ImagePattern(new Image("/res/white/bishop.png"));
	public static final ImagePattern WHITE_PAWN_IMAGE = new ImagePattern(new Image("/res/white/pawn.png"));
	public static final ImagePattern BACKGROUND_IMAGE = new ImagePattern(new Image("/res/background.png"));
	public static final ImagePattern LOGO_IMAGE = new ImagePattern(new Image("/res/chess-logo.png"));
	
	public static final int TIME = 600;
	public static final String CHECKMATE = "Checkmate";
	public static final String CHECK = "Check";
	public static final String STALEMATE = "Stalemate";
	public static final String TIMEOUT = "Time out";
}
