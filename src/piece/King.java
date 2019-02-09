package piece;

import util.Pair;
import java.util.HashSet;

import main.Game;
import util.Constant;
import util.Helper;

public class King {

	public static HashSet<Pair> generateMoves(Game gameRef, byte color, int x, int y) {
		HashSet<Pair> moveList = new HashSet<>();

		for (int i = -1; i <= 1; ++i) {
			for (int j = -1; j <= 1; ++j) {
				int x1 = x + i, y1 = y + j;
				if (Helper.insideBoard(x1, y1)) {
					if (!gameRef.isEmpty(x1, y1)) {
						if (gameRef.getColor(x1, y1) != color) {
							moveList.add(new Pair(x1, y1));
						}
					} else {
						moveList.add(new Pair(x1, y1));
					}
				}
			}
		}
		
		if (gameRef.getShortCastleRights()) {
			int rank = (color & Constant.WHITE) != 0 ? 7 : 0;
			if (gameRef.isEmpty(rank, 5) && gameRef.isEmpty(rank, 6)) {
				moveList.add(new Pair(rank, 6));
				gameRef.setShortCastle(true);
			}
		}

		if (gameRef.getLongCastleRights()) {
			int rank = (color & Constant.WHITE) != 0 ? 7 : 0;
			if (gameRef.isEmpty(rank, 3) && gameRef.isEmpty(rank, 2) && gameRef.isEmpty(rank, 1)) {
				moveList.add(new Pair(rank, 2));
				gameRef.setLongCastle(true);
			}
		}
		
		return moveList;
	}

}
