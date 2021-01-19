package org.example.model.gameStart;

import org.example.model.Die;
import org.example.model.Player;
import org.example.ui.Screen;

public class RollDiceToEnter implements GameStart {
    Die die;

    public RollDiceToEnter(Die die) {
        this.die = die;
    }

    public void startCondition(Player currentPlayer) {
        if (!currentPlayer.isCanPlayAtStart()) {
            currentPlayer.setCanPlayAtStart(die.rollDie() == 6);
            Screen.displayMessage("Die number is " + die.rollDie());

            if (currentPlayer.isCanPlayAtStart()) {
                Screen.displayMessage("Entered Board");
            } else {
                Screen.displayMessage("Number was not 6, Try next round");
            }
        }
    }
}
