package util;

import java.util.ArrayList;
import java.util.HashSet;

import javafx.scene.paint.ImagePattern;
import main.Game;

public class Helper {

	public static int isKillable(byte cell, byte color) {
		if (cell == 0) {
			return 0;
		}

		byte cellColor = (byte) (cell & Constant.WHITE);
		if (cellColor != color) {
			return 1;
		}
		
		return -1;
	}

	public static boolean insideBoard(int x, int y) {
		return x >= 0 && y >= 0 && x < 8 && y < 8;
	}
	
	public static HashSet<Pair> getMoveList(Game gameRef, int x, int y) {
		HashSet<Pair> moveList = new HashSet<Pair>();

		if (gameRef.isKing(x, y)) {
			moveList = piece.King.generateMoves(gameRef, gameRef.getColor(x, y), x, y);
		} else if (gameRef.isQueen(x, y)) {
			moveList = piece.Queen.generateMoves(gameRef, gameRef.getColor(x, y), x, y);
		} else if (gameRef.isRook(x, y)) {
			moveList = piece.Rook.generateMoves(gameRef, gameRef.getColor(x, y), x, y);
		} else if (gameRef.isBishop(x, y)) {
			moveList = piece.Bishop.generateMoves(gameRef, gameRef.getColor(x, y), x, y);
		} else if (gameRef.isKnight(x, y)) {
			moveList = piece.Knight.generateMoves(gameRef, gameRef.getColor(x, y), x, y);
		} else if (gameRef.isPawn(x, y)) {
			moveList = piece.Pawn.generateMoves(gameRef, gameRef.getColor(x, y), x, y);
		} 

		return moveList;
	}
	
	public static boolean isAttacked(Game gameRef, ArrayList<Pair> positions, byte color) {
		for (int i = 0; i < 8; ++i) {
			for (int j = 0; j < 8; ++j) {
				if (gameRef.getColor(i, j) == color) {
					continue;
				}
				
				HashSet<Pair> moveList = getMoveList(gameRef, i, j);
				for (Pair position : positions) {
					if (moveList.contains(position)) {
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	public static ImagePattern getImagePattern(Game gameRef, int i, int j) {
		ImagePattern imagePattern = null;
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
		
		return imagePattern;
	}

}