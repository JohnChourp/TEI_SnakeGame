package org.example.model.squares;

import java.util.ArrayList;

public class Squares {
	private final ArrayList<SquareInfo> squares = new ArrayList<>();

	public ArrayList<SquareInfo> getSquares() {
		return squares;
	}

	public void addSquare(SquareInfo squareToAdd) {
		squares.add(squareToAdd);
	}
}
