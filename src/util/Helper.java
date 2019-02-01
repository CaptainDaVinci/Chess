package util;

import java.util.ArrayList;

public class Helper {

	public static boolean cellCheck(byte cell, int i, int j, byte color, ArrayList<Pair> moveList) {
		if (cell != 0 && (cell & Constant.WHITE) == color) {
			return true;
		}

		moveList.add(new Pair(i, j));
		if (cell != 0) {
			return true;
		}
		
		return false;
	}

	public static boolean insideBoard(int x, int y) {
		return x >= 0 && y >= 0 && x < 8 && y < 8;
	}

}