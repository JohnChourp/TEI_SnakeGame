package org.example.model.squares;

import org.example.model.Player;
import org.example.ui.Screen;

public class SquareLoseTurn implements SquareInfo {
    public void applyAction(Player currentPlayer) {
        if (currentPlayer.isHasImmunity()) {
            currentPlayer.setHasImmunity(false);
            Screen.displayMessage("Used immunity");
        } else {
            currentPlayer.setLostTurn(true);
            Screen.displayMessage("Lost turn next round");
        }
    }
}