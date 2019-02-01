package piece;

import util.Pair;
import java.util.ArrayList;

import main.Game;
import util.Helper;

public class Knight {

	public static ArrayList<Pair> generateMoves(Game gameRef, byte color, int x, int y) {
		ArrayList<Pair> moveList = new ArrayList<>();

		int dx[] = {1, -1};
		int dy[] = {2, -2};
		
		for (int i = 0; i < 2; ++i) {
			for (int j = 0; j < 2; ++j) {
				int x1 = x + dx[i], y1 = y + dy[j];
				int x2 = x + dy[j], y2 = y + dx[i];

				if (Helper.insideBoard(x1, y1)) {
					Helper.cellCheck(gameRef.getCell(x1, y1), x1, y1, color, moveList);
				}
				
				if (Helper.insideBoard(x2, y2)) {
					Helper.cellCheck(gameRef.getCell(x2, y2), x2, y2, color, moveList);
				}
			}
		}
		
		return moveList;
	}

}
