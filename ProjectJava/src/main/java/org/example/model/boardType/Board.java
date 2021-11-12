package org.example.model.boardType;

import org.example.model.squares.SquareInfo;

import java.util.ArrayList;

public class Board {
	private final ArrayList<SquareInfo> squares = new ArrayList<>();
	private BoardType boardType;

	public ArrayList<SquareInfo> getSquares() {
		return squares;
	}

	public void addSquare(SquareInfo squareToAdd) {
		squares.add(squareToAdd);
	}

	public BoardType getBoardType() {
		return boardType;
	}

	public void setBoardType(BoardType boardType) {
		this.boardType = boardType;
	}
}