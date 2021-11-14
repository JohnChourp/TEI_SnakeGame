package org.example.model.boardType;

import org.example.model.Player;
import org.example.model.ui.Screen;

public class Bounce implements BoardInfo {
	int lastSquare;

	public Bounce(int squares) {
		this.lastSquare = squares;
	}

	public void boardTypeAction(int posAfterRoll, Player player) {
		if (posAfterRoll > lastSquare) {
			posAfterRoll = 2 * lastSquare - posAfterRoll;
			Screen.displayMessage("Hit Board and Bounced back " + (lastSquare - posAfterRoll) + " squares , new position is " + posAfterRoll);
		}
		player.setPos(posAfterRoll);
	}
}
