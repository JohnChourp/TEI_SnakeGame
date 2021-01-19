package org.example.model.squares;
import org.example.model.Player;
import org.example.ui.Screen;

public class Ladder implements SquareInfo
{
    private final int goToSquare;

    public Ladder(int goToSquare)
    {
        this.goToSquare = goToSquare;
    }

    public void applyAction(Player currentPlayer)
    {
        currentPlayer.setCurrentPos(goToSquare);
        Screen.displayMessage("Pressed ladder ");
    }
}
