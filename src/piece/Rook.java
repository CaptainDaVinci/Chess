package piece;

import util.Pair;
import java.util.HashSet;

import main.Game;
import util.Helper;

public class Rook {

	public static HashSet<Pair> generateMoves(Game gameRef, byte color, int x, int y) {
		HashSet<Pair> moveList = new HashSet<>();

		for (int i = x + 1; i < 8; ++i) {
			int option = Helper.isKillable(gameRef.getCell(i, y), color); 
			if (option == 0 || option == 1) {
				moveList.add(new Pair(i, y));
			}
			
			if (option != 0) {
				break;
			}
		}

		for (int i = x - 1; i >= 0; --i) {
			int option = Helper.isKillable(gameRef.getCell(i, y), color); 
			if (option == 0 || option == 1) {
				moveList.add(new Pair(i, y));
			}
			
			if (option != 0) {
				break;
			}
		}

		for (int i = y + 1; i < 8; ++i) {
			int option = Helper.isKillable(gameRef.getCell(x, i), color); 
			if (option == 0 || option == 1) {
				moveList.add(new Pair(x, i));
			}
			
			if (option != 0) {
				break;
			}
		}

		for (int i = y - 1; i >= 0; --i) {
			int option = Helper.isKillable(gameRef.getCell(x, i), color); 
			if (option == 0 || option == 1) {
				moveList.add(new Pair(x, i));
			}
			
			if (option != 0) {
				break;
			}
		}
		
		return moveList;
	}

}
