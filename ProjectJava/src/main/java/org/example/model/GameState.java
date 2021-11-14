package org.example.model;

import org.example.model.boardType.Board;
import org.example.model.squares.Squares;
import org.example.model.gameStart.GameStart;
import org.example.model.interaction.Interaction;
import org.example.model.ui.QuitGame;

public class GameState {
	private final Board board;
	private final Squares squares;
	private final PlayerList playerList;
	private final Die die;
	private final GameStart gameStart;
	private final Interaction interaction;
	private final QuitGame quit = new QuitGame();
	private Player player;

	public GameState(Board board, Squares squares, PlayerList playerList, Die die, GameStart gameStart, Interaction interaction, Player player) {
		this.board = board;
		this.squares = squares;
		this.playerList = playerList;
		this.die = die;
		this.gameStart = gameStart;
		this.interaction = interaction;
		this.player = player;
	}

	public PlayerList getPlayerList() {
		return playerList;
	}

	public Player setCurrentPlayer() {
		return player = playerList.getPlayerList().get(playerList.getPlayerNumber());
	}

	public void gameStartAction() {
		if (quit.isQuitPrompt()) {
			gameStart.gameStartAction(player);
		}
	}

	public boolean boardTypeAction() {
		return board.getBoardType().boardTypeAction(player.getPos() + die.rollDie(), player);
	}

	public void squareInfoAction() {
		squares.getSquares().get(player.getPos()).squareInfoAction(player);
	}

	public void interactionAction() {
		interaction.interactionAction(player);
	}

	public void setNextPlayer() {
		if (quit.isQuit()) {
			playerList.setNextPlayerNumber();
		}
	}

	public int getDieNumber() {
		return die.getDieNumber();
	}

	public boolean isQuit() {
		return quit.isQuit();
	}
}
