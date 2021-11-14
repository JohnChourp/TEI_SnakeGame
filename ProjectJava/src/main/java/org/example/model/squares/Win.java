package org.example.model.squares;

import org.example.model.Player;

public class Win implements SquareInfo {
	public void squareInfoAction(Player player) {
		player.setWinner(true);
	}
}
