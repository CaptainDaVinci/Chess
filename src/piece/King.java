package piece;

import util.Pair;
import java.util.ArrayList;

import main.Game;
import util.Helper;

public class King {

	public static ArrayList<Pair> generateMoves(Game gameRef, byte color, int x, int y) {
		ArrayList<Pair> moveList = new ArrayList<>();

		for (int i = -1; i <= 1; ++i) {
			for (int j = -1; j <= 1; ++j) {
				int x1 = x + i, y1 = y + j;
				if (Helper.insideBoard(x1, y1)) {
					Helper.cellCheck(gameRef.getCell(x1, y1), x1, y1, color, moveList);
				}
			}
		}
		
		return moveList;
	}

}
