package org.example.model.boardType;

import org.example.model.Player;
import org.example.model.ui.Screen;

public class Loop implements BoardType {
	boolean winner;
	int rounds;
	int lastSquare;

	public Loop(int rounds, int squares) {
		this.rounds = rounds;
		this.lastSquare = squares;
	}

	public boolean endAction(int posAfterRoll, Player player) {

		if (posAfterRoll > lastSquare) {
			posAfterRoll = posAfterRoll - lastSquare;
			player.setRounds();
			Screen.displayMessage(player.getName() + " Looped " + player.getRound() + " Times");
		}
		player.setCurrentPos(posAfterRoll);

		if (player.getRound() == rounds) {
			player.setCurrentPos(lastSquare);
			winner = true;
		}
		return winner;
	}
}
