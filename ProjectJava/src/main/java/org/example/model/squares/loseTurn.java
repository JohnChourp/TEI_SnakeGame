package org.example.model.squares;

import org.example.model.Player;
import org.example.model.ui.Screen;

public class loseTurn implements SquareInfo {
	public void squareInfoAction(Player currentPlayer) {
		if (currentPlayer.isHasImmunity()) {
			currentPlayer.setHasImmunity(false);
			Screen.displayMessage("Used immunity");
		} else {
			currentPlayer.setLostTurn(true);
			Screen.displayMessage("Lost turn next round");
		}
	}
}