package org.example.model;

import org.example.model.boardType.Board;
import org.example.model.gameStart.GameStart;
import org.example.model.interaction.Interaction;
import org.example.model.ui.QuitGame;

public class GameState {
	private final Board board;
	private final PlayerList playerList;
	private final Die die;
	private final GameStart gameStart;
	private final Interaction interaction;
	private final QuitGame quit = new QuitGame();
	private Player currentPlayer;

	public GameState(Board board, PlayerList playerList, Die die, GameStart gameStart, Interaction interaction, Player currentPlayer) {
		this.board = board;
		this.playerList = playerList;
		this.die = die;
		this.gameStart = gameStart;
		this.interaction = interaction;
		this.currentPlayer = currentPlayer;
	}

	public PlayerList getPlayerList() {
		return playerList;
	}

	public Player setCurrentPlayer() {
		return currentPlayer = playerList.getCurrentPlayer();
	}

	public void gameStartAction() {
		if (quit.isQuitPrompt()) {
			gameStart.gameStartAction(currentPlayer);
		}
	}

	public boolean boardTypeAction() {
		return board.getBoardType().boardTypeAction(currentPlayer.getCurrentPos() + die.rollDie(), currentPlayer);
	}

	public void squareInfoAction() {
		board.getSquares().get(currentPlayer.getCurrentPos()).squareInfoAction(currentPlayer);
	}

	public void interactionAction() {
		interaction.interactionAction(currentPlayer);
	}

	public void setNextPlayer() {
		if (quit.isQuit()) {
			playerList.setNextPlayer();
		}
	}

	public int getDieNumber() {
		return die.getDieNumber();
	}

	public boolean isQuit() {
		return quit.isQuit();
	}
}
