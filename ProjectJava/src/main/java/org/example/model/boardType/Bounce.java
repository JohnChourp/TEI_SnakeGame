package org.example.model.boardType;

import org.example.model.Player;
import org.example.model.ui.Screen;

public class Bounce implements BoardInfo {
	boolean winner;
	int lastSquare;

	public Bounce(int squares) {
		this.lastSquare = squares;
	}

	public boolean boardTypeAction(int posAfterRoll, Player player) {
		if (posAfterRoll > lastSquare) {
			posAfterRoll = 2 * lastSquare - posAfterRoll;
			Screen.displayMessage("Hit Board and Bounced back " + (lastSquare - posAfterRoll) + " squares , new position is " + (posAfterRoll + 1));
		}
		player.setPos(posAfterRoll);
		return winner = posAfterRoll == lastSquare;
	}
}
