package org.example.model.boardType;

import org.example.model.Player;

public interface BoardInfo {
	boolean boardTypeAction(int posAfterRoll, Player player);
}

