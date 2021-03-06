package main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import util.Pair;
import util.WrapInt;
import util.Constant;
import util.Countdown;
import util.Helper;

public class Game {
	
	private byte board[][];
	private HashSet<Pair> moveList;
	private boolean turn;
	private boolean blackKingMoved, whiteKingMoved;
	private boolean whiteRookMoved, whiteQueenRookMoved;
	private boolean blackRookMoved, blackQueenRookMoved;
	private boolean longCastle, shortCastle;
	private boolean gameOver;
	private Timer player1Time, player2Time;
	private WrapInt whiteTime, blackTime;
	private Text whiteTimeText, blackTimeText, resultText;
	private GridPane whiteCasualties, blackCasualties;
	private int wi, wj, bi, bj;

	public Game(Text whiteTimeText, Text blackTimeText, GridPane whiteCasualties, GridPane blackCasualties, Text result) {
		board = new byte[8][8];
		moveList = new HashSet<Pair>();
		turn = true;
		blackKingMoved = whiteKingMoved = false;
		whiteRookMoved = whiteQueenRookMoved = false;
		blackRookMoved = blackQueenRookMoved = false;
		shortCastle = longCastle = false;

		blackTime = new WrapInt(Constant.TIME);
		whiteTime = new WrapInt(Constant.TIME);
		wi = wj = bi = bj = 0;
		gameOver = false;
		
		this.whiteTimeText = whiteTimeText;
		this.blackTimeText = blackTimeText;
		this.whiteTimeText.setFill(Color.RED);
		this.blackTimeText.setFill(Color.BLACK);
		this.whiteCasualties = whiteCasualties;
		this.blackCasualties = blackCasualties;
		this.resultText = result;

		player1Time = new Timer();
		player1Time.schedule(new Countdown(whiteTime, whiteTimeText, resultText), 1000, 1000);

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
		if (turn && whiteTime.i <= 0) {
			resultText.setText(Constant.TIMEOUT);
			return ;
		}

		if (!turn && blackTime.i <= 0) {
			resultText.setText(Constant.TIMEOUT);
			return ;
		}

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

		if (!isEmpty(to.x, to.y)) {
			Rectangle cell = new Rectangle(0, 0, Constant.CELL_SIZE / 2, Constant.CELL_SIZE / 2);
			cell.setFill(Helper.getImagePattern(this, to.x, to.y));

			if (isWhite(to.x, to.y)) {
				whiteCasualties.add(cell, wj++, wi);
				if (wj > 7) {
					++wi;
					wj = 0;
				}
			}

			if (isBlack(to.x, to.y)) {
				blackCasualties.add(cell, bj++, bi);
				if (bj > 7) {
					++bi;
					bj = 0;
				}
			}
		}

		board[to.x][to.y] = fromPieceId;
		
		
		if (turn) {
			whiteTimeText.setFill(Color.BLACK);
			blackTimeText.setFill(Color.RED);
			player1Time.cancel();
			player2Time = new Timer();
			player2Time.schedule(new Countdown(blackTime, blackTimeText, resultText), 0, 1000);
		} else {
			blackTimeText.setFill(Color.BLACK);
			whiteTimeText.setFill(Color.RED);
			player2Time.cancel();
			player1Time = new Timer();
			player1Time.schedule(new Countdown(whiteTime, whiteTimeText, resultText), 0, 1000);
		}
		
		this.turn = !this.turn;
		moveList.clear();

		resultText.setText("");
		boolean inCheck = isCheck();
		if (inCheck) {
			resultText.setText(Constant.CHECK);
		}

		if (noMovesPossible()) {
			if (inCheck) {
				resultText.setText(Constant.CHECKMATE);
			} else {
				resultText.setText(Constant.STALEMATE);
			}
			
			gameOver = true;
			player1Time.cancel();
			player2Time.cancel();
		}

		setShortCastle(false);
		setLongCastle(false);
	}
	
	private boolean noMovesPossible() {
		byte color = turn ? Constant.WHITE : Constant.BLACK;
		
		for (int i = 0; i < 8; ++i) {
			for (int j = 0; j < 8; ++j) {
				if (getColor(i, j) == color) {
					if (!generateMoves(i, j).isEmpty()) {
						return false;
					}
				}
			}
		}
		
		return true;
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
		if (gameOver) {
			return moveList;
		}
		
		if (turn && whiteTime.i <= 0) {
			resultText.setText(Constant.TIMEOUT);
			return moveList;
		}

		if (!turn && blackTime.i <= 0) {
			resultText.setText(Constant.TIMEOUT);
			return moveList;
		}

		HashSet<Pair> allMoves = Helper.getMoveList(this, x, y);
		
		for (Pair move : allMoves) {
			byte temp = board[move.x][move.y];
			board[move.x][move.y] = board[x][y];
			board[x][y] = 0;
			
			if (!this.isCheck()) {
				moveList.add(move);
			}

			board[x][y] = board[move.x][move.y];
			board[move.x][move.y] = temp;
		}

		return moveList;
	}

	public boolean isCheck() {
		byte color = turn ? Constant.WHITE : Constant.BLACK;
		for (int i = 0; i < 8; ++i) {
			for (int j = 0; j < 8; ++j) {
				if (this.getColor(i, j) == color && this.isKing(i, j)) {
					ArrayList<Pair> positions = new ArrayList<Pair>();
					positions.add(new Pair(i, j));
					return Helper.isAttacked(this, positions, color);
				}
			}
		}
		
		return false;
	}
	
	
	public HashSet<Pair> getMoveList() {
		return moveList;
	}
	
	public void clearMoves() {
		moveList.clear();
	}

}
