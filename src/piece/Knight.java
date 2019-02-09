package piece;

import util.Pair;
import java.util.HashSet;

import main.Game;
import util.Helper;

public class Knight {

	public static HashSet<Pair> generateMoves(Game gameRef, byte color, int x, int y) {
		HashSet<Pair> moveList = new HashSet<>();

		int dx[] = {1, -1};
		int dy[] = {2, -2};
		
		for (int i = 0; i < 2; ++i) {
			for (int j = 0; j < 2; ++j) {
				int x1 = x + dx[i], y1 = y + dy[j];
				int x2 = x + dy[j], y2 = y + dx[i];

				if (Helper.insideBoard(x1, y1)) {
					int option = Helper.isKillable(gameRef.getCell(x1, y1), color); 
					if (option == 0 || option == 1) {
						moveList.add(new Pair(x1, y1));
					}
				}
				
				if (Helper.insideBoard(x2, y2)) {
					int option = Helper.isKillable(gameRef.getCell(x2, y2), color); 
					if (option == 0 || option == 1) {
						moveList.add(new Pair(x2, y2));
					}
				}
			}
		}
		
		return moveList;
	}

}
