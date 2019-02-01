package piece;

import util.Pair;
import java.util.ArrayList;

import main.Game;
import util.Helper;

public class Queen {

	public static ArrayList<Pair> generateMoves(Game gameRef, byte color, int x, int y) {
		ArrayList<Pair> moveList = new ArrayList<Pair>();
		
		for (int i = x + 1; i < 8; ++i) {
			if (Helper.cellCheck(gameRef.getCell(i, y), i, y, color, moveList)) {
				break;
			}
		}

		for (int i = x - 1; i >= 0; --i) {
			if (Helper.cellCheck(gameRef.getCell(i, y), i, y, color, moveList)) {
				break;
			}
		}

		for (int i = y + 1; i < 8; ++i) {
			if (Helper.cellCheck(gameRef.getCell(x, i), x, i, color, moveList)) {
				break;
			}
		}

		for (int i = y - 1; i >= 0; --i) {
			if (Helper.cellCheck(gameRef.getCell(x, i), x, i, color, moveList)) {
				break;
			}
		}
	
		for (int i = x + 1, j = y + 1; i < 8 && j < 8; ++i, ++j) {
			if (Helper.cellCheck(gameRef.getCell(i, j), i, j, color, moveList)) {
				break;
			}
		}

		for (int i = x - 1, j = y - 1; i >= 0 && j >= 0; --i, --j) {
			if (Helper.cellCheck(gameRef.getCell(i, j), i, j, color, moveList)) {
				break;
			}
		}
		
		for (int i = x - 1, j = y + 1; i >= 0 && j < 8; --i, ++j) {
			if (Helper.cellCheck(gameRef.getCell(i, j), i, j, color, moveList)) {
				break;
			}
		}

		for (int i = x + 1, j = y - 1; i < 8 && j >= 0; ++i, --j) {
			if (Helper.cellCheck(gameRef.getCell(i, j), i, j, color, moveList)) {
				break;
			}
		}
		
		return moveList;
	}

}
