package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class PlayerList {

	private final List<Player> playerList = new ArrayList<>();
	private int currentPlayerNumber = 0;

	public List<Player> getPlayerList() {
		return playerList;
	}

	public void setPlayer(Player playerToAdd) {
		playerList.add(playerToAdd);
	}

	public int getCurrentPlayerNumber() {
		return currentPlayerNumber;
	}

	public void setCurrentPlayerNumber() {
		currentPlayerNumber = (currentPlayerNumber + 1) % playerList.size();
	}

	public void setCurrentPlayerNumber(int currentPlayer) {
		this.currentPlayerNumber = currentPlayer;
	}

	public Player getPlayer(int playerNumber) {
		return playerList.get(playerNumber);
	}

	public int getPlayerPos(int playerNumber) {
		return getPlayer(playerNumber).getCurrentPos();
	}

	public int getCurrentPlayerPos() {
		return playerList.get(currentPlayerNumber).getCurrentPos();
	}

	public void setPlayerPos(int playerNumber, int sendPlayerTo) {
		playerList.get(playerNumber).setCurrentPos(sendPlayerTo);
	}
}