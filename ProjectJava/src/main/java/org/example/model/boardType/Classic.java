package org.example.model.boardType;

import org.example.model.Player;

public class Classic implements BoardType {
	boolean winner;

	public void endAction(int posAfterRoll, int lastSquare, Player player) {
		if (posAfterRoll >= lastSquare) {
			winner = true;
			player.setCurrentPos(lastSquare);
		} else {
			player.setCurrentPos(posAfterRoll);
		}
	}

	public boolean isWinner() {
		return winner;
	}
}
