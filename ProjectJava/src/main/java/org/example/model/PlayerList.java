package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class PlayerList {
	private final List<Player> playerList = new ArrayList<>();
	private int playerNumber = 0;

	public List<Player> getPlayerList() {
		return playerList;
	}

	public void setPlayerList(Player playerToAdd) {
		playerList.add(playerToAdd);
	}

	public int getPlayerNumber() {
		return playerNumber;
	}

	public void setPlayerNumber(int currentPlayer) {
		this.playerNumber = currentPlayer;
	}

	public void setNextPlayerNumber() {
		playerNumber = (playerNumber + 1) % playerList.size();
	}
}