package main;
import java.util.HashSet;

import util.Pair;
import util.Constant;
import util.Helper;

public class Game {
	
	private byte board[][];
	private HashSet<Pair> moveList;
	private boolean turn;
	private boolean blackKingMoved, whiteKingMoved;
	private boolean whiteRookMoved, whiteQueenRookMoved;
	private boolean blackRookMoved, blackQueenRookMoved;
	private boolean longCastle, shortCastle;

	public Game() {
		board = new byte[8][8];
		moveList = new HashSet<Pair>();
		turn = true;
		blackKingMoved = whiteKingMoved = false;
		whiteRookMoved = whiteQueenRookMoved = false;
		blackRookMoved = blackQueenRookMoved = false;
		shortCastle = longCastle = false;
		
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

		board[0][3] = Constant.QUEEN;
		board[7][3] = Constant.QUEEN | Constant.WHITE;

		board[0][2] = board[0][5] = Constant.BISHOP;
		board[7][2] = board[7][5] = Constant.BISHOP | Constant.WHITE;
		
		board[0][4] = Constant.KING;
		board[7][4] = Constant.KING | Constant.WHITE;

	}
	
	public void setShortCastle(boolean status) {
		shortCastle = status;
	}
	
	public void setLongCastle(boolean status) {
		longCastle = status;
	}
	
	public boolean getTurn() {
		return this.turn;
	}
	
	public boolean getShortCastleRights() {
		if (turn) {
			return !(whiteKingMoved || whiteRookMoved);
		}
		
		return !(blackKingMoved || blackRookMoved);
	}
	
	public boolean getLongCastleRights() {
		if (turn) {
			return !(whiteKingMoved || whiteQueenRookMoved);
		}
		
		return !(blackKingMoved || blackQueenRookMoved);
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
		return moveList.contains(cell);
	}
	
	public void makeMove(final Pair from, final Pair to) {
		byte fromPieceId = board[from.x][from.y];

		if (isPawn(from.x, from.y) && pawnPromotion(from, to)) {
			fromPieceId = Constant.QUEEN;
			if (isWhite(from.x, from.y)) {
				fromPieceId |= Constant.WHITE;
			}
		}
		
		if (isKing(from.x, from.y)) {
			int rank = turn ? 7 : 0;

			if (shortCastle && to.x == rank && to.y == 6) {
				board[from.x][5] = board[rank][7];
				board[rank][7] = 0;
			} else if (longCastle && to.x == rank && to.y == 2) {
				board[from.x][3] = board[rank][0];
				board[rank][0] = 0;
			}
			
			if (turn) {
				whiteKingMoved = true;
			} else {
				blackKingMoved = true;
			}
		}
		
		if (isRook(from.x, from.y)) {
			if (turn) {
				if (from.y == 7) {
					whiteRookMoved = true;
				} else {
					whiteQueenRookMoved = true;
				}
			} else {
				if (from.y == 7) {
					blackRookMoved = true;
				} else {
					blackQueenRookMoved = true;
				}
			}
		}

		board[from.x][from.y] = 0;
		board[to.x][to.y] = fromPieceId;
		
		this.turn = !this.turn;
		moveList.clear();

		setShortCastle(false);
		setLongCastle(false);
	}
	
	private boolean pawnPromotion(Pair from, Pair to) {
		if (isWhite(from.x, from.y)) {
			return to.x == 0;
		}
		
		if (isBlack(from.x, from.y)) {
			return to.x == 7;
		}
		
		return false;
	}
	
	public HashSet<Pair> generateMoves(int x, int y) {
		moveList.clear();
		HashSet<Pair> allMoves = Helper.getMoveList(this, x, y);
		
		for (Pair move : allMoves) {
			byte temp = board[move.x][move.y];
			board[move.x][move.y] = board[x][y];
			board[x][y] = 0;
			
			if (!Helper.isCheck(this, (turn ? Constant.WHITE : Constant.BLACK))) {
				moveList.add(move);
			}

			board[x][y] = board[move.x][move.y];
			board[move.x][move.y] = temp;
		}

		return moveList;
	}
	
	public HashSet<Pair> getMoveList() {
		return moveList;
	}
	
	public void clearMoves() {
		moveList.clear();
	}

}
