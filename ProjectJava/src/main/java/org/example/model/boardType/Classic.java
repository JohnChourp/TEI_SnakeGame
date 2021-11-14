package org.example.model.boardType;

import org.example.model.Player;

public class Classic implements BoardInfo {
	boolean winner;
	int lastSquare;

	public Classic(int squares) {
		this.lastSquare = squares;
	}

	public boolean boardTypeAction(int posAfterRoll, Player player) {
		if (posAfterRoll >= lastSquare) {
			winner = true;
			player.setPos(lastSquare);
		} else {
			player.setPos(posAfterRoll);
		}
		return winner;
	}
}
