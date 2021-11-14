package org.example.model.squares;

import org.example.model.Player;
import org.example.model.ui.Screen;

public class loseTurn implements SquareInfo {
	public void squareInfoAction(Player player) {
		if (player.isHasImmunity()) {
			player.setHasImmunity(false);
			Screen.displayMessage("Used immunity");
		} else {
			player.setLostTurn(true);
			Screen.displayMessage("Lost turn next round");
		}
	}
}