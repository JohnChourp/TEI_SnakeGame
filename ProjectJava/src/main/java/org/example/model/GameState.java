package org.example.model;

import org.example.model.gameStart.GameStart;
import org.example.model.interaction.Interaction;
import org.example.ui.QuitGame;

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

    public Player setCurrentPlayer() {
        return currentPlayer = playerList.getCurrentPlayer();
    }

    public PlayerList getPlayerList() {
        return playerList;
    }

    public void setEndAction() {
        board.getEndAction(currentPlayer.getCurrentPos() + rollDie(), currentPlayer);
    }

    public void setApplyAction() {
        board.getApplyAction(currentPlayer.getCurrentPos()).applyAction(currentPlayer);
    }

    public boolean isWinner() {
        return board.isWinner();
    }

    public void setNextPlayer() {
        if (isQuit()) {
            playerList.setNextPlayer();
        }
    }

    public int rollDie() {
        return die.rollDie();
    }

    public int getDieNumber() {
        return die.getDieNumber();
    }

    public void setStartCondition() {
        if (isQuitPrompt()) {
            gameStart.startCondition(currentPlayer);
        }
    }

    public void setMeetPlayerAction() {
        interaction.meetPlayerAction(currentPlayer);
    }

    public boolean isQuitPrompt() {
        return quit.isQuitPrompt();
    }

    public boolean isQuit() {
        return quit.isQuit();
    }
}
