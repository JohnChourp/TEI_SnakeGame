package org.example.model.squares;

import org.example.model.Player;
import org.example.model.ui.Screen;

public class Ladder implements SquareInfo {
	private final int goToSquare;

	public Ladder(int goToSquare) {
		this.goToSquare = goToSquare;
	}

	public void squareInfoAction(Player currentPlayer) {
		currentPlayer.setPos(goToSquare - 1);
		Screen.displayMessage("Pressed ladder ");
	}
}
