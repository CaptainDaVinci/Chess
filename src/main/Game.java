package main;
import java.util.ArrayList;

import util.Pair;
import util.Constant;

public class Game {
	private byte board[][];
	private ArrayList<Pair> moveList;
	private boolean turn;

	public Game() {
		board = new byte[8][8];
		moveList = new ArrayList<Pair>();
		turn = true;
		
		for (int i = 0; i < 8; ++i) {
			board[1][i] = Constant.PAWN;
		}
		
		for (int i = 0; i < 8; ++i) {
			board[6][i] = Constant.PAWN | Constant.WHITE;
		}
		
		board[0][0] = board[0][7] = Constant.ROOK;
		board[7][0] = board[7][7] = Constant.ROOK | Constant.WHITE;

		board[0][1] = board[0][6] = Constant.KNIGHT;
		board[7][1] = board[7][6] = Constant.KNIGHT | Constant.WHITE;

		board[0][2] = board[0][5] = Constant.BISHOP;
		board[7][2] = board[7][5] = Constant.BISHOP | Constant.WHITE;
		
		board[0][3] = Constant.KING;
		board[7][4] = Constant.KING | Constant.WHITE;

		board[0][4] = Constant.QUEEN;
		board[7][3] = Constant.QUEEN | Constant.WHITE;
	}
	
	public boolean getTurn() {
		return this.turn;
	}
	
	public byte[][] getBoard() {
		return this.board;
	}
	
	public byte getColor(int i, int j) {
		return (byte) (board[i][j] & Constant.WHITE);
	}
	
	public boolean isWhite(int i, int j) {
		return (board[i][j] & Constant.WHITE) != 0;
	}
	
	public boolean isBlack(int i, int j) {
		return (board[i][j] & Constant.WHITE) == 0;
	}
	
	public boolean isKing(int i, int j) {
		return (board[i][j] & Constant.KING) != 0;
	}

	public boolean isQueen(int i, int j) {
		return (board[i][j] & Constant.QUEEN) != 0;
	}

	public boolean isRook(int i, int j) {
		return (board[i][j] & Constant.ROOK) != 0;
	}

	public boolean isBishop(int i, int j) {
		return (board[i][j] & Constant.BISHOP) != 0;
	}

	public boolean isKnight(int i, int j) {
		return (board[i][j] & Constant.KNIGHT) != 0;
	}

	public boolean isPawn(int i, int j) {
		return (board[i][j] & Constant.PAWN) != 0;
	}
	
	public boolean isEmpty(int i, int j) {
		return board[i][j] == 0;
	}
	
	public byte getCell(int i, int j) {
		return board[i][j];
	}
	
	public boolean validMove(Pair cell) {
		for (Pair p : moveList) {
			if (p.equals(cell)) {
				return true;
			}
		}

		return false;
	}
	
	public void makeMove(Pair from, Pair to) {
		byte fromPieceId = board[from.x][from.y];
		
		board[from.x][from.y] = 0;
		board[to.x][to.y] = fromPieceId;
		
		this.turn = !this.turn;
		moveList.clear();
	}
	
	public ArrayList<Pair> generateMoves(int x, int y) {
		if (isKing(x, y)) {
			moveList = piece.King.generateMoves(this, getColor(x, y), x, y);
		} else if (isQueen(x, y)) {
			moveList = piece.Queen.generateMoves(this, getColor(x, y), x, y);
		} else if (isRook(x, y)) {
			moveList = piece.Rook.generateMoves(this, getColor(x, y), x, y);
		} else if (isBishop(x, y)) {
			moveList = piece.Bishop.generateMoves(this, getColor(x, y), x, y);
		} else if (isKnight(x, y)) {
			moveList = piece.Knight.generateMoves(this, getColor(x, y), x, y);
		} else if (isPawn(x, y)) {
			moveList = piece.Pawn.generateMoves(this, getColor(x, y), x, y);
		} else {
			moveList.clear();
		}
		
		return moveList;
	}
	
	public ArrayList<Pair> getMoveList() {
		return moveList;
	}
	
	public void clearMoves() {
		moveList.clear();
	}

}
