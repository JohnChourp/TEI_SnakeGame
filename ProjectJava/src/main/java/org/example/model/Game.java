package org.example.model;

import org.example.model.boardType.Board;
import org.example.model.gameStart.GameStart;
import org.example.model.interaction.Interaction;
import org.example.model.squares.Squares;
import org.example.model.ui.Screen;

public class Game {
	GameState gameState;

	public Game(Board board, Squares squares, PlayerList playerList, Die die, GameStart gameRules, Interaction interaction, Player currentPlayer) {
		gameState = new GameState(board, squares, playerList, die, gameRules, interaction, currentPlayer);
	}

	public void play() {
		Player player;
		PlayerList playerList = gameState.getPlayerList();
		boolean winner = false;

		do {
			player = gameState.setCurrentPlayer();
			Screen.displayMessage("\nIt's " + player.getName() + "'s turn.");
			gameState.gameStartAction();

			if (player.isCanPlayAtStart() && gameState.isQuit() && ! player.isLostTurn()) {
				winner = gameState.boardTypeAction();
				Screen.displayMessage("Die number is " + gameState.getDieNumber());
				gameState.squareInfoAction();
				gameState.interactionAction();
				Screen.displayMessage("New position is " + (playerList.getPlayerList().get(playerList.getPlayerNumber()).getPos()));
			} else {
				player.setLostTurn(false);
			}
			gameState.setNextPlayer();
		} while (! winner && gameState.isQuit());

		if (gameState.isQuit()) {
			Screen.displayMessage("\n!!! Player " + player.getName() + " Won !!!");
		}
	}
}