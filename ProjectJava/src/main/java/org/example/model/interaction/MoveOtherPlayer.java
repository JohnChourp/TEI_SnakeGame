package org.example.model.interaction;

import org.example.model.Player;
import org.example.model.PlayerList;
import org.example.model.ui.Screen;

public class MoveOtherPlayer implements Interaction {
	int sendPlayerTo;
	PlayerList playerList;

	public MoveOtherPlayer(PlayerList playerList, int sendPlayerTo) {
		this.sendPlayerTo = sendPlayerTo;
		this.playerList = playerList;
	}

	public void interactionAction(Player currentPlayer) {
		for (int i = 0; i < playerList.getPlayerList().size(); i++) {
			if (currentPlayer.getCurrentPos() == playerList.getPlayerList().get(i).getCurrentPos() && i != playerList.getCurrentPlayerNumber()) {
				Screen.displayMessage(currentPlayer.getName() + " hits " + playerList.getPlayerList().get(i).getName());
				playerList.getPlayerList().get(i).setCurrentPos(sendPlayerTo);
			}
		}
	}
}
