package org.example.model.boardType;

import org.example.model.Player;

public class Classic implements BoardInfo {
	int lastSquare;

	public Classic(int squares) {
		this.lastSquare = squares;
	}

	public void boardTypeAction(int posAfterRoll, Player player) {
		player.setPos(Math.min(posAfterRoll, lastSquare));
	}
}
