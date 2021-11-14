package org.example.model.boardType;

import org.example.model.Player;
import org.example.model.ui.Screen;

public class Loop implements BoardInfo {
	boolean winner;
	int rounds;
	int lastSquare;

	public Loop(int rounds, int squares) {
		this.rounds = rounds;
		this.lastSquare = squares;
	}

	public boolean boardTypeAction(int posAfterRoll, Player player) {

		if (posAfterRoll > lastSquare) {
			posAfterRoll = posAfterRoll - lastSquare;
			player.setRound();
			Screen.displayMessage(player.getName() + " Looped " + player.getRound() + " Times");
		}
		player.setPos(posAfterRoll);

		if (player.getRound() == rounds) {
			player.setPos(lastSquare);
			winner = true;
		}
		return winner;
	}
}
