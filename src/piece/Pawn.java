package piece;

import util.Pair;
import java.util.HashSet;

import main.Game;
import util.Helper;
import util.Constant;

public class Pawn {

	public static HashSet<Pair> generateMoves(Game gameRef, byte color, int x, int y) {
		HashSet<Pair> moveList = new HashSet<>();

		if ((color & Constant.WHITE) != 0) {
			if (gameRef.isEmpty(x - 1, y)) {
				moveList.add(new Pair(x - 1, y));
			}
			
			if (x == 6 && gameRef.isEmpty(x - 2, y)) {
				moveList.add(new Pair(x - 2, y));
			}
			

			if (Helper.insideBoard(x - 1, y + 1) && !gameRef.isEmpty(x - 1, y + 1) && gameRef.isBlack(x - 1, y + 1)) {
				moveList.add(new Pair(x - 1, y + 1));
			}

			if (Helper.insideBoard(x - 1, y - 1) && !gameRef.isEmpty(x - 1, y - 1) && gameRef.isBlack(x - 1, y - 1)) {
				moveList.add(new Pair(x - 1, y - 1));
			}
		} else {
			if (gameRef.isEmpty(x + 1, y)) {
				moveList.add(new Pair(x + 1, y));
			}
			
			if (x == 1 && gameRef.isEmpty(x + 2, y)) {
				moveList.add(new Pair(x + 2, y));
			}
			

			if (Helper.insideBoard(x + 1, y + 1) && !gameRef.isEmpty(x + 1, y + 1) && gameRef.isWhite(x + 1, y + 1)) {
				moveList.add(new Pair(x + 1, y + 1));
			}

			if (Helper.insideBoard(x + 1, y - 1) && !gameRef.isEmpty(x + 1, y - 1) && gameRef.isWhite(x + 1, y - 1)) {
				moveList.add(new Pair(x + 1, y - 1));
			}
		}
		
		return moveList;
	}
	
}
