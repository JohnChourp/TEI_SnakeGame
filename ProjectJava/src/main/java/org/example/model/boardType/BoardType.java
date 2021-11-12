package org.example.model.boardType;

import org.example.model.Player;

public interface BoardType {
	boolean boardTypeAction(int posAfterRoll, Player player);
}

