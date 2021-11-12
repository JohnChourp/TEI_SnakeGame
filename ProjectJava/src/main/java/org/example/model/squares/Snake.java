package org.example.model.squares;

import org.example.model.Player;
import org.example.model.ui.Screen;

public class Snake implements SquareInfo {
	private final int goToSquare;

	public Snake(int goToSquare) {
		this.goToSquare = goToSquare;
	}

	public void squareInfoAction(Player currentPlayer) {
		if (currentPlayer.isHasImmunity()) {
			currentPlayer.setHasImmunity(false);
			Screen.displayMessage("Used immunity");
		} else {
			currentPlayer.setPos(goToSquare - 1);
			Screen.displayMessage("Pressed Snake ");
		}
	}
}
