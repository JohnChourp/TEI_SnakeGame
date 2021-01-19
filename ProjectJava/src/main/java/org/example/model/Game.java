package org.example.model;

import org.example.model.gameStart.GameStart;
import org.example.model.interaction.Interaction;
import org.example.ui.Screen;

public class Game {
    GameState gameState;

    public Game(Board board, PlayerList playerList, Die die, GameStart gameRules, Interaction interaction, Player currentPlayer) {
        gameState = new GameState(board, playerList, die, gameRules, interaction, currentPlayer);
    }

    public void play() {
        Player player;
        PlayerList playerList = gameState.getPlayerList();

        do {
            player = gameState.setCurrentPlayer();
            Screen.displayMessage("\nIt's " + player.getName() + "'s turn.");
            gameState.setStartCondition();

            if (player.isCanPlayAtStart() && gameState.isQuit() && !player.isLostTurn()) {
                gameState.setEndAction();
                Screen.displayMessage("Die number is " + gameState.getDieNumber());
                gameState.setApplyAction();
                gameState.setMeetPlayerAction();
                Screen.displayMessage("New position is " + (playerList.getCurrentPlayerPos() + 1));
            } else {
                player.setLostTurn(false);
            }
            gameState.setNextPlayer();
        }
        while (!gameState.isWinner() && gameState.isQuit());

        if (gameState.isQuit()) {
            Screen.displayMessage("\n!!! Player " + player.getName() + " Won !!!");
        }
    }
}