package piece;

import util.Pair;

import java.util.ArrayList;
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
		
		if (gameRef.getTurn() && (color & Constant.WHITE) != Constant.WHITE) {
			return moveList;
		}
		
		if (!gameRef.getTurn() && (color & Constant.WHITE) == Constant.WHITE) {
			return moveList;
		}
		
		int rank = (color & Constant.WHITE) == Constant.WHITE ? 7 : 0;
		if (gameRef.getShortCastleRights()) {
			boolean flag = true;
			for (int i = 5; i < 7; ++i) {
				if (!gameRef.isEmpty(rank, i)) {
					flag = false;
					break;
				}
			}

			if (flag) {
				ArrayList<Pair> positions = new ArrayList<Pair>();
				for (int i = 4; i <= 7; ++i) {
					positions.add(new Pair(rank, i));
				}
				
				if (!Helper.isAttacked(gameRef, positions, color)) {
					moveList.add(new Pair(rank, 6));
					gameRef.setShortCastle(true);
				}
			}
		}

		if (gameRef.getLongCastleRights()) {
			boolean flag = true;
			for (int i = 1; i < 4; ++i) {
				if (!gameRef.isEmpty(rank, i)) {
					flag = false;
					break;
				}
			}

			if (flag) {
				ArrayList<Pair> positions = new ArrayList<Pair>();
				for (int i = 0; i <= 4; ++i) {
					positions.add(new Pair(rank, i));
				}
				
				if (!Helper.isAttacked(gameRef, positions, color)) {
					moveList.add(new Pair(rank, 2));
					gameRef.setLongCastle(true);
				}
			}
		}
		
		return moveList;
	}

}
