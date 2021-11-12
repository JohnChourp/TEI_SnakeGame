package org.example.model.boardType;

import org.example.model.Player;

public interface BoardType {
	boolean endAction(int posAfterRoll, Player player);
}

