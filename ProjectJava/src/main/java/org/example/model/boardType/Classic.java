package org.example.model.boardType;

import org.example.model.Player;

public class Classic implements BoardType {
	boolean winner;
	int lastSquare;

	public Classic(int squares) {
		this.lastSquare = squares;
	}

	public boolean endAction(int posAfterRoll, Player player) {
		if (posAfterRoll >= lastSquare) {
			winner = true;
			player.setCurrentPos(lastSquare);
		} else {
			player.setCurrentPos(posAfterRoll);
		}
		return winner;
	}
}
