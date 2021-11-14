package org.example.model.squares;

import org.example.model.Player;
import org.example.model.ui.Screen;

public class Snake implements SquareInfo {
	private final int goToSquare;

	public Snake(int goToSquare) {
		this.goToSquare = goToSquare;
	}

	public void squareInfoAction(Player player) {
		if (player.isHasImmunity()) {
			player.setHasImmunity(false);
			Screen.displayMessage("Used immunity");
		} else {
			player.setPos(goToSquare - 1);
			Screen.displayMessage("Pressed Snake");
		}
	}
}
