package org.example.model.boardType;

import org.example.model.Player;

public interface BoardType {
	void endAction(int posAfterRoll, Player player);

	boolean isWinner();
}

