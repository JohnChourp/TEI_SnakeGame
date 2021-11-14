package org.example.model.squares;

import org.example.model.Player;
import org.example.model.ui.Screen;

public class GetImmunity implements SquareInfo {
	public void squareInfoAction(Player player) {
		player.setHasImmunity(true);
		Screen.displayMessage("Got immunity");
	}
}
