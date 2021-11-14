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

	public void interactionAction(Player player) {
		for (int i = 0; i < playerList.getPlayerList().size(); i++) {
			if (player.getPos() == playerList.getPlayerList().get(i).getPos() && i != playerList.getPlayerNumber()) {
				Screen.displayMessage(player.getName() + " hits " + playerList.getPlayerList().get(i).getName());
				playerList.getPlayerList().get(i).setPos(sendPlayerTo);
			}
		}
	}
}
