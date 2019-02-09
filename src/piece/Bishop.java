package piece;

import java.util.HashSet;

import main.Game;
import util.Pair;
import util.Helper;

public class Bishop {

	public static HashSet<Pair> generateMoves(Game gameRef, byte color, int x, int y) {
		HashSet<Pair> moveList = new HashSet<>();

		for (int i = x + 1, j = y + 1; i < 8 && j < 8; ++i, ++j) {
			int option = Helper.isKillable(gameRef.getCell(i, j), color); 
			if (option == 0 || option == 1) {
				moveList.add(new Pair(i, j));
			}
			
			if (option != 0) {
				break;
			}
		}
		
		for (int i = x - 1, j = y + 1; i >= 0 && j < 8; --i, ++j) {
			int option = Helper.isKillable(gameRef.getCell(i, j), color); 
			if (option == 0 || option == 1) {
				moveList.add(new Pair(i, j));
			}
			
			if (option != 0) {
				break;
			}
		}

		for (int i = x - 1, j = y - 1; i >= 0 && j >= 0; --i, --j) {
			int option = Helper.isKillable(gameRef.getCell(i, j), color); 
			if (option == 0 || option == 1) {
				moveList.add(new Pair(i, j));
			}
			
			if (option != 0) {
				break;
			}
		}

		for (int i = x + 1, j = y - 1; i < 8 && j >= 0; ++i, --j) {
			int option = Helper.isKillable(gameRef.getCell(i, j), color); 
			if (option == 0 || option == 1) {
				moveList.add(new Pair(i, j));
			}
			
			if (option != 0) {
				break;
			}
		}
		
		return moveList;
	}

}
